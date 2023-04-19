package dropwizard.scala.example.test.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import jakarta.ws.rs.client.{Client, Entity}
import jakarta.ws.rs.core.Response
import org.glassfish.jersey.client.ClientProperties.{CONNECT_TIMEOUT, READ_TIMEOUT}
import org.glassfish.jersey.client.{ClientConfig, JerseyClientBuilder}
import org.glassfish.jersey.media.multipart.MultiPartFeature

import scala.concurrent.duration.SECONDS

object HttpUtil {
  private val client = JerseyHttpClientFactory.default()

  def get(url: String): Response = client.target(url).request().get()

  def get[T](url: String, responseClass: Class[T]): T = {
    client.target(url).request().get(responseClass)
  }

  def post[T](url: String, entity: Entity[_], responseClass: Class[T]): T = {
    client.target(url).request().post(entity, responseClass)
  }

}

object JerseyHttpClientFactory {
  def default(): Client = {
    create(connectTimeoutSeconds = 5, readTimeoutSeconds = 15)
  }

  def create(connectTimeoutSeconds: Int, readTimeoutSeconds: Int): Client = {
    val config = new ClientConfig()
    config.property(CONNECT_TIMEOUT, SECONDS.toMillis(connectTimeoutSeconds).toInt)
    config.property(READ_TIMEOUT, SECONDS.toMillis(readTimeoutSeconds).toInt)
    val client = JerseyClientBuilder.createClient(config)
    client.register(jacksonJaxbJsonProvider, 0)
    client.register(classOf[MultiPartFeature])
    client
  }

  private def jacksonJaxbJsonProvider: JacksonJsonProvider = {
    val provider = new JacksonJsonProvider()
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(new DefaultScalaModule)
    objectMapper.registerModule(new JodaModule)
    provider.setMapper(objectMapper)
    provider
  }
}
