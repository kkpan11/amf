package amf.validation

import amf.core.remote.{Hint, OasJsonHint}

class OasInterSpecRefsReportTest extends UniquePlatformReportGenTest {

  override val basePath    = "file://amf-client/shared/src/test/resources/production/inter-spec-refs/"
  override val reportsPath = "amf-client/shared/src/test/resources/production/inter-spec-refs/reports/"

  test("Oas refs Raml datatype fragment") {
    validate("oas-raml-datatype/api.json", Some("oas-raml-datatype.report"))
  }

  test("Oas refs Raml security scheme fragment") {
    validate("oas-raml-securityScheme/api.json", Some("oas-raml-securityScheme.report"))
  }

  override val hint: Hint = OasJsonHint
}
