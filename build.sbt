name := "tagger"

version := "1.0"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.2"

libraryDependencies += "com.mpatric" % "mp3agic" % "0.8.2"



scoverage.ScoverageSbtPlugin.instrumentSettings

org.scoverage.coveralls.CoverallsPlugin.coverallsSettings //instrument coverage
