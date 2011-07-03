package thesmith.hbase.controller

import org.bowlerframework.controller.{FunctionNameConventionRoutes, Controller}
import org.bowlerframework.model.{Validations}
import org.bowlerframework.view.{Renderable}
import org.bowlerframework._

import thesmith.hbase.service.Top10Store
import thesmith.hbase.model.Top10

class Top10Controller(top10Store: Top10Store) extends Controller with FunctionNameConventionRoutes with Validations with Renderable {

  def `GET /`() = render(top10Store.get())

  def `GET /:id`(id: Option[String]) = render(top10Store.get(id.get))

  def `POST /`(top10: Top10) = {
    validate(top10) {
      val validator = new Top10Validator(top10)
      validator.validate
    }

    top10Store.create(top10)
    RequestScope.response.sendRedirect("/" + top10.id)
  }
}