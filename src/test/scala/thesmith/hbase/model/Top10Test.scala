package thesmith.hbase.model

import org.scalatest.FunSuite

class Top10Test extends FunSuite {
  val id = "1"

  test("top10 creation") {
    val top10 = new Top10(id, "top10", "this is a top10", 4)
    assert(top10 != null)
    assert(id == top10.id)
  }
}