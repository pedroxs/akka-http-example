package service

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.server.Directives._
import com.typesafe.config.Config

import scala.concurrent.ExecutionContextExecutor


/**
  * Created by joaquim.silveira on 17/05/16.
  */
trait HelloService {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor

  def config: Config
  val logger: LoggingAdapter

  val helloRoutes = {
    pathPrefix("hello") {
      path("world") {
        get {
          complete("Hello World!")
        }
      } ~
      path("ops") {
        get {
          sys.error("kaput!")
        }
      }
    }
  }
}
