package config

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import org.slf4j.LoggerFactory

/**
  * Created by joaquim.silveira on 17/05/16.
  */
trait GlobalErrorHandler {

  val log = LoggerFactory.getLogger(this.getClass)

  def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case ae: ArithmeticException =>
        extractUri { uri =>
          log.info(s"Request to $uri could not be handled normally")
          complete(HttpResponse(BadRequest, entity = "Bad numbers, bad result!!!"))
        }
      case re: RuntimeException =>
        extractRequestContext { ctx =>
          val uri = ctx.request.uri
          val ip = ctx.request.headers.find(ipF).map(_.value) match {
            case Some(x) => s" from client ip: $x"
            case None => ""
          }
          log.error(s"Error trying to access $uri$ip. Reason: ${re.getMessage}")
          if (log.isDebugEnabled) log.error("trace", re)
          complete(HttpResponse(status = InternalServerError, entity = "OMG!"))
        }
    }

  private def ipF(header: HttpHeader): Boolean = {
    header.name match {
      case "X-Forwarded-For" => true
      case "Remote-Address" => true
      case "X-Real-IP" => true
      case _ => false
    }
  }
}
