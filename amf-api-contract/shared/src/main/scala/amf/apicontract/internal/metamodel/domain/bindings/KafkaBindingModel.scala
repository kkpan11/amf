package amf.apicontract.internal.metamodel.domain.bindings

import amf.apicontract.client.scala.model.domain.bindings.kafka._
import amf.core.client.scala.model.domain.AmfObject
import amf.core.client.scala.vocabulary.Namespace.ApiBinding
import amf.core.client.scala.vocabulary.ValueType
import amf.core.internal.metamodel.Field
import amf.core.internal.metamodel.Type.{Int, Str}
import amf.core.internal.metamodel.domain.{ModelDoc, ModelVocabularies, ShapeModel}

object KafkaOperationBindingModel extends OperationBindingModel with BindingVersion {
  val GroupId: Field =
    Field(
      ShapeModel,
      ApiBinding + "groupId",
      ModelDoc(ModelVocabularies.ApiBinding, "groupId", "Schema that defines the id of the consumer group")
    )

  val ClientId: Field =
    Field(
      ShapeModel,
      ApiBinding + "clientId",
      ModelDoc(
        ModelVocabularies.ApiBinding,
        "clientId",
        "Schema that defines the id of the consumer inside a consumer group"
      )
    )

  override def modelInstance: AmfObject = KafkaOperationBinding()

  override def fields: List[Field] = List(GroupId, ClientId, BindingVersion) ++ OperationBindingModel.fields

  override val key: Field = Type

  override val `type`: List[ValueType] = ApiBinding + "KafkaOperationBinding" :: OperationBindingModel.`type`

  override val doc: ModelDoc = ModelDoc(ModelVocabularies.ApiBinding, "KafkaOperationBinding")
}

object KafkaMessageBindingModel extends MessageBindingModel with BindingVersion {
  val MessageKey: Field =
    Field(
      ShapeModel,
      ApiBinding + "messageKey",
      ModelDoc(ModelVocabularies.ApiBinding, "key", "Schema that defines the message key")
    )

  override def modelInstance: AmfObject = KafkaMessageBinding()

  override def fields: List[Field] = List(MessageKey, BindingVersion) ++ MessageBindingModel.fields

  override val `type`: List[ValueType] = ApiBinding + "KafkaMessageBinding" :: MessageBindingModel.`type`

  override val key: Field = Type

  override val doc: ModelDoc = ModelDoc(ModelVocabularies.ApiBinding, "KafkaMessageBinding")
}

// added in binding version 0.3.0
object KafkaServerBindingModel extends ServerBindingModel with BindingVersion {
  val SchemaRegistryUrl: Field =
    Field(
      Str,
      ApiBinding + "schemaRegistryUrl",
      ModelDoc(
        ModelVocabularies.ApiBinding,
        "schemaRegistryUrl",
        "API URL for the Schema Registry used when producing Kafka messages (if a Schema Registry was used)"
      )
    )

  // TODO: MUST NOT be specified if `schemaRegistryUrl` is not specified
  val SchemaRegistryVendor: Field =
    Field(
      Str,
      ApiBinding + "schemaRegistryVendor",
      ModelDoc(
        ModelVocabularies.ApiBinding,
        "schemaRegistryVendor",
        "The vendor of Schema Registry and Kafka serdes library that should be used"
      )
    )

  override def modelInstance: AmfObject = KafkaServerBinding()

  override def fields: List[Field] =
    List(SchemaRegistryUrl, SchemaRegistryVendor, BindingVersion) ++ ServerBindingModel.fields

  override val `type`: List[ValueType] = ApiBinding + "KafkaServerBinding" :: ServerBindingModel.`type`

  override val key: Field = Type

  override val doc: ModelDoc = ModelDoc(ModelVocabularies.ApiBinding, "KafkaServerBinding")
}

// added in binding version 0.3.0
object KafkaChannelBindingModel extends ChannelBindingModel with BindingVersion {
  val Topic: Field =
    Field(
      Str,
      ApiBinding + "topic",
      ModelDoc(
        ModelVocabularies.ApiBinding,
        "topic",
        "Kafka topic name if different from channel name."
      )
    )

  // TODO: must be a positive number
  val Partitions: Field =
    Field(
      Int,
      ApiBinding + "partitions",
      ModelDoc(
        ModelVocabularies.ApiBinding,
        "partitions",
        "Number of partitions configured on this topic."
      )
    )

  // TODO: must be a positive number
  val Replicas: Field =
    Field(
      Int,
      ApiBinding + "replicas",
      ModelDoc(
        ModelVocabularies.ApiBinding,
        "replicas",
        "Number of replicas configured on this topic."
      )
    )

  override def modelInstance: AmfObject = KafkaChannelBinding()

  override def fields: List[Field] =
    List(Topic, Partitions, Replicas, BindingVersion) ++ ChannelBindingModel.fields

  override val `type`: List[ValueType] = ApiBinding + "KafkaChannelBinding" :: ChannelBindingModel.`type`

  override val key: Field = Type

  override val doc: ModelDoc = ModelDoc(ModelVocabularies.ApiBinding, "KafkaChannelBinding")
}
