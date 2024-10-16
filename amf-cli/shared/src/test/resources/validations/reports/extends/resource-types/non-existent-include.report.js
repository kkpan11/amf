ModelId: file://amf-cli/shared/src/test/resources/validations/resource_types/non-existent-include.raml
Profile: 
Conforms: false
Number of results: 2

Level: Violation

- Constraint: http://a.ml/vocabularies/amf/core#declaration-not-found
  Message: ResourceType resourceTypes/nonexistent.raml not found
  Severity: Violation
  Target: 
  Property: 
  Range: [(4,15)-(4,54)]
  Location: file://amf-cli/shared/src/test/resources/validations/resource_types/non-existent-include.raml

- Constraint: http://a.ml/vocabularies/amf/core#unresolved-reference
  Message: File Not Found: ENOENT: no such file or directory, open 'amf-cli/shared/src/test/resources/validations/resource_types/resourceTypes/nonexistent.raml'
  Severity: Violation
  Target: resourceTypes/nonexistent.raml
  Property: 
  Range: [(4,15)-(4,54)]
  Location: file://amf-cli/shared/src/test/resources/validations/resource_types/non-existent-include.raml
