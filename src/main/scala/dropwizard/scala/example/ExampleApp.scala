package dropwizard.scala.example

import dropwizard.scala.example.filter.DiagnosticContextFilter
import dropwizard.scala.example.health.DefaultHealthCheck
import dropwizard.scala.example.resource.RootResource
import io.dropwizard.setup.Environment

class ExampleApp extends io.dropwizard.Application[ExampleAppConfig] {

  override def run(config: ExampleAppConfig, env: Environment): Unit = {
    env.jersey().register(new RootResource(config.appName))
    env.jersey.register(new CustomJacksonJaxbJsonProvider)
    env.jersey.register(new DiagnosticContextFilter)
    env.healthChecks().register("default", new DefaultHealthCheck)
  }
}


object ExampleApp {
  def main(args: Array[String]): Unit = new ExampleApp().run(args: _*)
}
