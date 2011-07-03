package thesmith.hbase.service

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.util.Bytes

import thesmith.hbase.model.Top10
import org.apache.hadoop.hbase.{HTableDescriptor, HColumnDescriptor}
import org.apache.hadoop.hbase.client._
import collection.mutable.ListBuffer
import scala.collection.JavaConversions._

object Top10Stores {
  val TABLE = "top10"
  val FAMILY = Bytes.toBytes(TABLE)
  val ID_KEY = Bytes.toBytes("id")
  val TITLE_KEY = Bytes.toBytes("title")
  val DESC_KEY = Bytes.toBytes("desc")
  val RATING_KEY = Bytes.toBytes("rating")
}

class Top10Store(conf: Configuration) {
  initializeTable(conf)
  private val table = new HTable(conf, Top10Stores.TABLE)

  def create(top10: Top10) {
    val theput = new Put(Bytes.toBytes(top10.id))
    theput.add(Top10Stores.FAMILY, Top10Stores.ID_KEY, Bytes.toBytes(top10.id))
    theput.add(Top10Stores.FAMILY, Top10Stores.TITLE_KEY, Bytes.toBytes(top10.title))
    theput.add(Top10Stores.FAMILY, Top10Stores.DESC_KEY, Bytes.toBytes(top10.desc))
    theput.add(Top10Stores.FAMILY, Top10Stores.RATING_KEY, Bytes.toBytes(top10.rating))
    table.put(theput)
  }

  def get(id: String): Option[Top10] = {
    val result = table.get(new Get(Bytes.toBytes(id)))
    if (result.isEmpty()) return None

    Some(resultToTop10(result))
  }

  def get(): List[Top10] = {
    val scan = new Scan()
    scan.addColumn(Top10Stores.FAMILY, Top10Stores.ID_KEY)
    scan.addColumn(Top10Stores.FAMILY, Top10Stores.TITLE_KEY)
    scan.addColumn(Top10Stores.FAMILY, Top10Stores.DESC_KEY)
    scan.addColumn(Top10Stores.FAMILY, Top10Stores.RATING_KEY)

    val top10s = ListBuffer[Top10]()
    val results = table.getScanner(scan)
    for (row <- results) {
      top10s += resultToTop10(row)
    }

    top10s.toList
  }

  private def resultToTop10(row: Result): Top10 = {
    val id = Bytes.toString(row.getValue(Top10Stores.FAMILY, Top10Stores.ID_KEY))
    val title = Bytes.toString(row.getValue(Top10Stores.FAMILY, Top10Stores.TITLE_KEY))
    val desc = Bytes.toString(row.getValue(Top10Stores.FAMILY, Top10Stores.DESC_KEY))
    val rating = Bytes.toInt(row.getValue(Top10Stores.FAMILY, Top10Stores.RATING_KEY))

    new Top10(id, title, desc, rating)
  }

  private def descriptor(): HTableDescriptor = {
    val tableDescriptor = new HTableDescriptor(Top10Stores.TABLE);
    tableDescriptor.addFamily(new HColumnDescriptor(Top10Stores.FAMILY));

    tableDescriptor
  }

  private def initializeTable(conf: Configuration) {
    val admin = new HBaseAdmin(conf)
    if (!admin.tableExists(Top10Stores.TABLE)) {
      admin.createTable(descriptor())
    }
  }
}