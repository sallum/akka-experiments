import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinPool

/**
 * Writer classes
 *
 * @author Ignacio Mulas
 */
class Writer(nrOfWorkers: Int) extends Actor {

  val writerRouter = context.actorOf(
    Props[HDFSWriter].withRouter(RoundRobinPool(nrOfWorkers)), name = "HDFSWriterRouter")

  def receive = {
    case Write(message)   => writerRouter ! Write(message)
    case Ready => println("I am done writing!")
    case _       => println("Oi oi oi, I did not understand you")
  }
}

class HDFSWriter extends Actor {
  def receive = {
    case Write(message) => println("I am a HDFSWriter and I can write now: " + message)
    case _       => println("huh?")
  }
}

