package service

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import model.Ping
import org.scalatest._

/**
  * Created by joaquim.silveira on 17/05/16.
  */
class PingServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with PingService {

  val ping1 = Ping("0.0.0.0")

  "PingService" should "reply properly" in {
    Get(s"/ping/ip/0.0.0.0") ~> pingRoutes ~> check {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[Ping] shouldBe ping1
      responseAs[String] should include("0.0.0.0")
    }
  }
}
