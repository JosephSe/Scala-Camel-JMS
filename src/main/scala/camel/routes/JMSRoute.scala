package camel.routes

import camel.processors.{XMLProcessor, JenkinsProcessor}
import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * Created by 104403 on 21/10/2014.
 */
class JMSRoute extends RouteBuilder {

  "active-jms:topic:ContractsAndRatePlans" to "direct:processJMSMessage"
  "active-jms:topic:StaticData" to "direct:processJMSMessage"
  "active-jms:topic:Inventory" to "direct:processJMSMessage"
  "active-jms:topic:RateRulesAndRates" to "direct:processJMSMessage"
  //
  //  "file:///Users/josephwork/Documents/files/others/codebase/scala/XMLDownloader/files/inXML?noop=true" to "direct:processJMSMessage"
//  "file://files/inXML?noop=true" transform(simple("${bodyAs(String)}")) to "active-jms:topic:StaticData"
//  "file://files/inXML?noop=true" transform(simple("${bodyAs(String)}")) to "activemq:queue:StaticData"
//  "file://files/inXML/AreaCityCode?noop=true" transform(simple("${bodyAs(String)}")) to "activemq:queue:StaticData"

  "direct:processJMSMessage" transform(simple("${bodyAs(String)}")) to "direct:processXML"
  "direct:processXML"  bean(new XMLProcessor) when(simple("${header.writeToFile}")) to "direct:writeToFile"
  "direct:writeToFile" to "file:///Users/josephwork/Documents/files/others/codebase/scala/XMLDownloader/files/xmls"
  //  "direct:writeToFile" to "file://files/xmls/"

}
