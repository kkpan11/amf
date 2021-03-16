package amf.plugins.document.webapi

import amf.core.Root
import amf.core.errorhandling.ErrorHandler
import amf.core.parser.{ParserContext, Reference}
import amf.core.remote.Vendor
import amf.plugins.features.validation.CoreValidations.InvalidCrossSpec

trait CrossSpecRestriction {

  protected def vendors: Seq[String]
  protected def validVendorsToReference: Seq[Vendor] = Seq.empty

  // TODO: all documents should have a Vendor
  protected def restrictCrossSpecReferences(optionalReferencedVendor: Option[Vendor], reference: Reference)(
      implicit errorHandler: ErrorHandler): Unit = {
    val possibleReferencedVendors = vendors ++ validVendorsToReference.map(_.name)
    optionalReferencedVendor.foreach { referencedVendor =>
      if (!possibleReferencedVendors.contains(referencedVendor.name)) {
        referenceNodes(reference).foreach(node =>
          errorHandler.violation(InvalidCrossSpec, "", "Cannot reference fragments of another spec", node))
      }
    }
  }

  protected def restrictCrossSpecReferences(document: Root, ctx: ParserContext): Unit = {
    document.references.foreach { r =>
      restrictCrossSpecReferences(r.unit.sourceVendor, r.origin)(ctx.eh)
    }
  }

  private def referenceNodes(reference: Reference) = reference.refs.map(_.node)
}
