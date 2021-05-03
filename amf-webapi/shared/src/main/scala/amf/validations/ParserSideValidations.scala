package amf.validations

import amf._
import amf.core.validation.SeverityLevels.{VIOLATION, WARNING}
import amf.core.validation.core.ValidationSpecification
import amf.core.validation.core.ValidationSpecification.PARSER_SIDE_VALIDATION
import amf.core.vocabulary.Namespace
import amf.core.vocabulary.Namespace.AmfParser
import amf.plugins.features.validation.Validations

// noinspection TypeAnnotation
object ParserSideValidations extends Validations {
  override val specification: String = PARSER_SIDE_VALIDATION
  override val namespace: Namespace  = AmfParser

  val NonEmptyBindingMap = validation(
    "non-empty-binding-map",
    "Reserved name binding must have an empty map"
  )

  val ExclusiveLinkTargetError = validation(
    "exclusive-link-target-error",
    "operationRef and operationId are mutually exclusive in a OAS 3.0.0 Link Object"
  )

  val MandatoryObjectNodeType = validation(
    "mandatory-object-node-type",
    "Mandatory object node type"
  )

  val InvalidSecuritySchemeType = validation(
    "invalid-security-scheme-type",
    "Invalid security scheme type"
  )

  val InvalidDatetimeFormat = validation(
    "invalid-datetime-format",
    "Invalid format value for datetime"
  )

  val InvalidBasePath = validation(
    "invalid-base-path",
    "Invalid base path"
  )

  val InvalidBaseUriParametersType = validation(
    "invalid-base-uri-parameters-type",
    "Invalid baseUriParameters type"
  )
  // Used also in resolution
  val UnusedBaseUriParameter = validation(
    "unused-base-uri-parameter",
    "Unused base uri parameter"
  )

  val ParametersWithoutBaseUri = validation(
    "parameters-without-base-uri",
    "'baseUri' not defined and 'baseUriParameters' defined."
  )

  val ImplicitVersionParameterWithoutApiVersion = validation(
    "implicit-version-parameter-without-api-version",
    "Base uri has 'version' parameter but the API doesn't define a version"
  )

  val InvalidVersionBaseUriParameterDefinition = validation(
    "invalid-version-base-uri-parameter-definition",
    "'version' baseUriParameter can't be defined if present in baseUri as variable"
  )

  val ParameterNameRequired = validation(
    "parameter-name-required",
    "Parameter name is required"
  )

  val RequestBodyContentRequired = validation(
    "content-required",
    "Request body content is required"
  )

  val InvalidServerPath = validation(
    "invalid-server-path",
    "Invalid server path"
  )

  val InvalidAbstractDeclarationParameterInType = validation(
    "invalid-abstract-declaration-parameter-in-type",
    "Trait/Resource Type parameter in type"
  )

  val InvalidSecuredByType = validation(
    "invalid-secured-by-type",
    "Invalid 'securedBy' type"
  )

  val ScopeNamesMustBeEmpty = validation(
    "scope-names-must-be-empty",
    "Scope names must be an empty array"
  )

  val MandatoryPathsProperty = validation(
    "mandatory-paths-property",
    "Paths property must be declared"
  )

  val MandatoryChannelsProperty = validation(
    "mandatory-channels-property",
    "Channels property must be declared"
  )

  val InvalidSecuritySchemeDescribedByType = validation(
    "invalid-security-scheme-described-by-type",
    "Invalid 'describedBy' type, map expected"
  )

  val ExpectedReference = validation(
    "expected-reference",
    "Expected reference"
  )

  val InvalidTagType = validation(
    "invalid-tag-type",
    "Tag values must be of type string"
  )

  val InvalidSecuritySchemeObject = validation(
    "invalid-security-scheme-object",
    "Invalid security scheme"
  )

  val InvalidSecurityRequirementObject = validation(
    "invalid-security-requirement-object",
    "Invalid security requirement object"
  )

  val InvalidSecurityRequirementsSeq = validation(
    "invalid-security-requirements-sequence",
    "'security' must be an array of security requirements object"
  )

  val InvalidEndpointPath = validation(
    "invalid-endpoint-path",
    "Invalid endpoint path (invalid template uri)"
  )

  val DuplicatedEndpointPath = validation(
    "duplicated-endpoint-path",
    "Duplicated endpoint path"
  )

  val DuplicatedOperationId = validation(
    "duplicated-operation-id",
    "Duplicated operation id"
  )

  val SchemaDeprecated = validation(
    "schema-deprecated",
    "'schema' keyword it's deprecated for 1.0 version, should use 'type' instead"
  )

  val SchemasDeprecated = validation(
    "schemas-deprecated",
    "'schemas' keyword it's deprecated for 1.0 version, should use 'types' instead"
  )

  val InvalidOperationType = validation(
    "invalid-operation-type",
    "Invalid operation type"
  )

  val InvalidExternalTypeType = validation(
    "invalid-external-type-type",
    "Invalid external type type"
  )

  val InvalidXmlSchemaType = validation(
    "invalid-xml-schema-type",
    "Invalid xml schema type"
  )

  val InvalidJsonSchemaExpression = validation(
    "invalid-json-schema-expression",
    "Invalid json schema expression"
  )

  val InvalidPropertyType = validation(
    "invalid-property-type",
    "Invalid property key type. Should be string"
  )

  val UnableToParseArray = validation(
    "unable-to-parse-array",
    "Unable to parse array definition"
  )

  val InvalidDecimalPoint = validation(
    "invalid-decimal-point",
    "Invalid decimal point"
  )

  val MissingOAuthFlowField = validation(
    "missing-oauth-flow-field",
    "Missing mandatory property for declared OAuth flow"
  )

  val NullAbstractDeclaration = validation(
    "null-abstract-declaration",
    "Generating abstract declaration (resource type / trait)  with null value"
  )

  val InvalidAbstractDeclarationType = validation(
    "invalid-abstract-declaration-type",
    "Invalid type for declaration node"
  )

  val InvalidJsonSchemaVersion = validation(
    "invalid-json-schema-version",
    "Invalid Json Schema version"
  )

  val CrossSecurityWarningSpecification = validation(
    "cross-security-warning",
    "Using a security scheme type from raml in oas or from oas in raml"
  )

  val ChainedReferenceSpecification = validation(
    "chained-reference-error",
    "References cannot be chained"
  )

  val UnableToSetDefaultType = validation(
    "unable-to-set-default-type",
    "Unable to set default type"
  )

  val ExclusiveSchemaType = validation(
    "exclusive-schema-type",
    "'schema' and 'type' properties are mutually exclusive"
  )

  val ExclusiveSchemasType = validation(
    "exclusive-schemas-type",
    "'schemas' and 'types' properties are mutually exclusive"
  )

  val PathTemplateUnbalancedParameters = validation(
    "path-template-unbalanced-parameters",
    "Nested parameters are not allowed in path templates"
  )

  val OasBodyAndFormDataParameterSpecification = validation(
    "oas-not-body-and-form-data-parameters",
    "Operation cannot have a body parameter and a formData parameter"
  )

  val OasInvalidBodyParameter = validation(
    "oas-invalid-body-parameter",
    "Only one body parameter is allowed"
  )

  val DuplicatedParameters = validation(
    "duplicate-parameters",
    "Sibling parameters must have unique 'name' and 'in' values"
  )

  val DuplicatedTags = validation(
    "duplicate-tags",
    "Sibling tags must have unique names"
  )

  val OasInvalidParameterBinding = validation(
    "oas-invalid-parameter-binding",
    "Parameter has invalid binding"
  )

  val OasInvalidParameterSchema = validation(
    "oas-invalid-parameter-binding",
    "Schema is required for a parameter in body"
  )

  val OasFormDataNotFileSpecification = validation(
    "oas-file-not-form-data-parameters",
    "Parameters with type file must be in formData"
  )

  // Used also in validation
  val UnsupportedExampleMediaTypeErrorSpecification = validation(
    "unsupported-example-media-type",
    "Cannot validate example with unsupported media type"
  )

  val UnknownSecuritySchemeErrorSpecification = validation(
    "unknown-security-scheme",
    "Cannot find the security scheme"
  )

  val MissingSecuritySchemeErrorSpecification = validation(
    "missing-security-scheme-type",
    "Missing security scheme type"
  )

  val UnknownScopeErrorSpecification = validation(
    "unknown-scope",
    "Cannot find the scope in the security settings"
  )

  val DuplicatedPropertySpecification = validation(
    "duplicated-property",
    "Duplicated property in node"
  )

  val UnexpectedFileTypesSyntax = validation(
    "unexpected-file-types-syntax",
    "Unexpected 'fileTypes' syntax. Options are string or sequence"
  )

  val JsonSchemaInheritanceWarning = validation(
    "json-schema-inheritance",
    "Inheriting from JSON Schema"
  )

  val XmlSchemaInheritancceWarning = validation(
    "xml-schema-inheritance",
    "Inheriting from XML Schema"
  )

  val InvalidEndpointType = validation(
    "invalid-endpoint-type",
    "Invalid endpoint type"
  )

  val InvalidParameterType = validation(
    "invalid-parameter-type",
    "Invalid parameter type"
  )

  val UnableToParseShape = validation(
    "unable-to-parse-shape",
    "Unable to parse shape"
  )

  val JsonSchemaFragmentNotFound = validation(
    "json-schema-fragment-not-found",
    "Json schema fragment not found"
  )

  val PatternPropertiesOnClosedNodeSpecification = validation(
    "pattern-properties-on-closed-node",
    "Closed node cannot define pattern properties"
  )

  val DiscriminatorOnExtendedUnionSpecification = validation(
    "discriminator-on-extended-union",
    "Property 'discriminator' not supported in a node extending a unionShape"
  )

  val UnresolvedParameter = validation(
    "unresolved-parameter",
    "Unresolved parameter"
  )

  val UnableToParseJsonSchema = validation(
    "unable-to-parse-json-schema",
    "Unable to parse json schema"
  )

  val MalformedJsonReference = validation(
    "malformed-json-reference",
    "Malformed json reference"
  )

  val InvalidAnnotationType = validation(
    "invalid-annotation-type",
    "Invalid annotation type"
  )

  val InvalidParameterStyleBindingCombination = validation(
    "invalid-parameter-style-binding-combination",
    "Invalid parameter style binding combination"
  )

  val InvalidTypesType = validation(
    "invalid-types-type",
    "Invalid types type"
  )

  val InvalidOAuth2FlowName = validation(
    "invalid-oauth2-flow-name",
    "Invalid OAuth2 flow name"
  )

  val InvalidDocumentationType = validation(
    "invalid-documentation-type",
    "Invalid documentation type"
  )

  val InvalidAllowedTargetsType = validation(
    "invalid-allowed-targets-type",
    "Invalid allowedTargets type"
  )

  val InvalidAllowedTargets = validation(
    "invalid-allowed-targets",
    "Invalid allowedTargets value"
  )

  val InvalidExtensionsType = validation(
    "invalid-extension-type",
    "Invalid extension type"
  )

  val ModuleNotFound = validation(
    "module-not-found",
    "Module not found"
  )

  val InvalidModuleType = validation(
    "invalid-module-type",
    "Invalid module type"
  )

  val ClosedShapeSpecification = validation(
    "closed-shape",
    "Invalid property for node"
  )

  val ClosedShapeSpecificationWarning = validation(
    "closed-shape-warning",
    "Invalid property for node"
  )

  val InvalidTypeExpression = validation(
    "invalid-type-expression",
    "Invalid type expression"
  )

  val UnexpectedReference = validation(
    "unexpected-reference",
    "Unexpected reference"
  )

  val MissingDiscriminatorProperty = validation(
    "missing-discriminator-property",
    "Type is missing property marked as discriminator"
  )

  val InvalidPayload = validation(
    "invalid-payload",
    "Invalid payload"
  )

  val InvalidValueInPropertiesFacet = validation(
    "invalid-value-in-properties-facet",
    "Properties facet must be a map of key and values"
  )

  val InvalidComponents = validation("invalid-components", "Components facet must be a map")

  val InvalidUserDefinedFacetName = validation(
    "invalid-user-defined-facet-name",
    "User defined facets must not begin with open parenthesis"
  )

  val InvalidFieldNameInComponents = validation(
    "invalid-field-name-in-components",
    "Field name in components must match the following expression: ^[a-zA-Z0-9\\.\\-_]+$"
  )

  val ParameterMissingSchemaOrContent = validation(
    "parameter-missing-schema-or-content",
    "Parameter must define a 'schema' or 'content' field, but not both"
  )

  val ServerVariableMissingDefault = validation(
    "server-variable-missing-default",
    "Server variable must define a 'default' field"
  )

  val InvalidEndpointDeclaration = validation("invalid-endpoint-declaration", "Invalid endpoint declaration")

  val SlashInUriParameterValues = validation(
    "slash-in-uri-parameter-value",
    "Values of uri parameter must not contain '/' character"
  )

  // TODO: Should be removed and used the violation in the next major
  val ItemsFieldRequiredWarning = validation(
    "items-field-required-warning",
    "'items' field is required when type is array"
  )

  // TODO: Should be removed and used the violation in the next major
  val invalidExampleFieldWarning = validation(
    "invalid-example-field-warning",
    "Property 'example' not supported"
  )

  val InvalidIdentifier = validation(
    "invalid-identifier",
    "'id' must be a string"
  )

  val MissingParameterType = validation(
    "missing-parameter-type",
    "Missing parameter type"
  )

  val InvalidStatusCode = validation(
    "invalid-status-code",
    "Status code for a Response object must be a string"
  )

  val UnknownYamlTag = validation(
    "unknown-yaml-tag",
    "Unknown tag detected, must be allowed by json schema ruleset"
  )

  val MissingRequiredFieldForGrantType = validation(
    "missing-field-oauth-2-grant-type",
    "Missing required field for given grant type"
  )

  val HeaderMustBeObject = validation(
    "header-must-be-object",
    "Message header must be of type object"
  )

  override val levels: Map[String, Map[ProfileName, String]] = Map(
    ExclusiveLinkTargetError.id -> all(VIOLATION),
    OasBodyAndFormDataParameterSpecification.id -> Map(
      Oas20Profile -> VIOLATION
    ),
    OasInvalidBodyParameter.id    -> all(VIOLATION),
    OasInvalidParameterBinding.id -> all(VIOLATION),
    OasFormDataNotFileSpecification.id -> Map(
      Oas20Profile -> VIOLATION
    ),
    JsonSchemaInheritanceWarning.id -> all(WARNING),
    PatternPropertiesOnClosedNodeSpecification.id -> Map(
      Raml10Profile -> VIOLATION,
      Raml08Profile -> VIOLATION,
      Oas20Profile  -> WARNING,
      Oas30Profile  -> WARNING,
      AmfProfile    -> WARNING
    ),
    DiscriminatorOnExtendedUnionSpecification.id -> Map(
      Raml10Profile -> VIOLATION,
      Raml08Profile -> VIOLATION,
      Oas20Profile  -> WARNING,
      Oas30Profile  -> WARNING,
      AmfProfile    -> WARNING
    ),
    ItemsFieldRequiredWarning.id                 -> all(WARNING), // TODO: should be violation
    NullAbstractDeclaration.id                   -> all(WARNING),
    SchemaDeprecated.id                          -> all(WARNING),
    SchemasDeprecated.id                         -> all(WARNING),
    UnusedBaseUriParameter.id                    -> all(WARNING),
    CrossSecurityWarningSpecification.id         -> all(WARNING),
    MissingRequiredFieldForGrantType.id          -> all(WARNING),
    invalidExampleFieldWarning.id                -> all(WARNING), // TODO: should be violation
    OasInvalidParameterSchema.id                 -> all(WARNING), // TODO: should be violation
    InvalidAllowedTargets.id                     -> all(WARNING), // TODO: should be violation
    MissingDiscriminatorProperty.id              -> all(VIOLATION),
    InvalidPayload.id                            -> all(VIOLATION),
    ClosedShapeSpecificationWarning.id           -> all(WARNING),
    ImplicitVersionParameterWithoutApiVersion.id -> all(WARNING), // TODO: should be violation
    InvalidVersionBaseUriParameterDefinition.id  -> all(WARNING), // TODO: should be violation
    HeaderMustBeObject.id                        -> Map(Async20Profile -> VIOLATION),
  )

  override val validations: List[ValidationSpecification] = List(
    ChainedReferenceSpecification,
    PathTemplateUnbalancedParameters,
    UnknownSecuritySchemeErrorSpecification,
    MissingSecuritySchemeErrorSpecification,
    UnknownScopeErrorSpecification,
    JsonSchemaInheritanceWarning,
    XmlSchemaInheritancceWarning,
    DuplicatedPropertySpecification,
    UnsupportedExampleMediaTypeErrorSpecification,
    OasInvalidBodyParameter,
    DuplicatedParameters,
    DuplicatedTags,
    PatternPropertiesOnClosedNodeSpecification,
    DiscriminatorOnExtendedUnionSpecification,
    OasFormDataNotFileSpecification,
    OasBodyAndFormDataParameterSpecification,
    OasInvalidParameterBinding,
    UnableToParseJsonSchema,
    InvalidAbstractDeclarationType,
    NullAbstractDeclaration,
    UnableToSetDefaultType,
    UnableToParseArray,
    InvalidDecimalPoint,
    InvalidPropertyType,
    JsonSchemaFragmentNotFound,
    InvalidJsonSchemaExpression,
    InvalidXmlSchemaType,
    InvalidExternalTypeType,
    InvalidAbstractDeclarationParameterInType,
    ExclusiveSchemaType,
    SchemaDeprecated,
    UnresolvedParameter,
    ParameterNameRequired,
    RequestBodyContentRequired,
    InvalidSecuredByType,
    ScopeNamesMustBeEmpty,
    InvalidSecuritySchemeDescribedByType,
    InvalidEndpointPath,
    DuplicatedEndpointPath,
    DuplicatedOperationId,
    InvalidOperationType,
    InvalidServerPath,
    ParametersWithoutBaseUri,
    UnusedBaseUriParameter,
    InvalidBaseUriParametersType,
    InvalidBasePath,
    InvalidParameterType,
    InvalidJsonSchemaVersion,
    InvalidEndpointType,
    UnableToParseShape,
    InvalidAnnotationType,
    InvalidTypesType,
    SchemasDeprecated,
    ExclusiveSchemasType,
    InvalidDocumentationType,
    InvalidAllowedTargetsType,
    InvalidTypeExpression,
    InvalidExtensionsType,
    ModuleNotFound,
    UnexpectedReference,
    UnexpectedFileTypesSyntax,
    CrossSecurityWarningSpecification,
    MissingDiscriminatorProperty,
    InvalidPayload,
    InvalidValueInPropertiesFacet,
    InvalidUserDefinedFacetName,
    InvalidFieldNameInComponents,
    ParameterMissingSchemaOrContent,
    ServerVariableMissingDefault,
    SlashInUriParameterValues,
    InvalidDatetimeFormat,
    InvalidTagType,
    InvalidIdentifier,
    InvalidComponents,
    InvalidStatusCode,
    HeaderMustBeObject,
    InvalidModuleType,
    ClosedShapeSpecification,
    ClosedShapeSpecificationWarning
  )
}
