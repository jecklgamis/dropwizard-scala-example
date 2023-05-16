package dropwizard.scala.example

import dropwizard.scala.example.filter.DiagnosticContextFilter
import dropwizard.scala.example.health.DefaultHealthCheck
import dropwizard.scala.example.resource.{ProbeResource, RootResource}
import io.dropwizard.core.setup.Environment

class ExampleApp extends io.dropwizard.core.Application[ExampleAppConfig] {

  override def run(config: ExampleAppConfig, env: Environment): Unit = {
    env.jersey().register(new RootResource(config.appName))
    env.jersey.register(new CustomJacksonJaxbJsonProvider)
    env.jersey.register(new DiagnosticContextFilter)
    env.jersey.register(new ProbeResource)
    env.healthChecks().register("default", new DefaultHealthCheck)
  }
}


object ExampleApp {
  def main(args: Array[String]): Unit = new ExampleApp().run(args: _*)
}
