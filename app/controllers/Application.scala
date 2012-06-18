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
  	    Ok(html.redirect()).withSession("token" -> token)	 		
  	 }
    ) 	   
  }
  	
  	def showApps = Action { implicit request =>
  	  val token = session.get("token").get
  	  val client = new CloudFoundryClient(token,cloudControllerUrl)
  	  val applist = client.getApplications()
  	  val resapp=applist.toList
  	  Ok(html.apps(resapp))
  	}
  	
  	
 	def showLogs(appName:String) = Action { implicit request =>
  	    session.get("token").map{ token =>
  	     val client = new CloudFoundryClient(token,cloudControllerUrl)
  	     val file1 = client.getFile(appName,0,"/logs/stderr.log")
  	     val file2 = client.getFile(appName,0,"/logs/stdout.log")
  	     Ok(html.logs(file1,file2,appName))
  	    }.getOrElse{
  	      Ok("No Running Instances")	
  	    } 	      
  	}

 	def logout = Action { implicit request =>
  	     session.get("token").map { token =>
  	     val client = new CloudFoundryClient(token,cloudControllerUrl)
  	     client.logout()
  	    }
  	     Ok(html.login(loginForm)).withNewSession
  	  }
 	
} 