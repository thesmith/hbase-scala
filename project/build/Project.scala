import sbt._

class BowlerProject(info: ProjectInfo) extends DefaultWebProject(info) with IdeaProject {
  val bowler = "org.bowlerframework" %% "core" % "0.5-SNAPSHOT"

  val slf4jVersion = "1.6.0"
  val jettyVersion = "7.4.1.v20110513"

  val sfl4jnop = "org.slf4j" % "slf4j-nop" % slf4jVersion % "runtime"

  // allows you to use an embedded/in-JVM jetty-server to run unit-tests.
  val scalatraTest = "org.scalatra" %% "scalatra-scalatest" % "2.0.0-SNAPSHOT" % "test"

  val jetty7 = "org.eclipse.jetty" % "jetty-server" % jettyVersion % "compile,test -> default"
  val jettyWebapp = "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "compile,test -> default"

  val servletApi = "javax.servlet" % "servlet-api" % "2.5" % "provided"

  val hbase = "org.apache.hbase" % "hbase" % "0.90.1-cdh3u0"
  val hadoop = "org.apache.hadoop" % "hadoop-core" % "0.20.2-cdh3u0"
  override def ivyXML =
    <dependencies>
      <dependency org="org.apache.hbase" name="hbase" rev="0.90.1-cdh3u0">
        <exclude module="hadoop-core"/>
        <exclude module="jruby-complete"/>
        <exclude module="jetty"/>
        <exclude module="jasper-compiler"/>
        <exclude module="jsp-2.1"/>
        <exclude module="jsp-api-2.1"/>
        <exclude module="jetty-util"/>
        <exclude module="jasper-runtime"/>
        <exclude module="jersey-server"/>
        <exclude module="jetty-util"/>
      </dependency>
      <dependency org="org.apache.hadoop" name="hadoop-core" rev="0.20.2-cdh3u0">
        <exclude module="jetty"/>
        <exclude module="jasper-compiler"/>
        <exclude module="jasper-runtime"/>
        <exclude module="jetty-util"/>
      </dependency>
    </dependencies>


  val mavenLocal = "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"

  val sonatypeNexusSnapshots =
    "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  val sonatypeNexusReleases =
    "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases"

  val thriftRepo = "Thrift repo" at "http://people.apache.org/~rawson/repo"
  val javaRepo = "Java repo" at "http://download.java.net/maven/2/"
  val clouderaRepo = "Cloudera repo" at "https://repository.cloudera.com/content/repositories/releases/"
  val scalaToolsRepo = "Scala-Tools repo" at "http://scala-tools.org/repo-releases/"
  val fuseSourceSnapshots = "FuseSource Snapshot Repository" at "http://repo.fusesource.com/nexus/content/repositories/snapshots"
  val scalaToolsnapshots = "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"
}
