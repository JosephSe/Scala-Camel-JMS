package camel.processors

import org.apache.camel.{Exchange, Processor}

import scala.util.Random
import scala.xml.{Elem, XML}

/**
 * Created by 104403 on 12/11/2014.
 */
class XMLProcessor extends Processor {

  def checkFile(xmlType: String, xml: Elem): String = {
    return xmlType match {
      case "property-record" => {
        val propertyId = (xml \\ "recordset" \\ "record" \\ "property" \\ "@propertyId")
        "Property-" + propertyId
      }
      case "country-record" => {
        val countryId = (xml \\ "recordset" \\ "record" \\ "country" \\ "@countryId")
        "Country-" + countryId
      }
      case "propertyContract-record" => {
        val id = (xml \\ "recordset" \\ "record" \\ "contract" \\ "@id")
        "Contract-" + id
      }
      case "propertyMarginRoomRate-record" => {
        val id = (xml \\ "recordset" \\ "record" \\ "marginRoomRate" \\ "@id")
        "MarginRoomRate-" + id
      }
      case "propertyRateRule-record" => {
        val id = (xml \\ "recordset" \\ "record" \\ "ratePlan" \\ "@id")
        "RateRule-" + id
      }
      case "propertyRatePlan-record" => {
        val id = (xml \\ "recordset" \\ "record" \\ "ratePlan" \\ "@id")
        "RatePlan-" + id
      }
      case "propertyStaticRoomRate-record" => {
        val id = (xml \\ "recordset" \\ "record" \\ "staticRoomRate" \\ "@id")
        "RoomRate-" + id
      }
      case "product-record" => {
        val id = (xml \\ "recordset" \\ "record" \\ "product" \\ "room" \\ "@roomId")
        "Product-" + id
      }
      case _ => "unmapped-record"
    }
  }

  override def process(p1: Exchange): Unit = {
    val fileName = "file-" + Random.nextString(10) + ".xml"
    println(fileName)
    p1.getIn.setHeader("CamelFileName", fileName)
    p1.getIn.setHeader("writeToFile", true)
  }

  def oldProcess(p1: Exchange): Unit = {
    val xml = XML.loadString(p1.getIn.getBody.toString)
    val xmlString = p1.getIn.getBody.toString
    val xmlSubString = xmlString.substring((xmlString.indexOf("xsi:type=\"soa:") + 14), xmlString.length)
    val xmlType = xmlSubString.substring(0, xmlSubString.indexOf("\">"))
    val txn = (xml \\ "recordset" \\ "@txn")
    p1.getIn.setHeader("type", xmlType)
    val fileName = xmlType + "/" + txn + ".xml"
    val newFileName = checkFile(xmlType, xml)
    if (!newFileName.equals("")) {
      println(newFileName)
      println((xmlType + "/" + newFileName + "-" + txn + ".xml"))
      p1.getIn.setHeader("CamelFileName", (xmlType + "/" + newFileName + "-" + txn + ".xml"))
      p1.getIn.setHeader("writeToFile", true)
    }
  }
}
