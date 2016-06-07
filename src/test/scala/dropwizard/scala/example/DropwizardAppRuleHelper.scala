package dropwizard.scala.example

import io.dropwizard.Configuration
import io.dropwizard.testing.ConfigOverride
import io.dropwizard.testing.junit.DropwizardAppRule
import org.junit.runner.Description

import scala.collection.mutable

object DropwizardAppRuleHelper {

  def withAppRunning[C <: Configuration](serviceClass: Class[_ <: io.dropwizard.Application[C]],
                                         configPath: String, configOverrides: ConfigOverride*)
                                        (fn: (DropwizardAppRule[C]) => Unit) {
    val overrides = new mutable.ListBuffer[ConfigOverride]
    configOverrides.foreach { o => overrides += o }
    val rule = new DropwizardAppRule(serviceClass, configPath, overrides.toList: _*)
    rule.apply(() => fn(rule), Description.EMPTY).evaluate()
  }

}
