ModelId: file://amf-cli/shared/src/test/resources/validations/examples/external-json/api.raml
Profile: RAML 1.0
Conforms: false
Number of results: 1

Level: Violation

- Constraint: http://a.ml/vocabularies/amf/validation#example-validation-error
  Message: should have required property 'lastName'
  Severity: Violation
  Target: file://amf-cli/shared/src/test/resources/validations/examples/external-json/api.raml#/declares/shape/a/examples/example/default-example
  Property: file://amf-cli/shared/src/test/resources/validations/examples/external-json/api.raml#/declares/shape/a/examples/example/default-example
  Range: [(1,0)-(4,1)]
  Location: file://amf-cli/shared/src/test/resources/validations/examples/external-json/example.json
