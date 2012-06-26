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
  	    Redirect(routes.Application.showApps()).withSession("token"-> token)		
  	 }
    ) 	   
  }
  	
  	def showApps = Action { implicit request =>
  	  val token = session.get("token").get
  	  val client = new CloudFoundryClient(token,cloudControllerUrl)
  	  val appslist = client.getApplications().toList
  	  Ok(html.apps(appslist))
  	}
  		
 	def showLogs(appName:String) = Action { 
  	    Ok(html.logs(appName)) 	      
  	}

 	def getLog(appName:String, logType:String) = Action { implicit request =>
  	    session.get("token").map{ token =>
  	    val client = new CloudFoundryClient(token,cloudControllerUrl) 
  	    Ok(client.getFile(appName,0,"/logs/"+logType+".log"))
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