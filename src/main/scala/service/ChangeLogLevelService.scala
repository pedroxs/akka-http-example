package service

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import json.MyJsonSupport
import log.LogService._
import log.{LogLevelReq, LogLevelResp}

/**
  * Created by joaquim.silveira on 17/05/16.
  */
trait ChangeLogLevelService extends MyJsonSupport {

  implicit val system: ActorSystem

  lazy val resp = LogLevelResp(logLevelToString(system.eventStream.logLevel), possibleLevels)

  val logLevelRoutes = {
    pathPrefix("log") {
      get {
        complete(ToResponseMarshallable(resp))
      } ~
      post {
        entity(as[LogLevelReq]) { req =>
          system.eventStream.setLogLevel(Logging.LogLevel.apply(req.level))
          complete(HttpResponse(StatusCodes.OK, entity = "level changed!"))
        }
      }
    }
  }
}