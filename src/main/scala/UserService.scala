package main.scala

import user._

import scala.concurrent.Future

class UserService extends UserServiceGrpc.UserService{

  //  Create the users list
  var usersList: List[User] = List()


  override def registerUser(request: User): Future[Response] =
    Future.successful(addUser(request))

  override def removeUserById(request: UserNameId): Future[Response] =
    Future.successful(deleteUser(request))

  override def createWishlist(request: RequestCreateWishlist): Future[Response] =
    Future.successful(addWishList(request))

  override def deleteWishlist(request: RequestDeleteWishlist): Future[Response] = ???

  override def getWishlistById(request: RequestUserWishlist): Future[Wishlist] = ???

  override def getUserWishlists(request: UserNameId): Future[WishlistsResponse] = ???

  override def addProductToWishlist(request: RequestAddProductToWishlist): Future[Response] = ???

  override def removeProductFromWishlist(request: RequestRemoveProductFromWishlist): Future[Response] = ???


  //  All the private method!!!
  private def addUser(user: User): Response = {
    // IMPLEMENT!!!
    // Search user and add if it is not in the list.

    var successResponse = Response("User add successfully!")
    var errorResponse = Response("The user already exist!")

    return successResponse
  }

  private def deleteUser(userNameId: UserNameId): Response = {
    // IMPLEMENT!!!
    // Search user and remove it.

    var successResponse = Response("User delete successfully!")
    var errorResponse = Response("The user doesn't exist!")

    return successResponse
  }

  private def addWishList(request: RequestCreateWishlist) = {
    var successResponse = Response("Wishlist add successfully!")
    var errorResponse = Response("The wishlist already exist!")
    return successResponse
  }


}
