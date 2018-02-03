package dropwizard.scala.example.resource

import javax.ws.rs.core.Response

import dropwizard.scala.example.{ExampleApp, ExampleAppConfig}
import io.dropwizard.testing.DropwizardTestSupport
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class RootResourceIntTest {
  val testSupport = new DropwizardTestSupport[ExampleAppConfig](classOf[ExampleApp], resourceFilePath("config.yml"))

  @Before
  def setup() {
    testSupport.before()
  }

  @Test
  def ensureRootEndPointOk(): Unit = {
    val response = TestHttpClient.create.target(s"http://127.0.0.1:${testSupport.getLocalPort}/").request().get(classOf[Response])
    assertEquals(200, response.getStatus)
    assertEquals("application/json", response.getHeaders.getFirst("Content-Type").asInstanceOf[String].split(';')(0))
    val entity = response.readEntity(classOf[Map[String, String]])
    assertEquals("It works for sure!", entity("message"))
  }

}

