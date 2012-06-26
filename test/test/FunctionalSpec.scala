/* add a configuration file to run this test. 
Create cf.properties that should contain your CF credentials as follows:
email=<your email>
password=<your password>
*/

package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import java.util.Properties
import java.io.FileReader

class FunctionalSpec extends Specification{
 
  val credential = new Properties() 
  credential.load(new FileReader("cf.properties"))
  val email = credential.getProperty("email")
  val pwd = credential.getProperty("password") 
  
     "run in a browser" in { 
      running(TestServer(3333),HTMLUNIT) { browser =>
         browser.goTo("http://localhost:3333/")
         //login
         browser.$("#email").text(email)
         browser.$("#password").text(pwd)
         browser.click("#submit")
        
         //apps
         //browser.url must equalTo("http://localhost:3333/apps")
         browser.$("#tableapps").size must equalTo(1)
         
         //logs
         browser.findFirst("#tableapps").findFirst("a").click()
         //browser.url must contain("http://localhost:3333/logs?appName=")
         browser.click("#scrollLog1")
         browser.$("#logStderr").size must equalTo(1)
         browser.click("#scrollLog2")
         browser.$("#logStdout").size must equalTo(1)
         
         //back to apps
         browser.click("#apps")
         //browser.url must equalTo("http://localhost:3333/apps")
         browser.$("#tableapps").size must equalTo(1)
         
         //logout
         browser.click("#logout")
         //browser.url must equalTo("http://localhost:3333/login")
         browser.$("#email").size must equalTo(1)
         browser.$("#password").size must equalTo(1)
      }
     } 
 }
