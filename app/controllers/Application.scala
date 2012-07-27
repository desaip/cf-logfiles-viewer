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
    "email" -> email,
    "password" -> nonEmptyText
  ) 
)
	val msgTxt = ""
	val cloudControllerUrl = "https://api.cloudfoundry.com"
  
	def index = Action { 	  
    Ok(html.login(loginForm, msgTxt))
  }
    
  	def checkLogin = Action {  	     
  	  implicit request => loginForm.bindFromRequest.fold( formWithErrors => BadRequest(html.login(formWithErrors,msgTxt)),
	{		 
  	    case(email,password)=> 
  		val client = new CloudFoundryClient(email, password, cloudControllerUrl)	
  		try {
  		  val token = client.login() 
  		  Redirect(routes.Application.showApps()).withSession("token"-> token, "email"->email)
  		}
  		catch{
  		   case cfe: CloudFoundryException => Ok(html.login(loginForm,"Invalid Email and/or Password - Please Login Again"))
  		   case ncd: NoClassDefFoundError => Ok("Server needs to be restarted")
  		   case _: Exception => Ok(html.login(loginForm,"Please Login Again"))
  		}	    		
  	 }
    ) 	   
  }
  	
  	def showApps = Action { implicit request =>
  	  session.get("token").map{token =>
  	  val client = new CloudFoundryClient(token,cloudControllerUrl)
  	  try{
  	  val appslist = client.getApplications().toList
  	  val url = client.getCloudControllerUrl().toString()
  	  val email = session.get("email").get
  	  Ok(html.apps(appslist,url,email))
  	  }
  	  catch{
  	    case cfe: CloudFoundryException => Ok("No Apps Found")
  	  	}
  	  }.getOrElse{
  	   Ok("No Apps found") 
  	  }
  	}
  		
 	def showLogs(appName:String, instances:java.lang.Integer) = Action { implicit request =>
  	    session.get("email").map{ email =>
  	    try {
  	     Ok(html.logs(appName,email,instances)) 	
  	    }
  	    catch{
  	      case cfe: CloudFoundryException => Ok("No Log Found")
  	    }
  	    }.getOrElse{
  	      Ok("No Log Found")	
  	    } 	      	          
  	}

 	def getLog(appName:String, logType:String,instance:java.lang.Integer) = Action { implicit request =>
  	    session.get("token").map{ token =>
  	    val client = new CloudFoundryClient(token,cloudControllerUrl) 
  	    try {
  	    val file = client.getFile(appName,instance,"/logs/"+logType+".log")
  	    Ok(file)  
  	    }
  	    catch{
  	      case cfe: CloudFoundryException => Ok("No Log Found")
  	    }
  	    }.getOrElse{
  	      Ok("No Log Found")	
  	    } 	      	  
 	}
 	
 	def getInfo = Action { implicit request =>
 	  session.get("token").map { token =>
  	     val client = new CloudFoundryClient(token,cloudControllerUrl)
  	     val infoList = client.getCloudInfo() 
  	     val email = session.get("email").get
  	     Ok(html.info(infoList, email))   
  	  }.getOrElse{
  	      Ok("No Info Found")	
 	}
 }
 	
 	def logout = Action { implicit request =>
  	     session.get("token").map { token =>
  	     val client = new CloudFoundryClient(token,cloudControllerUrl)
  	     client.logout()
  	    }
  	     Ok(html.login(loginForm,msgTxt)).withNewSession
  	  }	
 	
 	
} 