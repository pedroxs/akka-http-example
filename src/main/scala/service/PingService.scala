package service

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import com.typesafe.config.Config
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContextExecutor

/**
  * Created by joaquim.silveira on 17/05/16.
  */
trait PingService extends DefaultJsonProtocol {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: Materializer

  def config: Config
  val logger: LoggingAdapter

  //TODO: move this to a better place!
  implicit val pingFormat = jsonFormat1(Ping.apply)

  val pingRoutes = {
    logRequest("ping-service") {
      pathPrefix("ping") {
        path("ip" / Segment) { ip =>
          get {
            complete {
              if(ip.length > 1)
                Ping(ip)
              else
                StatusCodes.BadRequest -> "missing ip"
            }
          }
        } ~
        get {
          complete("pong!")
        }
      }
    }
  }
}
