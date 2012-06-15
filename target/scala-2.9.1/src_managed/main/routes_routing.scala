// @SOURCE:/Users/desaip/scalaApp1/conf/routes
// @HASH:084cdd6bf74a928969f67d9bc8c44fe02f9cceab
// @DATE:Fri Jun 15 12:23:49 PDT 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:5
val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:7
val controllers_Application_checkLogin1 = Route("POST", PathPattern(List(StaticPart("/apps"))))
                    

// @LINE:9
val controllers_Application_showLogs2 = Route("GET", PathPattern(List(StaticPart("/logs"))))
                    

// @LINE:11
val controllers_Application_logout3 = Route("GET", PathPattern(List(StaticPart("/login"))))
                    

// @LINE:14
val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Application.index"""),("""POST""","""/apps""","""controllers.Application.checkLogin"""),("""GET""","""/logs""","""controllers.Application.showLogs(appName:String)"""),("""GET""","""/login""","""controllers.Application.logout"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:5
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:7
case controllers_Application_checkLogin1(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.checkLogin, HandlerDef(this, "controllers.Application", "checkLogin", Nil))
   }
}
                    

// @LINE:9
case controllers_Application_showLogs2(params) => {
   call(params.fromQuery[String]("appName", None)) { (appName) =>
        invokeHandler(_root_.controllers.Application.showLogs(appName), HandlerDef(this, "controllers.Application", "showLogs", Seq(classOf[String])))
   }
}
                    

// @LINE:11
case controllers_Application_logout3(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.logout, HandlerDef(this, "controllers.Application", "logout", Nil))
   }
}
                    

// @LINE:14
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                