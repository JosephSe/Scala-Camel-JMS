package camel

import camel.connections.JMSFactory
import camel.routes.{FolderToXMLRoute, JMSRoute}
import org.apache.camel.impl.DefaultCamelContext

object CamelLoader extends App {

  println("starting")
  val staticFolderToLoad = System.getProperty("staticXMLFolder")
  val contractFolderToLoad = System.getProperty("contractXMLFolder")
  if(staticFolderToLoad == null || contractFolderToLoad == null) {
    println("Please specify -DstaticFolderToLoad & -DcontractFolderToLoad")
    System.exit(0)
  }
  // use the CamelContext as you do it with Java
  val camelContext = new DefaultCamelContext
  camelContext.addComponent("active-jms", JMSFactory.sitJMSComponent)
  camelContext.addRoutes(new FolderToXMLRoute(staticFolderToLoad, contractFolderToLoad))
//  camelContext.addRoutes(new AMQPRoute)
//    camelContext.setTracing(true)
//  logger.info("starting...")
  camelContext.start
  val producerTemplate = camelContext.createProducerTemplate()
  println("started")
  Thread.sleep(99000000)

}