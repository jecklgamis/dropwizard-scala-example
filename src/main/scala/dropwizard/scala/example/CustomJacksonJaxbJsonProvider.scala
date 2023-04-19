package dropwizard.scala.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.ext.Provider

object CustomJacksonJaxbJsonProvider {
  private val objectMapper = new ObjectMapper
  objectMapper.registerModule(new JavaTimeModule)
  objectMapper.registerModule(new DefaultScalaModule)
}

@Provider
@Produces(Array(MediaType.APPLICATION_JSON))
class CustomJacksonJaxbJsonProvider extends JacksonJsonProvider {
  setMapper(CustomJacksonJaxbJsonProvider.objectMapper)
}