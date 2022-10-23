import org.scalajs.linker.interface.ModuleSplitStyle

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion := "3.2.0",
    scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature"),
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(
          ModuleSplitStyle.SmallestModules
        )
    },
    libraryDependencies := Seq(
      "com.raquo" %%% "laminar" % "0.14.2",
      "org.scala-js" %%% "scalajs-dom" % "2.2.0",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    ),
    Global / semanticdbEnabled := true,
    Global / onChangedBuildSource := ReloadOnSourceChanges
  )
