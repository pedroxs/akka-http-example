import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import service.MainRoute

/**
  * Created by joaquim.silveira on 17/05/16.
  */
object AkkaHttpEntryPoint extends App with MainRoute {

  val config = ConfigFactory.load()

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))
}
