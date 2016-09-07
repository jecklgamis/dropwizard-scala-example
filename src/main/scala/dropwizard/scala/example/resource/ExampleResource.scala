package dropwizard.scala.example.resource

import javax.ws.rs.core.MediaType.{APPLICATION_JSON, APPLICATION_XML}
import javax.ws.rs.core.Response
import javax.ws.rs.{GET, Path, Produces}

import dropwizard.scala.example.api.{User, UserPojo, UserPojoWithJaxbAnnotation, UserWithJaxbAnnotation}
import org.joda.time.DateTime

@Path("/example")
class ExampleResource {

  @GET
  @Produces(Array(APPLICATION_JSON))
  def default: Response = {
    Response.ok.entity(Map("message" -> "Scala rocks!", "today" -> DateTime.now)).build()
  }

  @GET
  @Path("java/json")
  @Produces(Array(APPLICATION_JSON))
  def jsonAndJavaEntity: Response = {
    Response.ok.entity(new UserPojo("me", "me@example.com")).build()
  }

  @GET
  @Path("java/xml")
  @Produces(Array(APPLICATION_XML))
  def xmlAndJavaEntity: Response = {
    Response.ok.entity(new UserPojoWithJaxbAnnotation("me", "me@example.com")).build()
  }

  @GET
  @Path("java")
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def javaEntity: Response = {
    Response.ok.entity(new UserPojoWithJaxbAnnotation("me", "me@example.com")).build()
  }

  @GET
  @Path("scala/json")
  @Produces(Array(APPLICATION_JSON))
  def jsonAndScalaEntity: Response = {
    Response.ok.entity(new User("me", "me@example.com")).build()
  }

  @GET
  @Path("scala/xml")
  @Produces(Array(APPLICATION_XML))
  def xmlAndScalaEntity: Response = {
    Response.ok.entity(new UserWithJaxbAnnotation("me", "me@example.com")).build()
  }

  @GET
  @Path("scala")
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def scalaEntity: Response = {
    Response.ok.entity(new UserWithJaxbAnnotation("me", "me@example.com")).build()
  }

}





