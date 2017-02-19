package dropwizard.scala.example

import javax.ws.rs.Path
import javax.ws.rs.container.{ContainerRequestFilter, ContainerResponseFilter}

import com.codahale.metrics.health.HealthCheck
import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import dropwizard.scala.example.GuiceInjector.{withInjector, wrap}
import dropwizard.scala.example.filter.DiagnosticContextFilter
import io.dropwizard.setup.Environment
import org.glassfish.jersey.filter.LoggingFilter

class ExampleApp extends io.dropwizard.Application[ExampleAppConfig] {
  override def getName: String = "dropwizard-scala-example"

  override def run(t: ExampleAppConfig, env: Environment): Unit = {
    configure(t, env)
  }

  def configure(config: ExampleAppConfig, env: Environment) {
    withInjector(new ExampleAppModule(config, env)) { injector =>
      injector.instancesWithAnnotation(classOf[Path]).foreach { r => env.jersey().register(r) }
      injector.instancesOfType(classOf[HealthCheck]).foreach { h => env.healthChecks.register(h.getClass.getName, h) }
      injector.instancesOfType(classOf[ContainerRequestFilter]).foreach { f => env.jersey().register(f) }
      injector.instancesOfType(classOf[ContainerResponseFilter]).foreach { f => env.jersey().register(f) }
    }
    env.jersey.register(jacksonJaxbJsonProvider)
    env.jersey.register(new LoggingFilter)
    env.jersey.register(new DiagnosticContextFilter)
  }

  private def jacksonJaxbJsonProvider: JacksonJaxbJsonProvider = {
    val provider = new JacksonJaxbJsonProvider()
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper.registerModule(new JodaModule)
    objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true)
    provider.setMapper(objectMapper)
    provider
  }
}


object ExampleApp {
  def main(args: Array[String]) = new ExampleApp().run(args: _*)
}
