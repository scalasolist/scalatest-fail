package example

import org.scalatest._
import org.scalacheck.Gen
import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.SpanSugar._
import prop._

class Infinity extends PropSpec with Matchers with GeneratorDrivenPropertyChecks with TimeLimitedTests {
  val timeLimit = 500 millis

  val nums = Gen.choose(1,1000)
  val pairs = for (a <- nums; b <- nums) yield (a,b)

  val infinity : Int => Boolean = d => {
    var cur = 1001
    while (cur > 0)
      cur -= d
    cur == 0
  }
  val infLog = new example.OutputStatistics1(infinity)
  val check : Int => Boolean = x => x > 0

  property("correct pair", CorrectTest) {
    forAll( (nums, "a"), (nums, "b") ) { (a,b) =>
      infinity(a) shouldBe true
    }
  }

  property("correct check", CorrectTest) {
    forAll( (pairs, "pair") ) { p =>
      check(p._1) shouldBe true
    }
  }

  property("infinity", FailedTest) {
    forAll( (pairs, "pair") ) { p =>
      infinity(p._1) shouldBe true
    }
  }
}
