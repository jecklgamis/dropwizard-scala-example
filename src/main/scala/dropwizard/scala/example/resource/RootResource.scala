package dropwizard.scala.example.resource

import java.lang.System.getProperty
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.{GET, Path, Produces}

@Path("/")
class RootResource {

  @Produces(Array(APPLICATION_JSON))
  @GET
  def default: Response = {
    Response.ok.entity(Map("name" -> "dropwizard-scala-example", "message" -> "It works for sure!",
      "java.version" -> getProperty("java.version"), "java.vm.version" -> getProperty("java.vm.version"))).build()
  }

}
