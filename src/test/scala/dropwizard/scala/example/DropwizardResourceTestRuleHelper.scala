package dropwizard.scala.example

import io.dropwizard.testing.junit.ResourceTestRule
import org.junit.runner.Description

object DropwizardResourceTestRuleHelper {
  def withResourceTestRule(configBlock: (ResourceTestRule.Builder) => Unit)(testBlock: (ResourceTestRule) => Unit) {
    val builder = new ResourceTestRule.Builder()
    configBlock(builder)
    val rule = builder.build()
    rule.apply(() => {
      testBlock(rule)
    }, Description.EMPTY).evaluate()
  }
}
