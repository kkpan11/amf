package amf.apicontract.client.scala.model.domain.bindings.kafka

import amf.apicontract.client.scala.model.domain.bindings.{BindingVersion, ChannelBinding}
import amf.apicontract.internal.metamodel.domain.bindings.{
  KafkaChannelBinding030Model,
  KafkaChannelBinding040Model,
  KafkaChannelBindingModel,
  KafkaTopicConfigurationModel
}
import amf.apicontract.internal.metamodel.domain.bindings.KafkaChannelBindingModel._
import amf.core.client.scala.model.{IntField, StrField}
import amf.core.client.scala.model.domain.{DomainElement, Linkable}
import amf.core.internal.metamodel.{Field}
import amf.core.internal.parser.domain.{Annotations, Fields}
import amf.shapes.client.scala.model.domain.Key

abstract class KafkaChannelBinding(override val fields: Fields, override val annotations: Annotations)
    extends ChannelBinding
    with BindingVersion
    with Key {
  override protected def bindingVersionField: Field = BindingVersion
  override val key: StrField                        = fields.field(KafkaChannelBindingModel.key)
  override def componentId: String                  = "/kafka-channel"

  def topic: StrField      = fields.field(Topic)
  def partitions: IntField = fields.field(Partitions)
  def replicas: IntField   = fields.field(Replicas)

  def withTopic(topic: String): this.type        = set(Topic, topic)
  def withPartitions(partitions: Int): this.type = set(Partitions, partitions)
  def withReplicas(replicas: Int): this.type     = set(Replicas, replicas)
}

class KafkaChannelBinding030(override val fields: Fields, override val annotations: Annotations)
    extends KafkaChannelBinding(fields, annotations) {
  override def meta: KafkaChannelBinding030Model.type = KafkaChannelBinding030Model
  override def componentId: String                    = "/kafka-channel-030"
  override def linkCopy(): KafkaChannelBinding030     = KafkaChannelBinding030().withId(id)

  /** apply method for create a new instance with fields and annotations. Aux method for copy */
  override protected def classConstructor: (Fields, Annotations) => Linkable with DomainElement =
    KafkaChannelBinding030.apply
}

object KafkaChannelBinding030 {
  def apply(): KafkaChannelBinding030 = apply(Annotations())

  def apply(annotations: Annotations): KafkaChannelBinding030 = apply(Fields(), annotations)

  def apply(fields: Fields, annotations: Annotations): KafkaChannelBinding030 =
    new KafkaChannelBinding030(fields, annotations)
}

class KafkaChannelBinding040(override val fields: Fields, override val annotations: Annotations)
    extends KafkaChannelBinding(fields, annotations) {
  override def meta: KafkaChannelBinding040Model.type = KafkaChannelBinding040Model
  override def componentId: String                    = "/kafka-channel-040"
  override def linkCopy(): KafkaChannelBinding040     = KafkaChannelBinding040().withId(id)

  def topicConfiguration: KafkaTopicConfiguration = fields.field(KafkaChannelBinding040Model.TopicConfiguration)
  def withTopicConfiguration(topicConfiguration: KafkaTopicConfiguration): this.type =
    set(KafkaChannelBinding040Model.TopicConfiguration, topicConfiguration)

  /** apply method for create a new instance with fields and annotations. Aux method for copy */
  override protected def classConstructor: (Fields, Annotations) => Linkable with DomainElement =
    KafkaChannelBinding040.apply
}

object KafkaChannelBinding040 {
  def apply(): KafkaChannelBinding040 = apply(Annotations())

  def apply(annotations: Annotations): KafkaChannelBinding040 = apply(Fields(), annotations)

  def apply(fields: Fields, annotations: Annotations): KafkaChannelBinding040 =
    new KafkaChannelBinding040(fields, annotations)
}

class KafkaTopicConfiguration(override val fields: Fields, override val annotations: Annotations)
    extends DomainElement {
  override def meta: KafkaTopicConfigurationModel.type = KafkaTopicConfigurationModel
  override def componentId: String                     = "/kafka-topic-configuration"

  def cleanupPolicy: Seq[StrField] = fields.field(KafkaTopicConfigurationModel.CleanupPolicy)
  def retentionMs: IntField        = fields.field(KafkaTopicConfigurationModel.RetentionMs)
  def retentionBytes: IntField     = fields.field(KafkaTopicConfigurationModel.RetentionBytes)
  def deleteRetentionMs: IntField  = fields.field(KafkaTopicConfigurationModel.DeleteRetentionMs)
  def maxMessageBytes: IntField    = fields.field(KafkaTopicConfigurationModel.MaxMessageBytes)

  def withCleanupPolicy(cleanupPolicy: Seq[String]): this.type =
    set(KafkaTopicConfigurationModel.CleanupPolicy, cleanupPolicy)
  def withRetentionMs(retentionMs: Int): this.type =
    set(KafkaTopicConfigurationModel.RetentionMs, retentionMs)
  def withRetentionBytes(retentionBytes: Int): this.type =
    set(KafkaTopicConfigurationModel.RetentionBytes, retentionBytes)
  def withDeleteRetentionMS(deleteRetentionMS: Int): this.type =
    set(KafkaTopicConfigurationModel.DeleteRetentionMs, deleteRetentionMS)
  def withMaxMessageBytes(maxMessageBytes: Int): this.type =
    set(KafkaTopicConfigurationModel.MaxMessageBytes, maxMessageBytes)
}

object KafkaTopicConfiguration {
  def apply(): KafkaTopicConfiguration = apply(Annotations())

  def apply(annotations: Annotations): KafkaTopicConfiguration = apply(Fields(), annotations)

  def apply(fields: Fields, annotations: Annotations): KafkaTopicConfiguration =
    new KafkaTopicConfiguration(fields, annotations)
}
