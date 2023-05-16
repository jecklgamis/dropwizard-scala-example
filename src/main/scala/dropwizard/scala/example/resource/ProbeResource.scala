package dropwizard.scala.example.resource

import jakarta.ws.rs.core.{MediaType, Response}
import jakarta.ws.rs.{GET, Path, Produces}

@Path("/probe")
class ProbeResource {

  @GET
  @Path("/alive")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def live: Response = {
    Response.ok.entity(Map("message" -> "I'm alive!")
    ).build()
  }

  @GET
  @Path("/ready")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def ready: Response = {
    Response.ok.entity(Map("message" -> "I'm ready!")
    ).build()
  }

}
