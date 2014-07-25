package example

import org.scalatest._
import org.scalacheck.Gen
import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.SpanSugar._
import prop._

class Broke extends PropSpec with Matchers with GeneratorDrivenPropertyChecks{
  val nums = Gen.choose(1,1000)

  property("impossible forAll", FailedTest) {
    forAll( (nums, "n") ) { n =>
      n shouldBe 10
    }
  }

  property("impossible single", CorrectTest) {
    0 shouldBe 1
  }
}
