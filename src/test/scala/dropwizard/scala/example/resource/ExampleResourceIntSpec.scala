package dropwizard.scala.example.resource

import javax.ws.rs.core.MediaType.{APPLICATION_JSON, APPLICATION_XML}

import com.google.common.io.Resources
import dropwizard.scala.example.DropwizardAppRuleHelper.withAppRunning
import dropwizard.scala.example.ExampleApp
import org.scalatest.{FunSpec, Matchers}

class ExampleResourceIntSpec extends FunSpec with Matchers {
  describe("/scala endpoint") {
    it("must serialize JSON and XML based on Accept header") {
      withAppRunning(classOf[ExampleApp], Resources.getResource("config.yml").getPath) { rule =>

        val xml = TestHttpClient.create.target(s"http://localhost:${rule.getLocalPort}/scala")
          .request().accept(APPLICATION_XML).buildGet().invoke(classOf[String])
        assert(xml == "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userWithJaxbAnnotation><username>me</username><email>me@example.com</email></userWithJaxbAnnotation>")

        val json = TestHttpClient.create.target(s"http://localhost:${rule.getLocalPort}/scala")
          .request().accept(APPLICATION_JSON).buildGet().invoke(classOf[String])
        assert(json == "{\"username\":\"me\",\"email\":\"me@example.com\"}")
      }
    }
  }
}

