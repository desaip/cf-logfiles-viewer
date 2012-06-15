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
         
         browser.$("#email").text(email)
         browser.$("#password").text(pwd)
         browser.$("#submit").click()
         
         browser.url must equalTo("http://localhost:3333/apps")
         browser.$("#emailid").first.getText must equalTo(email)
         browser.$("a").get(1).click()
         
         browser.$("#stderr").first.getText must equalTo("Standard Error Logs")
         browser.$("#stdout").first.getText must equalTo("Standard Output Logs")
        
         }
     } 
 }

/* add a configuration to run this test. Create cf.properties that should contain your CF credentials as follows:
email=<your email>
password=<your password>
*/