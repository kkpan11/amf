package amf.apicontract.internal.spec.oas

import amf.apicontract.internal.plugins.ApiElementRenderPlugin
import amf.apicontract.internal.spec.common.emitter.DomainElementEmitterFactory
import amf.apicontract.internal.spec.oas.emitter.domain.Oas30EmitterFactory
import amf.core.client.scala.errorhandling.AMFErrorHandler
import amf.core.internal.remote.SpecId

object Oas30ElementRenderPlugin extends ApiElementRenderPlugin {

  override protected def vendor: SpecId = SpecId.OAS30

  override protected def emitterFactory: AMFErrorHandler => DomainElementEmitterFactory = eh => Oas30EmitterFactory(eh)
}
