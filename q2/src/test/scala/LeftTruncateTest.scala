import wiselychen._
import org.scalatest._

class LeftTruncateTest  extends FunSuite {

  test("Case 1234") {
    assert( Problem2.leftTruncate(1234) == List(1234,234,34,4)  )
  }

  test("Case 0") {
    assert( Problem2.leftTruncate(0) == List(0)  )
  }


  test("Case -1 : If the input < 0, at least don't break the code") {
    assert( Problem2.leftTruncate(-1) == List()  )
  }

  test("max int "){
    assert( Problem2.leftTruncate(java.lang.Integer.MAX_VALUE) ==
      List(2147483647, 147483647, 47483647, 7483647, 483647, 83647, 3647, 647, 47, 7))
  }

}
