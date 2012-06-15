
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
object apps extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,List[org.cloudfoundry.client.lib.CloudApplication],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(email: String, resapp: List[org.cloudfoundry.client.lib.CloudApplication]):play.api.templates.Html = {
        _display_ {import helper._

import org.cloudfoundry.client.lib.CloudApplication

import org.cloudfoundry.client.lib.CloudFoundryClient


Seq(format.raw/*1.77*/("""

"""),_display_(Seq(/*6.2*/main("")/*6.10*/ {_display_(Seq(format.raw/*6.12*/("""
 <p id="logout" class="buttons" align="right">
      <a href=""""),_display_(Seq(/*8.17*/routes/*8.23*/.Application.logout)),format.raw/*8.42*/("""">Log Out</a>
 </p>
<center>

<h2>Welcome to cloud foundry</h2>
<h4>Email is : <span id="emailid">"""),_display_(Seq(/*13.36*/email)),format.raw/*13.41*/("""</span></h4> 
<br>
<br>

"""),_display_(Seq(/*17.2*/{if(resapp.isEmpty){
<h3>  <a href="www.cloudfoundry.com"> No Applications Deployed Yet.
Create and deploy your applications on in seconds !!</a>  
</h3>
}
})),format.raw/*22.2*/("""
<table border="1" id="tableapps">
<tr>
<th>Application Name</th>
<th>Instances</th>
<th>Running Instances</th>
<th>Memory</th>
<th>View Log</th>
</tr>
"""),_display_(Seq(/*31.2*/resapp/*31.8*/.map/*31.12*/{app =>_display_(Seq(format.raw/*31.19*/("""
<tr>
<td>"""),_display_(Seq(/*33.6*/app/*33.9*/.getName)),format.raw/*33.17*/("""</td>
<td>"""),_display_(Seq(/*34.6*/app/*34.9*/.getInstances)),format.raw/*34.22*/("""</td>
<td>"""),_display_(Seq(/*35.6*/app/*35.9*/.getRunningInstances)),format.raw/*35.29*/("""</td>
<td>"""),_display_(Seq(/*36.6*/app/*36.9*/.getMemory)),format.raw/*36.19*/("""</td>
<td><a href=""""),_display_(Seq(/*37.15*/routes/*37.21*/.Application.showLogs(app.getName))),format.raw/*37.55*/("""">View Log</a></td> 
</tr>
""")))})),format.raw/*39.2*/("""
</table>
</center>   
 """)))})),format.raw/*42.3*/(""" 

    
    """))}
    }
    
    def render(email:String,resapp:List[org.cloudfoundry.client.lib.CloudApplication]) = apply(email,resapp)
    
    def f:((String,List[org.cloudfoundry.client.lib.CloudApplication]) => play.api.templates.Html) = (email,resapp) => apply(email,resapp)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Jun 15 13:39:45 PDT 2012
                    SOURCE: /Users/desaip/scalaApp1/app/views/apps.scala.html
                    HASH: 10513ffca5229e74c6168d7647394c258209f0d2
                    MATRIX: 555->1|826->76|858->204|874->212|908->214|1002->278|1016->284|1056->303|1186->402|1213->407|1269->433|1447->590|1630->743|1644->749|1657->753|1697->760|1738->771|1749->774|1779->782|1820->793|1831->796|1866->809|1907->820|1918->823|1960->843|2001->854|2012->857|2044->867|2095->887|2110->893|2166->927|2225->955|2281->980
                    LINES: 19->1|27->1|29->6|29->6|29->6|31->8|31->8|31->8|36->13|36->13|40->17|45->22|54->31|54->31|54->31|54->31|56->33|56->33|56->33|57->34|57->34|57->34|58->35|58->35|58->35|59->36|59->36|59->36|60->37|60->37|60->37|62->39|65->42
                    -- GENERATED --
                */
            