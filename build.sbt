name := "spark-essentials"

version := "0.2"

scalaVersion := "2.13.12"

val sparkVersion = "3.5.0"
val postgresVersion = "42.6.0"
val log4jVersion = "2.20.0"

// Resolver for Maven Central
resolvers ++= Seq(
  "Maven Central" at "https://repo1.maven.org/maven2/"
)

// Library Dependencies
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  // Logging dependencies
  "org.apache.logging.log4j" % "log4j-api" % log4jVersion,
  "org.apache.logging.log4j" % "log4j-core" % log4jVersion,
  // PostgreSQL for database connectivity
  "org.postgresql" % "postgresql" % postgresVersion,
  // MySQL connector (optional)
  "mysql" % "mysql-connector-java" % "8.0.33",
  // Testing framework
  "org.scalatest" %% "scalatest" % "3.2.12" % Test
)

// Set for ScalaTest 3.2.x
fork in Test := true
