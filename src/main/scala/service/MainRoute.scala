package service

import akka.http.scaladsl.server.Directives._
import config.GlobalErrorHandler

/**
  * Created by thiago on 5/17/16.
  */
trait MainRoute
  extends GlobalErrorHandler
    with ChangeLogLevelService
    with PingService
    with HelloService {


  val routes = handleExceptions(myExceptionHandler) {
    helloRoutes ~
    pingRoutes ~
    logLevelRoutes
  }
}