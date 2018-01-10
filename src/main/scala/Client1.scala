import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.{Keep, Sink}
import akka.stream.{ActorMaterializer, KillSwitches, UniqueKillSwitch}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Client1 {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()



    val consumerSettings = ConsumerSettings(system, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("localhost:9092")
      .withGroupId("group11")
      .withClientId("client1")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    val subscription = Subscriptions.topics("test")

    val src =
      Consumer.committableSource(consumerSettings, subscription)
        .mapAsync(1) { x =>
          Future {
            println(x.record.value())
            x
          }
        }.mapAsync(1) { msg =>
        msg.committableOffset.commitScaladsl()
      }

    src
      .to(Sink.ignore)
      .run()


  }

}
