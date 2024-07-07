package dropwizard.scala.example.test.util

import java.util.Base64
import java.util.concurrent.ConcurrentHashMap

import dropwizard.scala.example.{App, AppConfig}
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import io.dropwizard.testing.{ConfigOverride, DropwizardTestSupport}
import io.dropwizard.core.{Application, Configuration}
import org.slf4j.LoggerFactory

import scala.collection.mutable

trait AppTestSupport {

  def withAppRunning[T](config: String = "config.yaml")(fn: DropwizardTestSupport[AppConfig] => T): T = {
    val server = DropwizardTestSupportFactory.get(classOf[App], resourceFilePath(config))
    server.before()
    try {
      fn(server)
    } finally {
      server.after()
    }
  }
}


class DropwizardTestSupportCache {
  private val instances = new ConcurrentHashMap[String, DropwizardTestSupport[_ <: Configuration]]()

  def get(key: String): Option[DropwizardTestSupport[_ <: Configuration]] = {
    if (instances.containsKey(key)) Some(instances.get(key)) else None
  }

  def put(key: String, testSupport: DropwizardTestSupport[_ <: Configuration]): Unit = {
    instances.put(key, testSupport)
  }
}

object DropwizardTestSupportFactory {
  private val log = LoggerFactory.getLogger(this.getClass)
  private val cache = new DropwizardTestSupportCache

  private def keyFrom(configPath: String): String = Base64.getEncoder.encodeToString(configPath.getBytes)

  def get[C <: Configuration](appClass: Class[_ <: Application[C]], configPath: String,
                              configOverrides: ConfigOverride*): DropwizardTestSupport[C] = {
    val cacheKey = keyFrom(configPath)
    if (cache.get(cacheKey).isDefined) {
      log.info(s"Retrieved from cache : $cache/$configPath")
      cache.get(cacheKey).get.asInstanceOf[DropwizardTestSupport[C]]
    } else {
      val overrides = mutable.ListBuffer.empty[ConfigOverride]
      configOverrides.foreach(o => overrides += o)
      overrides += ConfigOverride.config("server.applicationConnectors[0].port", PortUtil.unused().toString)
      overrides += ConfigOverride.config("server.adminConnectors[0].port", PortUtil.unused().toString)
      val support = new DropwizardTestSupport[C](appClass, configPath, overrides.toSeq: _*)
      cache.put(cacheKey, support)
      log.info(s"Stored to cache $cacheKey")
      support
    }
  }
}




