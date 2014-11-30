
/**
 * IO Messages
 *
 * @author Ignacio Mulas
 */
sealed trait IOMessage
case class Write(message: String) extends IOMessage
case object Read extends IOMessage
case class Ready(message: String) extends IOMessage