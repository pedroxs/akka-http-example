package service

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._

/**
  * Created by joaquim.silveira on 17/05/16.
  */
trait HelloService {

  val helloRoutes = {
    pathPrefix("hello") {
      get {
        path("world") {
          complete("Hello World!")
        } ~
        path("ops") {
          sys.error("kaput!") // this demostrates functionality of GlobalErrorHandler
          //complete(HttpResponse(BadRequest, entity = "kaput!"))
        }
      }
    }
  }
}