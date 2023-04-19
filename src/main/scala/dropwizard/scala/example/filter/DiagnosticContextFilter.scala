package dropwizard.scala.example.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.ws.rs.container.{ContainerRequestContext, ContainerRequestFilter, ContainerResponseContext, ContainerResponseFilter}
import jakarta.ws.rs.core.Context
import org.slf4j.{LoggerFactory, MDC}

import java.util.UUID

class DiagnosticContextFilter extends ContainerRequestFilter with ContainerResponseFilter {
  private val log = LoggerFactory.getLogger(classOf[DiagnosticContextFilter])
  private val REQUEST_ID_KEY = "id"

  @Context
  protected var r: HttpServletRequest = _

  override def filter(requestContext: ContainerRequestContext): Unit = {
    val id = UUID.randomUUID().toString
    log.debug(s"[${Thread.currentThread().getName}] PUT:  ${REQUEST_ID_KEY}=${id}")
    MDC.put(REQUEST_ID_KEY, id)
  }

  override def filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext): Unit = {
    val v = MDC.get(REQUEST_ID_KEY)
    log.debug(s"[${Thread.currentThread().getName}] REMOVE: ${REQUEST_ID_KEY}=${v}")
  }

}
