package dropwizard.scala.example.resource

import javax.ws.rs.client.Client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.glassfish.jersey.client.{ClientConfig, JerseyClientBuilder}

object TestHttpClient {

  def create: Client = {
    val config = new ClientConfig()
    val client = JerseyClientBuilder.createClient(config)
    client.register(jacksonJaxbJsonProvider, 0)
    client
  }

  private def jacksonJaxbJsonProvider: JacksonJaxbJsonProvider = {
    val provider = new JacksonJaxbJsonProvider()
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
    provider.setMapper(objectMapper)
    provider
  }
}

