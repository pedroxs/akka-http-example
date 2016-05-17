package service

import akka.event.{LoggingAdapter, NoLogging}
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.typesafe.config.Config
import model.Ping
import org.scalatest._

/**
  * Created by joaquim.silveira on 17/05/16.
  */
class PingServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with PingService {
  override def testConfigSource = "akka.loglevel = WARNING"
  override def config: Config = testConfig
  override val logger: LoggingAdapter = NoLogging

  val ping1 = Ping("0.0.0.0")

  "PingService" should "reply properly" in {
    Get(s"/ping/ip/0.0.0.0") ~> pingRoutes ~> check {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      //responseAs[Ping] shouldBe ping1  // how to do this?
      responseAs[String] should include("0.0.0.0")
    }
  }
}
