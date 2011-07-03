package thesmith.hbase

import org.bowlerframework.view.scalate._
import org.bowlerframework.Request

import org.apache.hadoop.hbase.HBaseConfiguration

import thesmith.hbase.service.Top10Store
import thesmith.hbase.controller.Top10Controller

class Bootstrap{
  val conf = HBaseConfiguration.create
  val top10Store = new Top10Store(conf)
  val controller = new Top10Controller(top10Store)
  
  val parentLayout = DefaultLayout("default")

  def resolver(request: Request): Option[DefaultLayout] = Option(parentLayout)
  TemplateRegistry.defaultLayout = resolver(_)

  org.bowlerframework.view.scalate.RenderEngine.getEngine.allowCaching = true
  org.bowlerframework.view.scalate.RenderEngine.getEngine.allowReload = false
}