package json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import log.{LogLevelReq, LogLevelResp}
import model.Ping
import spray.json._

/**
  * Created by thiago on 5/17/16.
  */
trait MyJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val logLevelReqFormat = jsonFormat1(LogLevelReq.apply)
  implicit val logLevelRespFormat = jsonFormat2(LogLevelResp.apply)
  implicit val pingFormat = jsonFormat1(Ping.apply)

}