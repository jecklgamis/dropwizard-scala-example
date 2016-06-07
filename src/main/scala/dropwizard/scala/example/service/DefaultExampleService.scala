package dropwizard.scala.example.service

import org.slf4j.LoggerFactory

class DefaultExampleService extends ExampleService {
  private val log = LoggerFactory.getLogger(classOf[DefaultExampleService])

  override def someServiceMethod(): Unit = {
    log.info("Hello")
  }
}


