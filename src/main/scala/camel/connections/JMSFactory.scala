package camel.connections

import javax.jms.TopicConnectionFactory
import javax.naming.InitialContext

import org.apache.camel.component.jms.JmsComponent
import org.apache.qpid.client.AMQConnectionFactory
import org.springframework.jndi.JndiTemplate

/**
 * Created by 104403 on 12/11/2014.
 */
object JMSFactory {

  def sitJMSComponent = {
    getJMSConf("jnp://longesb02a1:1099", "/XAConnectionFactory")
  }

  def tstJMSComponent = {
    getJMSConf("jnp://longesb05a1:1099", "/ConnectionFactory")
  }

  def day0JMSComponent = {
    getJMSConf("jnp://longesb05d1:1099", "/ConnectionFactory")
  }

  def localJMSComponent = {
    getJMSConf("jnp://localhost:1099", "/ConnectionFactory")
//    getAMQPConf()
  }

  def getJMSConf(providerURL: String, connFactory: String) = {
    val sitJndiTempplate = new JndiTemplate()
    val sitProps = new java.util.Properties()
    sitProps.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory")
    sitProps.put(javax.naming.Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces")
    sitProps.put(javax.naming.Context.PROVIDER_URL, providerURL) // sit
    sitJndiTempplate.setEnvironment(sitProps)
    val sitCtx = new InitialContext(sitProps)

    val sitConnectionFactory = sitCtx.lookup(connFactory).asInstanceOf[TopicConnectionFactory]
    val sitJms = new JmsComponent()
    sitJms.setConnectionFactory(sitConnectionFactory)
    sitJms
  }

  def getAMQPConf() = {
    val connectionFActory = new AMQConnectionFactory
 //   connectionFActory.setHost("localhost")
 //   connectionFActory.setPort(5672)
    val cxt = new JmsComponent()
//    cxt.setConnectionFactory(connectionFActory)
    cxt
  }

}
