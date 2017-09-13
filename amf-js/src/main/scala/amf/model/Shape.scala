package amf.model

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExportAll

@JSExportAll
abstract class Shape(private[amf] val shape: amf.shape.Shape) {

  val name: String                    = shape.name
  val displayName: String             = shape.displayName
  val description: String             = shape.description
  val default: String                 = shape.default
  val values: js.Iterable[String]     = shape.values.toJSArray
  val documentation: CreativeWork     = Option(shape.documentation).map(amf.model.CreativeWork).orNull
  val xmlSerialization: XMLSerializer = Option(shape.xmlSerialization).map(amf.model.XMLSerializer).orNull

  def withName(name: String): this.type = {
    shape.withName(name)
    this
  }
  def withDisplayName(name: String): this.type = {
    shape.withDisplayName(name)
    this
  }
  def withDescription(description: String): this.type = {
    shape.withDescription(description)
    this
  }
  def withDefault(default: String): this.type = {
    shape.withDefault(default)
    this
  }
  def withValues(values: js.Iterable[String]): this.type = {
    shape.withValues(values.toList)
    this
  }
  def withDocumentation(documentation: CreativeWork): this.type = {
    shape.withDocumentation(documentation.element)
    this
  }

  def withXMLSerialization(xmlSerialization: XMLSerializer): this.type = {
    shape.withXMLSerialization(xmlSerialization.xmlSerializer)
    this
  }
}

object Shape {
  def apply(shape: amf.shape.Shape): Shape =
    (shape match {
      case node: amf.shape.NodeShape     => Some(NodeShape(node))
      case scalar: amf.shape.ScalarShape => Some(ScalarShape(scalar))
      case array: amf.shape.ArrayShape   => Some(new ArrayShape(array))
      case matrix: amf.shape.MatrixShape => Some(new MatrixShape(matrix.toArrayShape))
      case tuple: amf.shape.TupleShape   => Some(new TupleShape(tuple))
      case a                             => None
    }).orNull
}
