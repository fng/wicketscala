import sbt._

final class Project(info: ProjectInfo) extends ParentProject(info) {


   private def noAction = task {None}

  override def deliverLocalAction = noAction

  override def publishLocalAction = noAction

  override def publishAction = noAction


  lazy val core = project("core", "core", new Core(_))
  lazy val test = project("test", "test", new Test(_))

override def defaultModuleSettings = new EmptyConfiguration(projectID, ivyScala, ivyValidate)

    class Core(info: ProjectInfo) extends WicketScalaDefaults(info) {
     override def libraryDependencies = Set() ++ super.libraryDependencies
   }

   class Test(info: ProjectInfo) extends WicketScalaDefaults(info) {
     override def libraryDependencies = Set() ++ super.libraryDependencies
   }


}

abstract class WicketScalaDefaults(info: ProjectInfo) extends DefaultProject(info) with DependencyManagement{


  override def testFrameworks = super.testFrameworks ++ List(new TestFramework("com.novocode.junit.JUnitFrameworkNoMarker"))

  private val encodingUtf8 = List("-encoding", "UTF-8")

  override def compileOptions =
    encodingUtf8.map(CompileOption(_)) :::
            CompileOption("-no-specialization") ::
            target(Target.Java1_5) :: Unchecked :: super.compileOptions.toList

//  val htmlSources = super.mainScalaSourcePath ** "*.html"
//  val htmlSources2 = (super.mainScalaSourcePath ##) ** "*.html"
//
//  override def mainResources = super.mainResources +++ htmlSources

  override def managedStyle = ManagedStyle.Maven

  override def packageDocsJar = defaultJarPath("-javadoc.jar")

  override def packageSrcJar = defaultJarPath("-sources.jar")

  override def packageTestSrcJar = defaultJarPath("-test-sources.jar")

  lazy val sourceArtifact = Artifact(artifactID, "src", "jar", Some("sources"), Nil, None)

  lazy val docsArtifact = Artifact(artifactID, "docs", "jar", Some("javadoc"), Nil, None)

  override def packageToPublishActions = super.packageToPublishActions ++ Seq(packageDocs, packageSrc, packageTestSrc)




}

trait DependencyManagement {
  self: BasicScalaProject =>
  import Project._

  val specs = "org.scala-tools.testing" %% "specs" % "1.6.5" % "test" withSources
  val scalaz = "com.googlecode.scalaz" %% "scalaz-core" % "5.0" withSources
}