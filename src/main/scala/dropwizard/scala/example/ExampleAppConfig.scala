package dropwizard.scala.example

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.core.Configuration

class ExampleAppConfig extends Configuration {
  @JsonProperty
  val appName = "appName"
}


