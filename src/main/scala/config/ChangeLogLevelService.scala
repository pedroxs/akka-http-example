package config

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{StatusCodes, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.event.{Logging, LoggingAdapter}
import spray.json.DefaultJsonProtocol

/**
  * Created by joaquim.silveira on 17/05/16.
  */
trait ChangeLogLevelService extends DefaultJsonProtocol {
  implicit val system: ActorSystem
  val logger: LoggingAdapter

  case class LogLevelResp(currentLevel: Option[String], possibleLevels: Seq[String])
  case class LogLevelReq(level: Int)
  implicit val logLevelRespFormat = jsonFormat2(LogLevelResp.apply)
  implicit val logLevelReqFormat = jsonFormat1(LogLevelReq.apply)
  implicit val logLevelFormat = jsonFormat1(Logging.LogLevel.apply)

  val resp = LogLevelResp(None, Logging.AllLogLevels.map(logLevelToString).map(_.get))

  val logLevelRoutes = {
    pathPrefix("log") {
      get {
        //this line breaks IDE but compiles and works just fine...
        complete(resp.copy(currentLevel = logLevelToString(system.eventStream.logLevel)))
      } ~
      (post & entity(as[LogLevelReq])) { req =>
        system.eventStream.setLogLevel(Logging.LogLevel.apply(req.level))
        complete(HttpResponse(StatusCodes.OK, entity = "level changed!"))
      }
    }
  }

  def logLevelToString(level: Logging.LogLevel): Option[String] = {
    level match {
      case Logging.ErrorLevel => Some("ErrorLevel(1)")
      case Logging.WarningLevel => Some("WarningLevel(2)")
      case Logging.InfoLevel => Some("InfoLevel(3)")
      case Logging.DebugLevel => Some("DebugLevel(4)")
      case _ => None
    }
  }
}
