package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import org.cloudfoundry.client.lib.CloudFoundryClient
import org.cloudfoundry.client.lib.CloudFoundryException
import org.cloudfoundry.client.lib.CloudApplication
import scala.collection.immutable.List
import scala.collection.JavaConversions._

object Application extends Controller{
 
  val loginForm = Form(
  tuple(
    "email" -> nonEmptyText,
    "password" -> nonEmptyText
  ) 
)
	val cloudControllerUrl = "https://api.cloudfoundry.com"
  
	def index = Action {  	
    Ok(html.login(loginForm))
    }
  	
  	def checkLogin = Action { 
  	     
  	  implicit request => loginForm.bindFromRequest.fold( formWithErrors => BadRequest(html.login(formWithErrors)),
	{		 
  	    case(email,password)=> 
  		val client = new CloudFoundryClient(email, password, cloudControllerUrl)
  		val token = client.login()  		
  		/*catch {
  		  case cfe: CloudFoundryException => Ok("Invalid Login")
  		  case e: Exception => Ok("Exception found")		  
  		}
  		*/ 
  		val applist = client.getApplications()
  	    val resapp=applist.toList
  	    Ok(html.apps(email,resapp)).withSession("token" -> token)	 		
  	 }
    )   	   
  }
  	      
 	def showLogs(appName:String) = Action { implicit request =>
 	    var file1=""
 	    var file2=""
  	    session.get("token").map{ token =>
  	     val client = new CloudFoundryClient(token,cloudControllerUrl)
  	     file1 = client.getFile(appName,0,"/logs/stderr.log")
  	     file2 = client.getFile(appName,0,"/logs/stdout.log")
  	     }
  	   Ok(html.logs(file1,file2,appName))	   
  	}

 	def logout = Action { implicit request =>
  	     session.get("token").map { token =>
  	     val client = new CloudFoundryClient(token,cloudControllerUrl)
  	     client.logout()
  	    }
  	     Ok(html.login(loginForm)).withNewSession
  	  }
 	
} 