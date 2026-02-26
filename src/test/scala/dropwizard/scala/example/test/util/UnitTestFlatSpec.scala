package dropwizard.scala.example.test.util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{Inside, Inspectors, OptionValues}

abstract class UnitTestFlatSpec extends AnyFlatSpec with Matchers with OptionValues with Inside with Inspectors
