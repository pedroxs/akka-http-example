package service

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import json.MyJsonSupport
import model.Ping

/**
  * Created by joaquim.silveira on 17/05/16.
  */
trait PingService extends MyJsonSupport {

  val pingRoutes = {
    logRequest("ping-service") {
      pathPrefix("ping") {
        get {
          path("ip" / Segment) { ip =>
            if(ip.length > 1) {
              complete(Ping(ip))
            } else {
              complete(HttpResponse(BadRequest, entity = "Missing IP Address"))
            }
          } ~
          pathSingleSlash {
            complete("pong!")
          }
        }
      }
    }
  }
}
