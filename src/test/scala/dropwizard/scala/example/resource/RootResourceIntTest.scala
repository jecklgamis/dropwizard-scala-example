package dropwizard.scala.example.resource

import dropwizard.scala.example.{ExampleApp, ExampleAppConfig}
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import io.dropwizard.testing.{ConfigOverride, DropwizardTestSupport}
import javax.ws.rs.core.Response
import org.junit.Assert.assertEquals
import org.junit.{After, Before, Test}

class RootResourceIntTest {
  val testSupport = new DropwizardTestSupport[ExampleAppConfig](
    classOf[ExampleApp],
    resourceFilePath("config.yml"),
    ConfigOverride.config("server.applicationConnectors[1].keyStorePath", "src/main/resources/keystore.pfx")
  )

  @Before
  def setup() {
    testSupport.before()
  }

  @After
  def after() {
    testSupport.after()
  }

  @Test
  def testRootEndPoint(): Unit = {
    val response = HttpClient.create.target(s"http://127.0.0.1:${testSupport.getLocalPort}/")
      .request().get(classOf[Response])
    assertEquals(200, response.getStatus)
    assertEquals("application/json", response.getHeaders.getFirst("Content-Type"))
    val entity = response.readEntity(classOf[Map[String, String]])
    assertEquals("dropwizard-scala-example", entity("name"))
  }


}

