package wiselychen

object Problem2 {

  def leftTruncate(num:Int):List[Int] = {
    if( num < 0) return List()
    val arr = num.toString.toCharArray
    (0 to arr.length - 1).map(i => arr.slice(i, arr.length)).map(arr => arr.mkString("").toInt).toList
  }

}
