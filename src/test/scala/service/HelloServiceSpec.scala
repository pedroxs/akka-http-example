package service

import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest._

/**
  * Created by joaquim.silveira on 17/05/16.
  */
class HelloServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with HelloService {

  "HelloService" should "return 'Hello World'" in {
    Get(s"/hello/world") ~> helloRoutes ~> check {
      status shouldBe StatusCodes.OK
      contentType shouldBe `text/plain(UTF-8)`
      responseAs[String] shouldBe "Hello World!"
    }
  }
}
