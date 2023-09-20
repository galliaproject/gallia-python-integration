// gallia-python-integration

ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "io.github.galliaproject" // *must* match groupId for sonatype
ThisBuild / version      := "0.5.0"

// ===========================================================================
lazy val galliaVersion  = "0.5.0"
lazy val scalaPyVersion = "0.5.1"

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
  "io.github.galliaproject" %% "gallia-core"  % galliaVersion,
  "me.shadaj"               %% "scalapy-core" % scalaPyVersion) // to use python from Scala somewhat seamlessly      

// ===========================================================================
