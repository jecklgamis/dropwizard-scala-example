package dropwizard.scala.example.resource


import com.codahale.metrics.annotation.Timed
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.{GET, Path, Produces}

@Path("/")
class RootResource(appName: String) {

  @Produces(Array(APPLICATION_JSON))
  @GET
  @Timed
  def default: Response = {
    Response.ok.entity(Map("name" -> appName,
      "java.version" -> System.getProperty("java.version"),
      "java.runtime.name" -> System.getProperty("java.runtime.name"))
    ).build()
  }

}
