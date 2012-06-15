
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[scala.Tuple2[String, String]],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(loginForm: Form[(String,String)]):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.36*/("""

"""),format.raw/*4.1*/("""
"""),_display_(Seq(/*5.2*/main("login")/*5.15*/ {_display_(Seq(format.raw/*5.17*/("""	
	<center>		
    """),_display_(Seq(/*7.6*/form(action = routes.Application.checkLogin)/*7.50*/ {_display_(Seq(format.raw/*7.52*/("""

        """),_display_(Seq(/*9.10*/inputText(
            field = loginForm("email"),
            args = '_label -> "Email Id"
        ))),format.raw/*12.10*/("""

        """),_display_(Seq(/*14.10*/inputPassword(
            field = loginForm("password"),
            args = '_label -> "Password" 
        ))),format.raw/*17.10*/("""

        <p class="buttons">
            <input type="submit" id="submit">
        <p>
	</center>
    """)))})),format.raw/*23.6*/("""
""")))})))}
    }
    
    def render(loginForm:Form[scala.Tuple2[String, String]]) = apply(loginForm)
    
    def f:((Form[scala.Tuple2[String, String]]) => play.api.templates.Html) = (loginForm) => apply(loginForm)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Jun 15 12:23:49 PDT 2012
                    SOURCE: /Users/desaip/scalaApp1/app/views/login.scala.html
                    HASH: cb3b202aaef8893ab2cd33debe4f02dedeb8e39d
                    MATRIX: 533->1|655->35|683->54|714->56|735->69|769->71|817->90|869->134|903->136|944->147|1067->248|1109->259|1240->368|1375->472
                    LINES: 19->1|23->1|25->4|26->5|26->5|26->5|28->7|28->7|28->7|30->9|33->12|35->14|38->17|44->23
                    -- GENERATED --
                */
            