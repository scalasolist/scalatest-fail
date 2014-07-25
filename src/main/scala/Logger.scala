package example

trait OutputStatistics {
  import java.io.{File, FileWriter}
  import scala.sys

  val period = 10

  val outFile = new File("log.txt")
  val outWriter = new FileWriter(outFile)
  sys.addShutdownHook {
    outWriter.close()
  }
  outWriter.write("log started\n")
  outWriter.flush()

  private var _count : Int = 0
  def count = _count
  private var _total : Double = 0
  def total = _total
  private var _trials : Int = 0
  def trials = _trials
  def average = _total / _trials

  def periodical( elem : Double ) = {
    _trials += 1
    _total += elem
    if (_trials % period == 0)
      printStats()
  }

  private def printStats() = {
    outWriter.write( s"$trials trials with $average average\n" )
    outWriter.flush()
  }
}

class OutputStatistics2[R]( val original : (Int, Int) => R ) extends OutputStatistics with Function2[Int, Int, R] {
  override def apply(a : Int, b : Int) : R = {
    if ( (a <= 0) || (b <= 0) )
      warn(a,b)
    periodical(a+b)
    //logEvery(a,b)
    original(a,b)
  }

  private def logEvery(a : Int, b : Int) = {
    outWriter.write( s"call to $a `gcd` $b\n" )
    outWriter.flush()
  }

  private def warn(a : Int, b : Int) = {
    outWriter.write( s"pair of $a, $b contains non-positive number\n" )
    outWriter.flush()
  }
}

class OutputStatistics1[R]( val original : Int => R ) extends OutputStatistics with Function1[Int, R] {
  override def apply(t : Int) : R = {
    if (t <= 0)
      warn(t)
    periodical(t)
    original(t)
  }

  private def warn(t : Int) = {
    outWriter.write( s"danger: $t is non-positive\n" )
    outWriter.flush()
  }
}
