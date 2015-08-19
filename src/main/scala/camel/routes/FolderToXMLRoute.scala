package camel.routes

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * Created by 104403 on 21/10/2014.
 */
class FolderToXMLRoute(staticFolderToLoad: String, contractFolderToLoad: String) extends RouteBuilder {

  //  "active-jms:topic:ContractsAndRatePlans" to "direct:processJMSMessage"
  //  "active-jms:topic:StaticData" to "direct:processJMSMessage"
  //  "active-jms:topic:Inventory" to "direct:processJMSMessage"
  //  "active-jms:topic:RateRulesAndRates" to "direct:processJMSMessage"

  s"file:$staticFolderToLoad/?noop=true" to "active-jms:topic:StaticData"
  s"file:$contractFolderToLoad/?noop=true" to "active-jms:topic:ContractsAndRatePlans"
}
