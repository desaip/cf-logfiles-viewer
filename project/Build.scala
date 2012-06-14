import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "testApp"
    val appVersion      = "1.0-SNAPSHOT"


    val appDependencies = Seq(
      // Add your project dependencies here,

		"org.cloudfoundry" % "cloudfoundry-client-lib" % "0.7.3"
    )
        


    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here    
		resolvers += "SpringSource snapshots" at "http://maven.springframework.org/snapshot", 
		resolvers += "SpringSource milestones" at "http://maven.springframework.org/milestone"
		
    )

}
