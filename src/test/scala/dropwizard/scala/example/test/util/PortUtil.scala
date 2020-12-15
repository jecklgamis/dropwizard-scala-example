package dropwizard.scala.example.test.util

import java.net.{ConnectException, Socket}

import scala.util.{Random, Try}

object PortUtil {

  def unused(): Int = {
    var unusedPort = nextPort
    var portAvail = false
    while (!portAvail) {
      Try {
        new Socket("127.0.0.1", unusedPort)
        portAvail = false
        unusedPort = nextPort
      }.recover {
        case e: ConnectException => portAvail = true
      }
    }
    unusedPort
  }

  private def nextPort: Int = 1025 + new Random(System.nanoTime()).nextInt(65535 - 1025)
}
