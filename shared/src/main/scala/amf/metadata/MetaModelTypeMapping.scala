package amf.metadata

import amf.document.{Document, Fragment, Module}
import amf.domain._
import amf.domain.`abstract`._
import amf.domain.dialects.DomainEntity
import amf.domain.extensions.{CustomDomainProperty, DataNode, DomainExtension}
import amf.domain.security._
import amf.metadata.document.{DocumentModel, FragmentsTypesModels, ModuleModel}
import amf.metadata.domain._
import amf.metadata.domain.`abstract`._
import amf.metadata.domain.dialects.DialectEntityModel
import amf.metadata.domain.extensions.{CustomDomainPropertyModel, DataNodeModel, DomainExtensionModel}
import amf.metadata.domain.security._
import amf.metadata.shape._
import amf.shape._

trait MetaModelTypeMapping {

  /** Metadata Type references. */
  protected def metaModel(instance: Any): Obj = instance match {
    case _: Document                           => DocumentModel
    case _: WebApi                             => WebApiModel
    case _: Organization                       => OrganizationModel
    case _: License                            => LicenseModel
    case _: CreativeWork                       => CreativeWorkModel
    case _: EndPoint                           => EndPointModel
    case _: Operation                          => OperationModel
    case _: Parameter                          => ParameterModel
    case _: Request                            => RequestModel
    case _: Response                           => ResponseModel
    case _: Payload                            => PayloadModel
    case _: UnionShape                         => UnionShapeModel
    case _: NodeShape                          => NodeShapeModel
    case _: ArrayShape                         => ArrayShapeModel
    case _: FileShape                          => FileShapeModel
    case _: ScalarShape                        => ScalarShapeModel
    case _: AnyShape                           => AnyShapeModel
    case _: NilShape                           => NilShapeModel
    case _: PropertyShape                      => PropertyShapeModel
    case _: SchemaShape                        => SchemaShapeModel
    case _: XMLSerializer                      => XMLSerializerModel
    case _: PropertyDependencies               => PropertyDependenciesModel
    case _: DomainExtension                    => DomainExtensionModel
    case _: CustomDomainProperty               => CustomDomainPropertyModel
    case _: DataNode                           => DataNodeModel
    case _: Module                             => ModuleModel
    case _: ResourceType                       => ResourceTypeModel
    case _: Trait                              => TraitModel
    case _: ParametrizedResourceType           => ParametrizedResourceTypeModel
    case _: ParametrizedTrait                  => ParametrizedTraitModel
    case _: Variable                           => VariableModel
    case _: VariableValue                      => VariableValueModel
    case _: ExternalDomainElement              => ExternalDomainElementModel
    case _: SecurityScheme                     => SecuritySchemeModel
    case _: OAuth1Settings                     => OAuth1SettingsModel
    case _: OAuth2Settings                     => OAuth2SettingsModel
    case _: ApiKeySettings                     => ApiKeySettingsModel
    case _: Settings                           => SettingsModel
    case _: Scope                              => ScopeModel
    case _: ParametrizedSecurityScheme         => ParametrizedSecuritySchemeModel
    case entity: DomainEntity                  => new DialectEntityModel(entity)
    case _: Fragment.ExternalFragment          => FragmentsTypesModels.ExternalFragmentModel
    case _: Fragment.DocumentationItem         => FragmentsTypesModels.DocumentationItemModel
    case _: Fragment.DataType                  => FragmentsTypesModels.DataTypeModel
    case _: Fragment.ResourceTypeFragment      => FragmentsTypesModels.ResourceTypeModel
    case _: Fragment.TraitFragment             => FragmentsTypesModels.TraitModel
    case _: Fragment.NamedExample              => FragmentsTypesModels.NamedExampleModel
    case _: Fragment.AnnotationTypeDeclaration => FragmentsTypesModels.AnnotationTypeDeclarationModel
    case _: Fragment.ExtensionFragment         => FragmentsTypesModels.ExtensionModel
    case _: Fragment.OverlayFragment           => FragmentsTypesModels.OverlayModel
    case _                                     => throw new Exception(s"Missing metadata mapping for $instance")
  }

}
