
import sbtide.Keys.ideSkipProject
import org.jetbrains.sbtidea.Keys._

name := "taggedtypes-intellij-plugin"

lazy val plugin = project.in(file("."))
  .settings(
    scalaVersion := "2.12.8",
    
//    ideaPluginName := "ScalaTaggedTypesPlugin",
//    ideaBuild := "183.4284.148",
//    ideaDownloadSources := true,
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
    ideaExternalPlugins += IdeaPlugin.Zip("Scala", url("https://plugins.jetbrains.com/files/1347/52174/scala-intellij-bin-2018.3.4.zip")),
    ideExcludedDirectories := Seq(baseDirectory.value / "target")
  )

lazy val ideaRunner = createRunnerProject(plugin, "idea-runner")