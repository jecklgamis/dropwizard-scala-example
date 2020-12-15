package dropwizard.scala.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider

object CustomJacksonJaxbJsonProvider {
  private val objectMapper = new ObjectMapper
  objectMapper.registerModule(new JavaTimeModule)
  objectMapper.registerModule(new DefaultScalaModule)
}

@Provider
@Produces(Array(MediaType.APPLICATION_JSON))
class CustomJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {
  setMapper(CustomJacksonJaxbJsonProvider.objectMapper)
}