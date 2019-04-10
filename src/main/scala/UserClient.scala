package main.scala

import io.grpc.ManagedChannelBuilder
import product.{Product, ProductDetail, ProductId}
import user._

object UserClient extends App {

/*  For test purposes (Starts) */
  var testProduct1 = Product(
    Option(ProductId(1)), "Alfajor", Seq(ProductDetail("Price","15"),
      ProductDetail("Brand","Gualmayen")))

  var testProduct2 = Product(
    Option(ProductId(2)), "Alfajor", Seq(ProductDetail("Price","20"),
      ProductDetail("Brand","Jorjito")))

  var testWishList = WishList("WishListID", Seq(testProduct1))

  //  This user is already register on the system
  val testUser1 = User("ExistingUserID", "Test user already existing", Option(testWishList))
  //  This user does not exit on the system
  val testUser2 = User("NoExistingUserID", "Test user not existing", Option(testWishList))
/*  For test purposes (Ends) */


  // Creates the channel
  val channel = ManagedChannelBuilder.forAddress("localhost", 8000)
    .usePlaintext()
    .build()

  //  Blocking Call
  val blockingStub = UserServiceGrpc.blockingStub(channel)


/*  User registration and removing tests (Starts) */

  //  Adding a user that is already register.
 /* println(blockingStub.registerUser(testUser1).info)*/

  //  Adding a user that is not register.
 /* println(blockingStub.registerUser(testUser2).info)*/

  //  Removing an existing user.
  /*println(blockingStub.removeUserById(UserNameId("ExistingUserID")).info)

    //  Test that the user was removed. Should let the user register because there is no user with that id.
    println(blockingStub.registerUser(testUser1).info)*/

  //  Removing a non existing user.
 /* println(blockingStub.removeUserById(UserNameId("NO ID")).info)*/

/*  User registration and removing tests (Ends)  */

/* Wish list test (Starts)*/

  //  Gets a wish list from an existing user
 /* println(blockingStub.getUserWishList(testUser1).products)

  //  Gets a wish list from an non existing user
  println(blockingStub.getUserWishList(testUser2).products)*/

  //  Put a product into the user 1 wish list
 /* println("Wish List Before: ")
  println(blockingStub.getUserWishList(testUser1).products)

  var addRequest = RequestAddProductToWishList(Option(testProduct2), testUser1.userNameId)
  println(blockingStub.addProductToWishList(addRequest).info)

  println("Wish List After: ")
  println(blockingStub.getUserWishList(testUser1).products)*/

  // Deletes a existing product from a wish list

  /*val removeRequest = RequestRemoveProductFromWishList(Option(ProductId(1)), testUser1.userNameId)
  println("List before removing element: ")
  println(blockingStub.getUserWishList(testUser1))
  println(blockingStub.removeProductFromWishList(removeRequest).info)
  println("List after removing element: ")
  println(blockingStub.getUserWishList(testUser1))*/

}
