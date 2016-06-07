package dropwizard.scala.example.resource

import javax.ws.rs.client.Client
import javax.ws.rs.core.MediaType.{APPLICATION_JSON, APPLICATION_XML}

import com.google.common.io.Resources
import dropwizard.scala.example.DropwizardAppRuleHelper.withAppRunning
import dropwizard.scala.example.ExampleApp
import org.glassfish.jersey.client.ClientProperties._
import org.glassfish.jersey.client.{ClientConfig, JerseyClientBuilder}
import org.glassfish.jersey.filter.LoggingFilter
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider
import org.scalatest.{FunSpec, Matchers}

class ExampleResourceSpec extends FunSpec with Matchers {
  describe("/scala endpoint") {
    it("must serialize JSON and XML based on Accept header") {
      withAppRunning(classOf[ExampleApp], Resources.getResource("config.yml").getPath) { rule =>
        val xml = client.target(s"http://localhost:${rule.getLocalPort}/scala")
          .request().accept(APPLICATION_XML).buildGet().invoke(classOf[String])
        assert(xml == "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userWithJaxbAnnotation><username>me</username><email>me@example.com</email></userWithJaxbAnnotation>")

        val json = client.target(s"http://localhost:${rule.getLocalPort}/scala")
          .request().accept(APPLICATION_JSON).buildGet().invoke(classOf[String])
        assert(json == "{\"username\":\"me\",\"email\":\"me@example.com\"}")
      }
    }
  }

  def client: Client = {
    val config = new ClientConfig()
    config.register(classOf[LoggingFilter])
    config.register(classOf[XmlJaxbElementProvider.App])
    config.property(CONNECT_TIMEOUT, 5000)
    config.property(READ_TIMEOUT, 10000)
    JerseyClientBuilder.createClient(config)
  }
}

