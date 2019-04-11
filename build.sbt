import sbt.Keys.libraryDependencies


// AGGREGATION

lazy val multiDocker = Project("multi-docker", file("."))
  .dependsOn(proto, users, products)
  .settings(
  )
  .aggregate(proto, users, products)


// USERS


lazy val users = project.in(file("users"))
  .dependsOn(proto)
  .settings(
    name := "users",
    mainClass in Compile := Some("users.UserServer")
  )
  .settings(UserApi.settings)
  .enablePlugins(JavaAppPackaging, DockerPlugin)


// PRODUCTS


lazy val products = project.in(file("products"))
  .dependsOn(proto)
  .settings(
    name := "products",
    mainClass in Compile := Some("products.ProductServer"),
    PB.protoSources in Compile := Seq(file("common/protobuf"))
  )
  //  .settings(Settings.common: _*)
  .settings(ProductApi.settings)
  .enablePlugins(JavaAppPackaging, DockerPlugin)


// COMMON (protos and grpc)


val circeVersion = "0.10.0"

lazy val proto = project.in(file("common"))
  .settings(
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value
    ),
    // The trick is in this line:
    PB.protoSources in Compile := Seq(file("protobuf")),

    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),

    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
      "com.thesamet.scalapb" %%% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion,
      "com.thesamet.scalapb" %%% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
    )
  )


