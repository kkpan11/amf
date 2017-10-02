package amf.metadata.shape

import amf.metadata.Field
import amf.metadata.Type.{Str, Int}
import amf.vocabulary.Namespace.{Shacl, Shapes}

trait CommonOASFields {

  val Pattern = Field(Str, Shacl + "pattern")

  val MinLength = Field(Int, Shacl + "minLength")

  val MaxLength = Field(Int, Shacl + "maxLength")

  val Minimum = Field(Str, Shacl + "minInclusive")

  val Maximum = Field(Str, Shacl + "maxInclusive")

  val ExclusiveMinimum = Field(Str, Shacl + "minExclusive")

  val ExclusiveMaximum = Field(Str, Shacl + "maxExclusive")

  val Format = Field(Str, Shapes + "format")

  val MultipleOf = Field(Int, Shapes + "multipleOf")

  val commonOASFields = List(Pattern,
    MinLength,
    MaxLength,
    Minimum,
    Maximum,
    ExclusiveMinimum,
    ExclusiveMaximum,
    Format,
    MultipleOf)
}
