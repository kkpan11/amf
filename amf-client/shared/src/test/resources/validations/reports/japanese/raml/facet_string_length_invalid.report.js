Model: file://amf-client/shared/src/test/resources/validations/japanese/raml/facet_string_length_invalid.raml
Profile: RAML 1.0
Conforms? false
Number of results: 2

Level: Violation

- Source: http://a.ml/vocabularies/amf/validation#example-validation-error
  Message: should NOT be longer than 7 characters
  Level: Violation
  Target: file://amf-client/shared/src/test/resources/validations/japanese/raml/facet_string_length_invalid.raml#/declarations/types/scalar/TooLongEmail/example/default-example
  Property: file://amf-client/shared/src/test/resources/validations/japanese/raml/facet_string_length_invalid.raml#/declarations/types/scalar/TooLongEmail/example/default-example
  Position: Some(LexicalInformation([(6,13)-(6,21)]))
  Location: file://amf-client/shared/src/test/resources/validations/japanese/raml/facet_string_length_invalid.raml

- Source: http://a.ml/vocabularies/amf/validation#example-validation-error
  Message: should NOT be shorter than 10 characters
  Level: Violation
  Target: file://amf-client/shared/src/test/resources/validations/japanese/raml/facet_string_length_invalid.raml#/declarations/types/scalar/TooShortEmail/example/default-example
  Property: file://amf-client/shared/src/test/resources/validations/japanese/raml/facet_string_length_invalid.raml#/declarations/types/scalar/TooShortEmail/example/default-example
  Position: Some(LexicalInformation([(10,13)-(10,21)]))
  Location: file://amf-client/shared/src/test/resources/validations/japanese/raml/facet_string_length_invalid.raml