package amf.core.resolution.pipelines

import amf.ProfileNames
import amf.ProfileNames.ProfileName
import amf.core.model.document.BaseUnit
import amf.core.resolution.stages.{ReferenceResolutionStage, ResolutionStage}

class BasicResolutionPipeline(override val model: BaseUnit) extends ResolutionPipeline[BaseUnit] {
  val references                                     = new ReferenceResolutionStage(keepEditingInfo = false)
  override protected val steps: Seq[ResolutionStage] = Seq(references)
  override def profileName: ProfileName              = ProfileNames.AMF
}
