// gallia-python-integration

ThisBuild / scalaVersion := "3.3.1"
ThisBuild / organization := "io.github.galliaproject" // *must* match groupId for sonatype
ThisBuild / version      := "0.6.0-SNAPSHOT"

// ===========================================================================
lazy val scalaPyVersion = "0.5.2"

// ===========================================================================
lazy val `gallia-python-integration` =
    project
      .settings(
        //name := "gallia-python-integration"      
      )

  // ---------------------------------------------------------------------------
  lazy val `gallia-pandas` =
    project
      .dependsOn(`gallia-python-integration`)

  // ---------------------------------------------------------------------------
  lazy val `gallia-matplotlib` =
    project
      .dependsOn(`gallia-python-integration`)

    // ---------------------------------------------------------------------------
    lazy val `gallia-seaborn` =
      project
        .dependsOn(`gallia-pandas`)

      // ---------------------------------------------------------------------------
      lazy val `gallia-python-viz` =
        project
          .dependsOn(`gallia-matplotlib`)
          .dependsOn(`gallia-seaborn`)

// ===========================================================================
ThisBuild / libraryDependencies ++= Seq(
  "io.github.galliaproject" %% "gallia-core"  % version.value,
  "me.shadaj"               %% "scalapy-core" % scalaPyVersion) // to use python from Scala somewhat seamlessly      

// ===========================================================================
