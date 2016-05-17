import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import service.{HelloService, PingService}
import akka.http.scaladsl.server.Directives._

/**
  * Created by joaquim.silveira on 17/05/16.
  */
object AkkaHttpEntryPoint extends App with PingService with HelloService {
  override implicit val system = ActorSystem()
  override implicit def executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  override def config = ConfigFactory.load()
  override val logger = Logging(system, getClass)

  val routes: Route = helloRoutes ~ pingRoutes

  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))
}
