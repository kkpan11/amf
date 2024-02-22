package amf.apicontract.internal.spec.async.emitters.domain

import amf.apicontract.client.scala.model.domain.{EndPoint, Operation, Parameter}
import amf.apicontract.internal.metamodel.domain.EndPointModel
import amf.apicontract.internal.spec.oas.emitter.context.OasLikeSpecEmitterContext
import amf.apicontract.internal.spec.oas.emitter.domain.OasTagToReferenceEmitter
import org.mulesoft.common.client.lexical.Position
import amf.core.internal.parser.domain.FieldEntry
import amf.core.internal.render.BaseEmitters.{ValueEmitter, pos, traverse}
import amf.core.internal.render.SpecOrdering
import amf.core.internal.render.emitters.{EntryEmitter, PartEmitter}
import amf.shapes.internal.annotations.OrphanOasExtension
import org.mulesoft.common.client.lexical.Position.ZERO
import org.yaml.model.YDocument.{EntryBuilder, PartBuilder}
import org.yaml.model.{YDocument, YNode}

import scala.collection.mutable.ListBuffer

class AsyncApiEndpointsEmitter(f: FieldEntry, ordering: SpecOrdering)(implicit val spec: OasLikeSpecEmitterContext)
    extends EntryEmitter {

  val key = "channels"

  override def emit(b: YDocument.EntryBuilder): Unit = {
    val emitters =
      f.array.values.map(_.asInstanceOf[EndPoint]).map(e => new AsyncApiSingleEndpointEmitter(e, ordering))
    b.entry(
      key,
      _.obj(b => emitters.map(e => e.emit(b)))
    )
  }

  override def position(): Position = pos(f.element.annotations)
}

class AsyncApiSingleEndpointEmitter(channel: EndPoint, ordering: SpecOrdering)(implicit
    val spec: OasLikeSpecEmitterContext
) extends EntryEmitter
    with PartEmitter {

  override def emit(b: YDocument.EntryBuilder): Unit = {
    val channelPath = channel.path.value()
    b.entry(
      YNode(channelPath),
      emit(_)
    )
  }

  override def emit(b: YDocument.PartBuilder): Unit = {
    if (channel.isLink) {
      emitLink(b)
    } else {
      val result = ListBuffer[EntryEmitter]()
      val fs     = channel.fields
      val bindingOrphanAnnotations =
        channel.customDomainProperties.filter(_.extension.annotations.contains(classOf[OrphanOasExtension]))
      fs.entry(EndPointModel.Description).foreach(f => result += ValueEmitter("description", f))
      fs.entry(EndPointModel.Operations).foreach(f => result ++= operations(f))
      fs.entry(EndPointModel.Parameters)
        .foreach(f => result += new AsyncApiParametersEmitter(f.arrayValues[Parameter], ordering))
      fs.entry(EndPointModel.Bindings)
        .foreach(f => result += AsyncApiBindingsEmitter(f.value.value, ordering, bindingOrphanAnnotations))
      fs.entry(EndPointModel.Servers)
        .foreach(f => result += AsyncApiServersListEmitter(f, ordering))
      b.obj(traverse(ordering.sorted(result), _))
    }
  }

  def operations(f: FieldEntry): Seq[AsyncApiOperationEmitter] =
    f.arrayValues[Operation]
      .filter(e => e.method.value().matches("subscribe|publish"))
      .map(o => new AsyncApiOperationEmitter(o, ordering)(spec))

  def emitLink(b: PartBuilder): Unit = {
    OasTagToReferenceEmitter(channel).emit(b)
  }

  override def position(): Position = pos(channel.annotations)
}

case class AsyncApiChannelsDeclarationEmitter(key: String, channels: Seq[EndPoint], ordering: SpecOrdering)(implicit
    val spec: OasLikeSpecEmitterContext
) extends EntryEmitter {

  override def emit(b: EntryBuilder): Unit = {
    val namedChannelsEmitters =
      channels.map(c => new AsyncApiSingleEndpointEmitter(c, ordering))
    b.entry(
      key,
      _.obj(pb => namedChannelsEmitters.foreach(e => e.emit(pb)))
    )
  }

  override def position(): Position = channels.headOption.map(b => pos(b.annotations)).getOrElse(ZERO)
}
