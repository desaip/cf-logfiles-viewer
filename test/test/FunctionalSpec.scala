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
         browser.findFirst("#tableapps").find("a",1).click()
         //browser.url must contain("http://localhost:3333/logs?appName=")
         browser.find("#stderr").size must equalTo(1)
         browser.find("#stdout").size must equalTo(1)
         
         //back to apps
         browser.click("#apps")
         //browser.url must equalTo("http://localhost:3333/apps")
         browser.$("#tableapps").size must equalTo(1)

         //multiple logs
         browser.findFirst("#multiple_apps").click
         browser.click("#selectedAppsLogs")
         //browser.url must contain("http://localhost:3333/multipleApps?selected_apps=")
         browser.find("#stderr").size must equalTo(1)
         browser.find("#stdout").size must equalTo(1)
                
         //usage
         browser.click("#info")
         browser.$("#tableinfo").size must equalTo(1)
         
         //logout
         browser.click("#logout")
         //browser.url must equalTo("http://localhost:3333/login")
         browser.$("#email").size must equalTo(1)
         browser.$("#password").size must equalTo(1)
         
         //empty login validation
         browser.$("#email").text("")
         browser.$("#password").text("")
         browser.click("#submit")
         browser.$("#email").size must equalTo(1)
         browser.$("#password").size must equalTo(1)
         browser.$("#tableapps").size must equalTo(0)
         
         //invalid login
         browser.$("#email").text("invalid@xyz.xyz")
         browser.$("#password").text("invalid")
         browser.click("#submit")
         browser.$("#email").size must equalTo(1)
         browser.$("#password").size must equalTo(1)
         browser.$("#tableapps").size must equalTo(0)
         
      }
     } 
 }
