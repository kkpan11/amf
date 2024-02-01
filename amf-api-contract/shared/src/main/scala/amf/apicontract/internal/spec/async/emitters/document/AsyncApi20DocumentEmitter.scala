package amf.apicontract.internal.spec.async.emitters.document

import amf.apicontract.client.scala.model.domain.Tag
import amf.apicontract.client.scala.model.domain.api.{Api, WebApi}
import amf.apicontract.internal.metamodel.domain.api.WebApiModel
import amf.apicontract.internal.spec.async.emitters.context.AsyncSpecEmitterContext
import amf.apicontract.internal.spec.async.emitters.domain.{AsyncApiCreativeWorksEmitter, AsyncApiEndpointsEmitter, AsyncApiServersEmitter, DefaultContentTypeEmitter}
import amf.apicontract.internal.spec.common.emitter
import amf.apicontract.internal.spec.common.emitter.{AgnosticShapeEmitterContextAdapter, SecurityRequirementsEmitter}
import amf.apicontract.internal.spec.oas.emitter.domain.{InfoEmitter, TagsEmitter}
import amf.core.client.scala.model.document.{BaseUnit, Document}
import amf.core.client.scala.model.domain.{AmfArray, AmfScalar}
import amf.core.internal.parser.domain.FieldEntry
import amf.core.internal.remote.{AsyncApi20, AsyncApi21, AsyncApi22, AsyncApi23, AsyncApi24, AsyncApi25, AsyncApi26, Spec}
import amf.core.internal.render.BaseEmitters.{EmptyMapEmitter, EntryPartEmitter, ValueEmitter, pos, traverse}
import amf.core.internal.render.SpecOrdering
import amf.core.internal.render.emitters.EntryEmitter
import amf.core.internal.validation.CoreValidations.{NotLinkable, TransformationValidation}
import amf.shapes.client.scala.model.domain.CreativeWork
import amf.shapes.internal.annotations.OrphanOasExtension
import amf.shapes.internal.spec.common.emitter.annotations.AnnotationsEmitter
import org.mulesoft.common.client.lexical.Position
import org.yaml.model.{YDocument, YNode, YScalar, YType}

import scala.collection.mutable

class AsyncApi20DocumentEmitter(document: BaseUnit)(implicit val specCtx: AsyncSpecEmitterContext) {

  protected implicit val shapeCtx = AgnosticShapeEmitterContextAdapter(specCtx)

  def emitWebApi(ordering: SpecOrdering): Seq[EntryEmitter] = {
    val model = retrieveWebApi()
    val spec  = document.sourceSpec
    val api   = WebApiEmitter(model, ordering, spec, Seq())
    api.emitters
  }

  private def retrieveWebApi(): Api = document match {
    case document: Document => document.encodes.asInstanceOf[Api]
    case _ =>
      specCtx.eh.violation(
        TransformationValidation,
        document.id,
        None,
        "BaseUnit doesn't encode a WebApi.",
        document.position(),
        document.location()
      )
      WebApi()
  }

  def emitDocument(): YDocument = {
    val doc = document.asInstanceOf[Document]

    val ordering = SpecOrdering.ordering(AsyncApi20, doc.sourceSpec)

//    val references = ReferencesEmitter(document, ordering)
    val declares =
      wrapDeclarations(AsyncDeclarationsEmitters(doc.declares, ordering, document.references).emitters, ordering)
    val api = emitWebApi(ordering)
//    val extension = extensionEmitter()
//    val usage: Option[ValueEmitter] =
//      doc.fields.entry(BaseUnitModel.Usage).map(f => ValueEmitter("usage".asOasExtension, f))

    YDocument {
      _.obj { b =>
        versionEntry(b)
//        traverse(ordering.sorted(api ++ extension ++ usage ++ declares :+ references), b)
        traverse(ordering.sorted(api ++ declares), b)
      }
    }
  }

  def wrapDeclarations(emitters: Seq[EntryEmitter], ordering: SpecOrdering): Seq[EntryEmitter] =
    Seq(emitter.DeclarationsEmitterWrapper(emitters, ordering))

  def versionEntry(b: YDocument.EntryBuilder): Unit = {
    val default = "2.6.0" // the default is the latest version to always emit a valid Async Api spec
    val versionToEmit = document.sourceSpec
      .map {
        case AsyncApi20 => "2.0.0"
        case AsyncApi21 => "2.1.0"
        case AsyncApi22 => "2.2.0"
        case AsyncApi23 => "2.3.0"
        case AsyncApi24 => "2.4.0"
        case AsyncApi25 => "2.5.0"
        case AsyncApi26 => "2.6.0"
        case _          => default
      }
      .getOrElse(default)

    b.asyncapi = YNode(YScalar(versionToEmit), YType.Str) // this should not be necessary but for use the same logic
  }

  case class WebApiEmitter(api: Api, ordering: SpecOrdering, spec: Option[Spec], references: Seq[BaseUnit]) {
    val emitters: Seq[EntryEmitter] = {
      val fs     = api.fields
      val result = mutable.ListBuffer[EntryEmitter]()

      fs.entry(WebApiModel.Identifier).foreach(f => result += ValueEmitter("id", f))

      val orphanAnnotationsFromInfo =
        api.customDomainProperties.filter(customProp => {
          val hasOrphanAnnotation = customProp.extension.annotations.contains(classOf[OrphanOasExtension])
          hasOrphanAnnotation && customProp.extension.annotations.find(classOf[OrphanOasExtension]).get.value == "info"
        })

      result += InfoEmitter(fs, ordering, orphanAnnotationsFromInfo)

      fs.entry(WebApiModel.Servers)
        .map(f => result += new AsyncApiServersEmitter(f, ordering))

      fs.entry(WebApiModel.Tags)
        .map(f => result += TagsEmitter("tags", f.array.values.asInstanceOf[Seq[Tag]], ordering))

      fs.entry(WebApiModel.Documentations)
        .map(f => result += new AsyncApiCreativeWorksEmitter(f.arrayValues[CreativeWork].head, ordering))

      fs.entry(WebApiModel.EndPoints) match {
        case Some(f: FieldEntry) => result += new AsyncApiEndpointsEmitter(f, ordering)
        case None                => result += EntryPartEmitter("channels", EmptyMapEmitter())
      }

      fs.entry(WebApiModel.Security).map(f => result += SecurityRequirementsEmitter("security", f, ordering))

      fs.entry(WebApiModel.ContentType).map(f => result += DefaultContentTypeEmitter(f, ordering))

      result ++= AnnotationsEmitter(api, ordering).emitters
      ordering.sorted(result)
    }
  }
}
