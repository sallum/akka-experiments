import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinPool

/**
 * Reader Classes
 *
 * @author Ignacio Mulas
 */
class Reader(nrOfWorkers: Int) extends Actor {

  val readerRouter = context.actorOf(
    Props[ScreenReader].withRouter(RoundRobinPool(nrOfWorkers)), name = "ScreenReaderRouter")

  def receive = {
    case Read    => readerRouter ! Read
    case Ready(message) => context.actorSelection("../writer") ! Write(message)
    case _       => println("Oi oi oi, I did not understand you")
  }

}

class ScreenReader extends Actor {
  def receive = {
    case Read => readFromScreen
    case _    => println("huh?")
  }

  def readFromScreen = {
    for (message <- io.Source.stdin.getLines) {
      println("This is a screen reader, I will call the writer now!")
      sender ! Ready(message)
    }
  }
}