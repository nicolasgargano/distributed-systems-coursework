logLevel := Level.Warn

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.8.2"

addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.19")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.4")