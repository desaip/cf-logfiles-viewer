
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
object logs extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(file1:String, file2:String, appName:String):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.46*/("""
"""),_display_(Seq(/*2.2*/main("")/*2.10*/{_display_(Seq(format.raw/*2.11*/("""
<body onload="timer()">

<div align="right"><a href=""""),_display_(Seq(/*5.30*/routes/*5.36*/.Application.logout)),format.raw/*5.55*/("""">Log Out</a></div>
<h4 align="center"> Logs For <span id="appname">"""),_display_(Seq(/*6.50*/appName)),format.raw/*6.57*/("""</span>: </h4>

<div style="width:100%; height:100%;">

<div style="width:100%; height:50%;">

<span id="stderr">Standard Error Logs</span>
<br>
<pre>
"""),_display_(Seq(/*15.2*/file1)),format.raw/*15.7*/("""
</pre> 
<br>
</div>

<hr>

<div style="width:100%; height:50%;">
<span id="stdout">Standard Output Logs</span>
<br>
<pre>
"""),_display_(Seq(/*26.2*/file2)),format.raw/*26.7*/("""
</pre>
</div>

</div>

<script type="text/javascript">
function timer()
"""),format.raw("""{"""),format.raw/*34.2*/("""
setInterval(function()"""),format.raw("""{"""),format.raw/*35.24*/("""updateLogs()"""),format.raw("""}"""),format.raw/*35.37*/(""",10000);
"""),format.raw("""}"""),format.raw/*36.2*/("""

function updateLogs()
"""),format.raw("""{"""),format.raw/*39.2*/("""
	window.location = """"),_display_(Seq(/*40.22*/routes/*40.28*/.Application.showLogs(appName))),format.raw/*40.58*/("""" 
"""),format.raw("""}"""),format.raw/*41.2*/("""
</script>
</body>
 """)))})),format.raw/*44.3*/("""  """))}
    }
    
    def render(file1:String,file2:String,appName:String) = apply(file1,file2,appName)
    
    def f:((String,String,String) => play.api.templates.Html) = (file1,file2,appName) => apply(file1,file2,appName)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Jun 15 13:35:17 PDT 2012
                    SOURCE: /Users/desaip/scalaApp1/app/views/logs.scala.html
                    HASH: 062fe95dd9e335cc4a8a1f317de3b003ab25d539
                    MATRIX: 518->1|634->45|665->47|681->55|714->56|799->111|813->117|853->136|952->205|980->212|1162->364|1188->369|1342->493|1368->498|1488->572|1559->596|1619->609|1675->619|1746->644|1799->666|1814->672|1866->702|1916->706|1968->727
                    LINES: 19->1|22->1|23->2|23->2|23->2|26->5|26->5|26->5|27->6|27->6|36->15|36->15|47->26|47->26|55->34|56->35|56->35|57->36|60->39|61->40|61->40|61->40|62->41|65->44
                    -- GENERATED --
                */
            