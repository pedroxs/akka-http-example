package log

import akka.event.Logging

/**
  * Created by thiago on 5/17/16.
  */
object LogService {

  val possibleLevels: Seq[String] = Logging.AllLogLevels.map(logLevelToString).map(_.get)

  def logLevelToString(level: Logging.LogLevel): Option[String] = {
    level match {
      case Logging.ErrorLevel => Some("ErrorLevel(1)")
      case Logging.WarningLevel => Some("WarningLevel(2)")
      case Logging.InfoLevel => Some("InfoLevel(3)")
      case Logging.DebugLevel => Some("DebugLevel(4)")
      case _ => None
    }
  }
}
