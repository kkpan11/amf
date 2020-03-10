package amf.plugins.document.webapi.parser.spec.domain

import amf.core.annotations.{AutoGeneratedName, DeclaredElement}
import amf.core.model.domain.Shape

object ParsingHelpers {

  def autoGeneratedAnnotation(s: Shape): Unit = {
    if (!linkToDeclared(s) && !simpleInheritsLink(s))
      s.add(AutoGeneratedName())
  }

  private def simpleInheritsLink(s: Shape): Boolean = {
    s.inherits match {
      case Seq(simple) => simple.isLink || simple.annotations.contains(classOf[DeclaredElement])
      case _           => false
    }
  }

  private def linkToDeclared(s: Shape): Boolean = {
    s.isLink && s.annotations.contains(classOf[DeclaredElement])
  }
}
