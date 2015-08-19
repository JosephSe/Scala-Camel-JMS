package camel

import camel.connections.JMSFactory
import camel.routes.{JMSRoute}
import org.apache.camel.impl.DefaultCamelContext

object CamelStarter extends App {

  println("starting")
  // use the CamelContext as you do it with Java
  val camelContext = new DefaultCamelContext
  camelContext.addComponent("active-jms", JMSFactory.tstJMSComponent)
  camelContext.addRoutes(new JMSRoute)
  //  camelContext.addRoutes(new AMQPRoute)
  //    camelContext.setTracing(true)
//  logger.info("starting...")
  camelContext.start
  val producerTemplate = camelContext.createProducerTemplate()
  println("started")
  Thread.sleep(99000000)

}