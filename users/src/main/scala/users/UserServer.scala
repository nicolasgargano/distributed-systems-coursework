package users

import io.grpc.ServerBuilder
import user.UserServiceGrpc

import scala.concurrent.ExecutionContext

object UserServer extends App{

  //  Creates the server
  val userServer = ServerBuilder.forPort(8000)
    .addService(UserServiceGrpc.bindService(new UserService(), ExecutionContext.global ))
    .build()

  //  Start the server
  userServer.start()
  println("User server running on port: " + userServer.getPort)
  userServer.awaitTermination()

}
