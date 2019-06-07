package dropwizard.scala.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import dropwizard.scala.example.filter.DiagnosticContextFilter
import dropwizard.scala.example.health.DefaultHealthCheck
import dropwizard.scala.example.resource.RootResource
import io.dropwizard.setup.Environment

class ExampleApp extends io.dropwizard.Application[ExampleAppConfig] {

  override def run(config: ExampleAppConfig, env: Environment): Unit = {
    env.jersey().register(new RootResource(config.appName))
    env.jersey.register(jacksonJaxbJsonProvider)
    env.jersey.register(new DiagnosticContextFilter)
    env.healthChecks().register("default", new DefaultHealthCheck)
  }

  private def jacksonJaxbJsonProvider: JacksonJaxbJsonProvider = {
    val provider = new JacksonJaxbJsonProvider()
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper.registerModule(new JavaTimeModule)
    provider.setMapper(objectMapper)
    provider
  }
}


object ExampleApp {
  def main(args: Array[String]): Unit = new ExampleApp().run(args: _*)
}
