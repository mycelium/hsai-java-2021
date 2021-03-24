name := "funsets"

scalaVersion := "2.12.7"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-optimise"//,
  //"-Yinline-warnings" // -- что это такое???? Такой опции нет вообще!
)

fork := true

javaOptions += "-Xmx2G"

parallelExecution in Test := false
