package thesmith.hbase.controller

import com.recursivity.commons.validator._
import org.bowlerframework.model.DefaultModelValidator

import thesmith.hbase.model.Top10

class Top10Validator(top10: Top10) extends DefaultModelValidator(classOf[Top10Validator]) {
  this.add(NotNullOrNone("id", {
    top10.id
  }))
  this.add(NotNullOrNone("title", {
    top10.title
  }))
  this.add(NotNullOrNone("desc", {
    top10.desc
  }))
  this.add(NotNullOrNone("rating", {
    top10.rating
  }))
}