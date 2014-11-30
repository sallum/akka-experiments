import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

/**
 * Main
 *
 * @author Ignacio Mulas
 */
object Main extends App {

  val system = ActorSystem("ReaderWriterSystem")

  // default Actor constructor
  val readerActor = system.actorOf(Props(new Reader(3)), name = "reader")
  val writerActor = system.actorOf(Props(new Writer(3)), name = "writer")

  readerActor ! Read

}