ModelId: file://amf-cli/shared/src/test/resources/validations/jsonschema/misc_shapes.raml
Profile: RAML 1.0
Conforms: false
Number of results: 1

Level: Violation

- Constraint: http://a.ml/vocabularies/amf/validation#example-validation-error
  Message: should have required property 'emailAddresses'
  Severity: Violation
  Target: file://amf-cli/shared/src/test/resources/validations/jsonschema/misc_shapes.raml#/web-api/endpoint/%2Fep1/supportedOperation/get/returns/resp/200/payload/application%2Fjson/shape/schema/examples/example/default-example
  Property: file://amf-cli/shared/src/test/resources/validations/jsonschema/misc_shapes.raml#/web-api/endpoint/%2Fep1/supportedOperation/get/returns/resp/200/payload/application%2Fjson/shape/schema/examples/example/default-example
  Range: [(41,20)-(45,21)]
  Location: file://amf-cli/shared/src/test/resources/validations/jsonschema/misc_shapes.raml
