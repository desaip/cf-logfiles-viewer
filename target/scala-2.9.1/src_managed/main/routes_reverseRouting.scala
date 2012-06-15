// @SOURCE:/Users/desaip/scalaApp1/conf/routes
// @HASH:084cdd6bf74a928969f67d9bc8c44fe02f9cceab
// @DATE:Fri Jun 15 12:23:49 PDT 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:14
// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:5
package controllers {

// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:5
class ReverseApplication {
    


 
// @LINE:11
def logout() = {
   Call("GET", "/login")
}
                                                        
 
// @LINE:9
def showLogs(appName:String) = {
   Call("GET", "/logs" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("appName", appName)))))
}
                                                        
 
// @LINE:7
def checkLogin() = {
   Call("POST", "/apps")
}
                                                        
 
// @LINE:5
def index() = {
   Call("GET", "/")
}
                                                        

                      
    
}
                            

// @LINE:14
class ReverseAssets {
    


 
// @LINE:14
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:14
// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:5
package controllers.javascript {

// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:5
class ReverseApplication {
    


 
// @LINE:11
def logout = JavascriptReverseRoute(
   "controllers.Application.logout",
   """
      function() {
      return _wA({method:"GET", url:"/login"})
      }
   """
)
                                                        
 
// @LINE:9
def showLogs = JavascriptReverseRoute(
   "controllers.Application.showLogs",
   """
      function(appName) {
      return _wA({method:"GET", url:"/logs" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("appName", appName)])})
      }
   """
)
                                                        
 
// @LINE:7
def checkLogin = JavascriptReverseRoute(
   "controllers.Application.checkLogin",
   """
      function() {
      return _wA({method:"POST", url:"/apps"})
      }
   """
)
                                                        
 
// @LINE:5
def index = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"/"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:14
class ReverseAssets {
    


 
// @LINE:14
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:14
// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:5
package controllers.ref {

// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:5
class ReverseApplication {
    


 
// @LINE:11
def logout() = new play.api.mvc.HandlerRef(
   controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Seq())
)
                              
 
// @LINE:9
def showLogs(appName:String) = new play.api.mvc.HandlerRef(
   controllers.Application.showLogs(appName), HandlerDef(this, "controllers.Application", "showLogs", Seq(classOf[String]))
)
                              
 
// @LINE:7
def checkLogin() = new play.api.mvc.HandlerRef(
   controllers.Application.checkLogin(), HandlerDef(this, "controllers.Application", "checkLogin", Seq())
)
                              
 
// @LINE:5
def index() = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq())
)
                              

                      
    
}
                            

// @LINE:14
class ReverseAssets {
    


 
// @LINE:14
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            
}
                    
                