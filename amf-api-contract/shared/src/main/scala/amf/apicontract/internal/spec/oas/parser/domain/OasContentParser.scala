package amf.apicontract.internal.spec.oas.parser.domain

import amf.apicontract.client.scala.model.domain.Payload
import amf.apicontract.internal.metamodel.domain.PayloadModel
import amf.apicontract.internal.spec.common.parser.{SpecParserOps, WebApiShapeParserContextAdapter}
import amf.apicontract.internal.spec.oas.parser.context.OasWebApiContext
import amf.core.client.scala.model.domain.AmfArray
import amf.core.internal.annotations.TrackedElement
import amf.core.internal.parser.YMapOps
import amf.core.internal.parser.domain.{Annotations, ScalarNode}
import amf.shapes.internal.domain.metamodel.ExampleModel
import amf.shapes.internal.domain.resolution.ExampleTracking.tracking
import amf.shapes.internal.spec.common.parser.{AnnotationParser, OasExamplesParser}
import amf.shapes.internal.spec.oas.parser.OasTypeParser
import org.yaml.model.{YMap, YMapEntry}

import scala.collection.mutable

case class OasContentsParser(entry: YMapEntry, producer: Option[String] => Payload)(implicit ctx: OasWebApiContext) {
  def parse(): List[Payload] = {
    val payloads = mutable.ListBuffer[Payload]()
    entry.value
      .as[YMap]
      .entries
      .foreach { entry =>
        payloads += OasContentParser(entry, producer)(ctx).parse()
      }
    payloads.toList
  }
}

case class OasContentParser(entry: YMapEntry, producer: Option[String] => Payload)(implicit ctx: OasWebApiContext)
    extends SpecParserOps {

  private def buildPayloadWithMediaType(): Payload = {
    val mediaTypeNode         = ScalarNode(entry.key)
    val mediaTypeText: String = getMediaType(mediaTypeNode)

    val payload = producer(Some(mediaTypeText)).add(Annotations(entry))
    payload.setWithoutId(PayloadModel.MediaType, mediaTypeNode.string(), Annotations(entry.key))
  }

  private def getMediaType(mediaTypeNode: ScalarNode) =
    mediaTypeNode.text().toString

  private def createNameFromRefUrl(entry: YMapEntry): String = {
    val hasRef = entry.value.value.asInstanceOf[YMap].key("$ref")
    if (hasRef.isDefined) {
      val name = entry.value.value.toString.split("/").last
      if (name.endsWith("\"")) name.substring(0, name.length() - 1) else name
    } else "schema"
  }

  def parse(): Payload = {
    val map     = entry.value.as[YMap]
    val payload = buildPayloadWithMediaType()

    ctx.closedShape(payload, map, "content")

    // schema
    map.key(
      "schema",
      entry => {
        OasTypeParser(entry, shape => shape.withName(createNameFromRefUrl(entry)))(
          WebApiShapeParserContextAdapter(ctx))
          .parse()
          .map { s =>
            ctx.autoGeneratedAnnotation(s)
            payload.setWithoutId(PayloadModel.Schema, tracking(s, payload), Annotations(entry))
          }
      }
    )

    OasExamplesParser(map, payload)(WebApiShapeParserContextAdapter(ctx)).parse()
    payload.examples.foreach { ex =>
      payload.mediaType.option().foreach(mt => ex.set(ExampleModel.MediaType, mt, Annotations.synthesized()))
      ex.annotations += TrackedElement.fromInstance(payload)
    }

    // encoding
    map.key(
      "encoding",
      entry => {
        val encodings = OasEncodingParser(entry.value.as[YMap], payload.withEncoding).parse()
        payload.fields.setWithoutId(PayloadModel.Encoding,
                                    AmfArray(encodings, Annotations(entry.value)),
                                    Annotations(entry))
      }
    )

    AnnotationParser(payload, map)(WebApiShapeParserContextAdapter(ctx)).parse()

    ctx.closedShape(payload, map, "content")

    payload
  }

}
