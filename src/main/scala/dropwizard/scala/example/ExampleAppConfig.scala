package dropwizard.scala.example

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration

class ExampleAppConfig extends Configuration {
  @JsonProperty
  val appName = "appName"
}


