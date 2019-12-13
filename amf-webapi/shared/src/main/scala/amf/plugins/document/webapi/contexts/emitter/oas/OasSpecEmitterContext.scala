package amf.plugins.document.webapi.contexts.emitter.oas
import amf.core.emitter.BaseEmitters.MapEntryEmitter

import scala.collection.mutable
import amf.core.model.document.BaseUnit
import amf.core.model.domain.{Shape, DomainElement, Linkable}
import amf.core.parser.{FieldEntry, ErrorHandler}
import amf.plugins.domain.webapi.models.security.{SecurityRequirement, ParametrizedSecurityScheme}
import amf.plugins.domain.webapi.models.{Parameter, EndPoint, WebApi, Operation}
import amf.plugins.document.webapi.parser.{OasTypeDefStringValueMatcher, JsonSchemaTypeDefMatcher, CommonOasTypeDefMatcher}
import amf.plugins.document.webapi.parser.spec.domain.{Oas2ServersEmitter, Oas3EndPointServersEmitter, Oas3WebApiServersEmitter, ParametrizedSecuritySchemeEmitter, SecurityRequirementEmitter, OasParametrizedSecuritySchemeEmitter, Oas3OperationServersEmitter, OasHeaderEmitter, OasSecurityRequirementEmitter, OasServersEmitter}
import amf.plugins.document.webapi.contexts.{TagToReferenceEmitter, RefEmitter, SpecEmitterContext, SpecEmitterFactory}
import amf.core.model.domain.extensions.{ShapeExtension, CustomDomainProperty, DomainExtension}
import amf.core.emitter.{ShapeRenderOptions, SpecOrdering, EntryEmitter, PartEmitter}
import amf.core.remote.{Oas30, Oas20, Vendor}
import amf.plugins.document.webapi.parser.spec.declaration.{FacetsInstanceEmitter, OasFacetsInstanceEmitter, OasAnnotationTypeEmitter, OasDeclaredTypesEmitters, OasTagToReferenceEmitter, OasAnnotationEmitter, OasCustomFacetsEmitter, AnnotationTypeEmitter, AnnotationEmitter, CustomFacetsEmitter}
import org.yaml.model.YDocument.PartBuilder
import amf.core.utils.AmfStrings

abstract class OasSpecEmitterFactory(implicit val spec: OasSpecEmitterContext) extends SpecEmitterFactory {
  override def tagToReferenceEmitter: (DomainElement, Option[String], Seq[BaseUnit]) => TagToReferenceEmitter =
    OasTagToReferenceEmitter.apply

  override def customFacetsEmitter: (FieldEntry, SpecOrdering, Seq[BaseUnit]) => CustomFacetsEmitter =
    OasCustomFacetsEmitter.apply

  override def facetsInstanceEmitter: (ShapeExtension, SpecOrdering) => FacetsInstanceEmitter =
    OasFacetsInstanceEmitter.apply

  override def annotationEmitter: (DomainExtension, SpecOrdering) => AnnotationEmitter = OasAnnotationEmitter.apply

  override def securityRequirementEmitter: (SecurityRequirement, SpecOrdering) => SecurityRequirementEmitter =
    OasSecurityRequirementEmitter.apply

  override def parametrizedSecurityEmitter
  : (ParametrizedSecurityScheme, SpecOrdering) => ParametrizedSecuritySchemeEmitter =
    OasParametrizedSecuritySchemeEmitter.apply

  override def annotationTypeEmitter: (CustomDomainProperty, SpecOrdering) => AnnotationTypeEmitter =
    OasAnnotationTypeEmitter.apply

  def serversEmitter(api: WebApi, f: FieldEntry, ordering: SpecOrdering, references: Seq[BaseUnit]): OasServersEmitter

  def serversEmitter(operation: Operation,
                     f: FieldEntry,
                     ordering: SpecOrdering,
                     references: Seq[BaseUnit]): OasServersEmitter

  def serversEmitter(endpoint: EndPoint,
                     f: FieldEntry,
                     ordering: SpecOrdering,
                     references: Seq[BaseUnit]): OasServersEmitter

  def headerEmitter: (Parameter, SpecOrdering, Seq[BaseUnit]) => EntryEmitter = OasHeaderEmitter.apply

  override def declaredTypesEmitter: (Seq[Shape], Seq[BaseUnit], SpecOrdering) => EntryEmitter =
    OasDeclaredTypesEmitters.apply
}

case class Oas2SpecEmitterFactory(override val spec: OasSpecEmitterContext) extends OasSpecEmitterFactory()(spec) {
  override def serversEmitter(api: WebApi,
                              f: FieldEntry,
                              ordering: SpecOrdering,
                              references: Seq[BaseUnit]): Oas2ServersEmitter =
    Oas2ServersEmitter(api, f, ordering, references)(spec)

  override def serversEmitter(operation: Operation,
                              f: FieldEntry,
                              ordering: SpecOrdering,
                              references: Seq[BaseUnit]): Oas3OperationServersEmitter =
    Oas3OperationServersEmitter(operation, f, ordering, references)(spec)

  override def serversEmitter(endpoint: EndPoint,
                              f: FieldEntry,
                              ordering: SpecOrdering,
                              references: Seq[BaseUnit]): OasServersEmitter =
    Oas3EndPointServersEmitter(endpoint, f, ordering, references)(spec)
}

case class Oas3SpecEmitterFactory(override val spec: OasSpecEmitterContext) extends OasSpecEmitterFactory()(spec) {
  override def serversEmitter(api: WebApi,
                              f: FieldEntry,
                              ordering: SpecOrdering,
                              references: Seq[BaseUnit]): Oas3WebApiServersEmitter =
    Oas3WebApiServersEmitter(api, f, ordering, references)(spec)

  override def serversEmitter(operation: Operation,
                              f: FieldEntry,
                              ordering: SpecOrdering,
                              references: Seq[BaseUnit]): Oas3OperationServersEmitter =
    Oas3OperationServersEmitter(operation, f, ordering, references)(spec)

  override def serversEmitter(endpoint: EndPoint,
                              f: FieldEntry,
                              ordering: SpecOrdering,
                              references: Seq[BaseUnit]): OasServersEmitter =
    Oas3EndPointServersEmitter(endpoint, f, ordering, references)(spec)
}

abstract class OasSpecEmitterContext(eh: ErrorHandler,
                                     refEmitter: RefEmitter = OasRefEmitter,
                                     options: ShapeRenderOptions = ShapeRenderOptions())
  extends SpecEmitterContext(eh, refEmitter, options) {

  def schemasDeclarationsPath: String

  override def localReference(reference: Linkable): PartEmitter =
    factory.tagToReferenceEmitter(reference.asInstanceOf[DomainElement], reference.linkLabel.option(), Nil)

  val factory: OasSpecEmitterFactory
  val jsonPointersMap: mutable.Map[String, String] = mutable.Map() // id -> pointer

  val typeDefMatcher: OasTypeDefStringValueMatcher = CommonOasTypeDefMatcher

  val anyOfKey: String = "union".asOasExtension
}
final case class JsonSchemaEmitterContext(override val eh: ErrorHandler,
                                          override val options: ShapeRenderOptions = ShapeRenderOptions())
  extends Oas2SpecEmitterContext(eh = eh, options = options) {
  override val typeDefMatcher: OasTypeDefStringValueMatcher = JsonSchemaTypeDefMatcher

  override val anyOfKey: String = "anyOf"

  override def schemasDeclarationsPath: String = "/definitions/"
}

class Oas3SpecEmitterContext(eh: ErrorHandler,
                             refEmitter: RefEmitter = OasRefEmitter,
                             options: ShapeRenderOptions = ShapeRenderOptions())
  extends OasSpecEmitterContext(eh, refEmitter, options) {
  override val factory: OasSpecEmitterFactory  = Oas3SpecEmitterFactory(this)
  override val vendor: Vendor                  = Oas30
  override def schemasDeclarationsPath: String = "/components/schemas/"
}

class Oas2SpecEmitterContext(eh: ErrorHandler,
                             refEmitter: RefEmitter = OasRefEmitter,
                             options: ShapeRenderOptions = ShapeRenderOptions())
  extends OasSpecEmitterContext(eh, refEmitter, options) {
  override val factory: OasSpecEmitterFactory  = Oas2SpecEmitterFactory(this)
  override val vendor: Vendor                  = Oas20
  override def schemasDeclarationsPath: String = "/definitions/"
}

object OasRefEmitter extends RefEmitter {

  override def ref(url: String, b: PartBuilder): Unit = b.obj(MapEntryEmitter("$ref", url).emit(_))
}