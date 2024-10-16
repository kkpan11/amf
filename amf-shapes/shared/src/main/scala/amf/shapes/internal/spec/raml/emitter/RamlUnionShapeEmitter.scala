package amf.shapes.internal.spec.raml.emitter

import amf.core.client.scala.model.document.BaseUnit
import amf.core.internal.render.SpecOrdering
import amf.core.internal.render.emitters.EntryEmitter
import amf.shapes.client.scala.model.domain.UnionShape
import amf.shapes.internal.spec.common.emitter.RamlShapeEmitterContext

case class RamlUnionShapeEmitter(shape: UnionShape, ordering: SpecOrdering, references: Seq[BaseUnit])(implicit
    spec: RamlShapeEmitterContext
) extends RamlAnyShapeEmitter(shape, ordering, references) {

  override def emitters(): Seq[EntryEmitter] = {
    // If anyOf is empty and inherits is not empty, the shape is still not resolved. So, emit as a AnyShape
    val unionEmitters =
      if (shape.anyOf.isEmpty && shape.inherits.nonEmpty) Nil
      else Seq(RamlAnyOfShapeEmitter(shape, ordering, references = references))
    super.emitters() ++ unionEmitters
  }

  override val typeName: Option[String] = Some("union")
}
