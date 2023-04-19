package dropwizard.scala.example.resource


import com.codahale.metrics.annotation.Timed
import jakarta.ws.rs.core.{MediaType, Response}
import jakarta.ws.rs.{GET, Path, Produces}


@Path("/")
class RootResource(appName: String) {

  @Produces(Array(MediaType.APPLICATION_JSON))
  @GET
  @Timed
  def default: Response = {
    Response.ok.entity(Map("name" -> appName, "java.version" -> System.getProperty("java.version"), "java.runtime.name" -> System.getProperty("java.runtime.name"))
    ).build()
  }

}
