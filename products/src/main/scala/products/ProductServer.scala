package products

import io.grpc.ServerBuilder
import product.ProductServiceGrpc

import scala.concurrent.ExecutionContext

object ProductServer extends App {

  //  Creates the server.
  val productServer = ServerBuilder.forPort(8000)
    .addService(ProductServiceGrpc.bindService(new ProductService(), ExecutionContext.global))
    .build()

  //  Start the server.
  productServer.start()
  println("Product server running on port: " + productServer.getPort)
  productServer.awaitTermination()
}
