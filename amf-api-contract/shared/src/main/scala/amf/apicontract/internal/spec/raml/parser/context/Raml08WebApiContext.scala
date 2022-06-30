package amf.apicontract.internal.spec.raml.parser.context

import amf.apicontract.internal.spec.common.RamlWebApiDeclarations
import amf.apicontract.internal.spec.common.parser.{IgnoreCriteria}
import amf.core.client.scala.config.ParsingOptions
import amf.core.client.scala.parse.document.{ParsedReference, ParserContext}
import amf.core.internal.remote.{Raml08, Spec}
import amf.shapes.internal.spec.RamlWebApiContextType
import amf.shapes.internal.spec.RamlWebApiContextType.RamlWebApiContextType
import amf.shapes.internal.spec.common.parser.SpecSyntax

class Raml08WebApiContext(
    loc: String,
    refs: Seq[ParsedReference],
    override val wrapped: ParserContext,
    private val ds: Option[RamlWebApiDeclarations] = None,
    contextType: RamlWebApiContextType = RamlWebApiContextType.DEFAULT,
    options: ParsingOptions = ParsingOptions()
) extends RamlWebApiContext(loc, refs, options, wrapped, ds, contextType) {
  override val factory: RamlSpecVersionFactory = new Raml08VersionFactory()(this)
  override def spec: Spec                      = Raml08
  override def syntax: SpecSyntax              = Raml08Syntax

  override protected def clone(declarations: RamlWebApiDeclarations): RamlWebApiContext =
    new Raml08WebApiContext(loc, refs, wrapped, Some(declarations), options = options)

  override def ignoreCriteria: IgnoreCriteria = Raml08IgnoreCriteria
}
