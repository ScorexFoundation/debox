import sbt.Keys.{fork, publishArtifact}

lazy val scalac: Seq[String] = Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-explaintypes",                     // Explain type errors in more detail.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
  "-language:higherKinds",             // Allow higher-kinded types
  "-language:implicitConversions",     // Allow definition of implicit functions called views
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
  // "-Ypartial-unification",             // Enable partial unification in type constructor inference
  // "-Ywarn-dead-code",                  // Warn when dead code is identified.
  // "-Ywarn-numeric-widen"              // Warn when numerics are widened.
  //"-Xlog-free-terms",
)

lazy val scalac211: Seq[String] = Seq(
  "-optimize",
  "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
  "-Xfuture",                          // Turn on future language features.
  "-Ywarn-dead-code",                  // Warn when dead code is identified.
  "-Ywarn-numeric-widen"               // Warn when numerics are widened.
)

lazy val scalac212: Seq[String] = Seq(
  "-release", "8",
  "-opt:simplify-jumps",
  "-opt:compact-locals",
  "-opt:copy-propagation",
  "-opt:box-unbox",
  "-opt:closure-invocations",
  "-opt:unreachable-code",
  "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
  "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals",              // Warn if a local definition is unused.
  "-Ywarn-unused:params",              // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates",            // Warn if a private member is unused.
  "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
  "-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
  "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
  "-Xlint:nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit",               // Warn when nullary methods return Unit.
  "-Xlint:option-implicit",            // Option.apply used implicit view.
  "-Xlint:package-object-classes",     // Class or object defined in package object.
  "-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Xfuture",                          // Turn on future language features.
  "-Ywarn-dead-code",                  // Warn when dead code is identified.
  "-Ywarn-numeric-widen"               // Warn when numerics are widened.
)

lazy val scalac213: Seq[String] = Seq(
  "-release", "8",
  "-opt:simplify-jumps",
  "-opt:compact-locals",
  "-opt:copy-propagation",
  "-opt:box-unbox",
  "-opt:closure-invocations",
  "-opt:unreachable-code",
  "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals",              // Warn if a local definition is unused.
  "-Ywarn-unused:params",              // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates",            // Warn if a private member is unused.
  "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
  "-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
  "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
  "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
  "-Xlint:option-implicit",            // Option.apply used implicit view.
  "-Xlint:package-object-classes",     // Class or object defined in package object.
  "-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
  "-Ywarn-dead-code",                  // Warn when dead code is identified.
  "-Ywarn-numeric-widen"               // Warn when numerics are widened.
)

lazy val scalac3: Seq[String] = Seq(
  "-source:3.0-migration" // makes the compiler forgiving on most of the dropped features, printing warnings in place of errors
)

lazy val scala213 = "2.13.16"
lazy val scala212 = "2.12.20"
lazy val scala211 = "2.11.12"
lazy val scala3 = "3.3.5"
lazy val scalatestVersion = "3.2.19"

organization := "org.scorexfoundation"

def deboxSettings = Seq(
  organization := "org.scorexfoundation",
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  homepage := Some(url("http://github.com/ScorexFoundation/debox")),
  description := "Fast, deboxed, specialized data structures for Scala (fork of non/debox)",

  resolvers += Resolver.sonatypeRepo("public"),
  libraryDependencies ++= {
    val base = Seq(
      "org.scalatest" %%% "scalatest" % scalatestVersion % Test,
      "org.scalatest" %%% "scalatest-propspec" % scalatestVersion % Test,
      "org.scalatest" %%% "scalatest-shouldmatchers" % scalatestVersion % Test
    )
    val reflect = Seq(scalaOrganization.value % "scala-reflect" % scalaVersion.value % "provided")
    val core211 = Seq(
      "org.typelevel" %%% "spire-macros" % "0.17.0-M1",
      "org.scalatestplus" %%% "scalacheck-1-15" % "3.2.4.0-M1" % Test,
      "org.scalacheck" %%% "scalacheck" % "1.15.2" % Test
    )
    val spireMacros = Seq("org.typelevel" %%% "spire-macros" % "0.17.0")
    val spire = Seq("org.typelevel" %%% "spire" % "0.18.0")
    val scalacheck = Seq(
      "org.scalatestplus" %%% "scalacheck-1-18" % "3.2.19.0" % Test,
      "org.scalacheck" %%% "scalacheck" % "1.18.1" % Test
    )
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 11)) =>
        base ++ core211
      case Some((2, 12)) =>
        base ++ scalacheck ++ spireMacros ++ reflect
      case Some((2, 13)) =>
        base ++ scalacheck ++ spireMacros ++ reflect
      case Some((3, _)) =>
        base ++ scalacheck ++ spire
    }
  },
  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 11)) =>
        scalac ++ scalac211
      case Some((2, 12)) =>
        scalac ++ scalac212
      case Some((2, 13)) =>
        scalac ++ scalac213
      case Some((3, _)) =>
        scalac ++ scalac3
    }
  },
  javacOptions ++= javacReleaseOption,
  publishMavenStyle := true,
  publishTo := sonatypePublishToBundle.value,
  pomExtra := (
    <developers>
      <developer>
        <id>aslesarenko</id>
        <name>Alexander Slesarenko</name>
        <url>https://github.com/aslesarenko/</url>
      </developer>
    </developers>
  ),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/ScorexFoundation/debox"),
      "scm:git@github.com:ScorexFoundation/debox.git"
    )
  )
)

lazy val noPublishSettings = Seq(
  publish := {},
  publishLocal := {},
  publishArtifact := false
)

lazy val core = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(moduleName := "debox")
  .settings(deboxSettings)
  .jvmSettings(
    scalaVersion := scala213,
    crossScalaVersions := Seq(scala211, scala212, scala213, scala3)
  )
  .jsSettings(
    scalaVersion := scala213,
    crossScalaVersions := Seq(scala212, scala213, scala3),
    Test / parallelExecution := false
  )

lazy val benchmark = project
    .in(file("benchmark"))
    .dependsOn(core.jvm)
    .settings(
      moduleName := "debox-benchmark",
      crossScalaVersions := Seq(scala211, scala212, scala213, scala3),
      scalaVersion := scala213,
    )
    .enablePlugins(JmhPlugin)
    .settings(Seq(
      run / javaOptions += "-Xmx3G",
      run / fork := true))
    .settings(noPublishSettings)

def javacReleaseOption = {
  if (System.getProperty("java.version").startsWith("1."))
  // java <9 "--release" is not supported
    Seq()
  else
    Seq("--release", "8")
}

// prefix version with "-SNAPSHOT" for builds without a git tag
ThisBuild / dynverSonatypeSnapshots := true
// use "-" instead of default "+"
ThisBuild / dynverSeparator := "-"

Test / publishArtifact := true
pomIncludeRepository := { _ => false }

val credentialFile = Path.userHome / ".sbt" / ".sigma-sonatype-credentials"
credentials ++= (for {
  file <- if (credentialFile.exists) Some(credentialFile) else None
} yield Credentials(file)).toSeq

credentials ++= (for {
  username <- Option(System.getenv().get("SONATYPE_USERNAME"))
  password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
} yield Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password)).toSeq


// PGP key for signing a release build published to sonatype
// signing is done by sbt-pgp plugin
// how to generate a key - https://central.sonatype.org/pages/working-with-pgp-signatures.html
// how to export a key and use it with Travis - https://docs.scala-lang.org/overviews/contributors/index.html#export-your-pgp-key-pair
pgpPublicRing := file("ci/pubring.asc")
pgpSecretRing := file("ci/secring.asc")
pgpPassphrase := sys.env.get("PGP_PASSPHRASE").map(_.toArray)
usePgpKeyHex("C1FD62B4D44BDF702CDF2B726FF59DA944B150DD")

