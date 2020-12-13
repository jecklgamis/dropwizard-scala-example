package dropwizard.scala.example.test.util

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{Inside, Inspectors, OptionValues}

abstract class IntegrationFlatSpec extends AnyFlatSpec with Matchers with OptionValues with Inside with
  Inspectors with MockitoSugar with ArgumentMatchersSugar
