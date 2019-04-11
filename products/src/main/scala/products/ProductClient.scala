package products

import io.grpc.ManagedChannelBuilder
import product.{Product, ProductId, ProductServiceGrpc}

object ProductClient extends App {

  //  Creates the channel.
  val channel = ManagedChannelBuilder.forAddress("localhost", 8080)
    .usePlaintext()
    .build()

  //  Var use for the console interaction
  var exit = false


  while (!exit){
    //  Read product id (as integer) from command line.
    print("Please enter the product id (integer number) or enter -1 to exit: ")
    val selection = readInt()

    //  Continue until -1 is enter on the command line.
    if (selection != -1){
      val request = ProductId(selection)

      //  Blocking call
      val blockingStub = ProductServiceGrpc.blockingStub(channel)
      val reply: Product = blockingStub.requestProductById(request)

      //  Print the results.
      if (reply.getId == ProductId(-1)){
        println("There is not any product with that id.")
      }
      else {
        println("Product found. The details are below.")
        println("Name: " + reply.name)
        reply.details.foreach(detail => println(detail.detailName + ": " + detail.detailInfo))
      }
    }
    else exit = true
  }
}
