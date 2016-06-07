package dropwizard.scala.example

import com.codahale.metrics.health.HealthCheck
import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import dropwizard.scala.example.health.DefaultHealthCheck
import dropwizard.scala.example.resource.ExampleResource
import dropwizard.scala.example.service.{DefaultExampleService, ExampleService}
import io.dropwizard.setup.Environment

class ExampleAppModule(config: ExampleAppConfig, env: Environment) extends AbstractModule {
  override def configure() {
    bind(classOf[ExampleAppConfig]).toInstance(config)
    bind(classOf[Environment]).toInstance(env)
    val healthCheckBinder = Multibinder.newSetBinder(binder(), classOf[HealthCheck])
    healthCheckBinder.addBinding().to(classOf[DefaultHealthCheck])
    bind(classOf[ExampleResource])
    bind(classOf[ExampleService]).to(classOf[DefaultExampleService])
  }
}
