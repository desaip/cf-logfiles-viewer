package test
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import java.util.Properties
import java.io.BufferedReader
import java.io.FileReader

class FunctionalSpec extends Specification{
 
  var email = ""
  var pwd = ""
  
  def getProperties {
  val p:Properties = new Properties() 
  p.load(new FileReader("cf.properties"))
  email += p.getProperty("email")
  pwd += p.getProperty("password") 
  }
 
     "run in a browser" in { 
      running(TestServer(3333),HTMLUNIT) { browser =>
         browser.goTo("http://localhost:3333/")
         
         browser.$("#email").text(email)
         browser.$("#password").text(pwd)
         browser.$("#submit").click()
         browser.url must equalTo("http://localhost:3333/apps")
        //browser.$("#welcome").first.getText must equalTo("Welcome to cloud foundry")
        //browser.$("#email-id").first.getText must equalTo("Email is : desaip@vmware.com")
         }
     } 
 }