package users

import product.{Product, ProductDetail, ProductId}
import user._

import scala.collection.mutable
import scala.concurrent.Future

class UserService extends UserServiceGrpc.UserService{

  //  Tests Products
  var testProduct1 = Product(
    Option(ProductId(1)), "Alfajor", Seq(ProductDetail("Price","15"),
      ProductDetail("Brand","Gualmayen")))

  var testProduct2 = Product(
    Option(ProductId(1)), "Caramelo", Seq(ProductDetail("Price","2"),
      ProductDetail("Brand","Sugus")))

  //  Test wish list
  var testWishList = WishList("WishListID",Seq(testProduct1))

  //  Test user
  val user = User("ExistingUserID", "Test user already existing", Option(testWishList))

  //  Users
  var users: mutable.Map[String, User] = scala.collection.mutable.Map[String, User]()
  users.put("ExistingUserID", user)


  override def registerUser(request: User): Future[Response] =
    Future.successful(addUser(request))

  override def removeUserById(request: UserNameId): Future[Response] =
    Future.successful(deleteUser(request))


  /*  Methods NOT implemented */
  /*override def createWishList(request: RequestCreateWishList): Future[Response] =
    Future.successful(addWishList(request))

  override def deleteWishList(request: RequestDeleteWishList): Future[Response] =
    Future.successful(removeWishList(request))

  override def getWishListById(request: RequestUserWishList): Future[WishList] =
    Future.successful(getWishList(request))*/

  override def getUserWishList(request: User): Future[WishList] =
    Future.successful(getWishList(request))

  override def addProductToWishList(request: RequestAddProductToWishList): Future[Response] =
    Future.successful(putProductInWishList(request))

  override def removeProductFromWishList(request: RequestRemoveProductFromWishList): Future[Response] =
    Future.successful(deleteProductFromWishList(request))


  //  All the private method!!!
  private def addUser(user: User): Response = {
    var response = Response()

    //  Check if the user name id is used.
    if(users.keySet.contains(user.userNameId)){
      response = Response("The user already exist!")
    }
    else {
      //  Put the user in the map.
      users.put(user.userNameId, user)
      response = Response("User add successfully!")
    }
    response
  }

  private def deleteUser(userNameId: UserNameId): Response = {
    var response = Response()

    //  Check if the user name id exits
    if(users.keySet.contains(userNameId.id)){
      users.remove(userNameId.id)
      response = Response("User delete successfully!")
    }
    else {
      response = Response("The user doesn't exist!")
    }
    response
  }

  private def getWishList(request: User): WishList = {

    //  Checks if the user exists
    val currentUser: Option[User] = users.get(request.userNameId)

    // Double pattern matching because currentUser is an Option and wishList is also an Option
    currentUser match {
      case Some(u) =>
        val wishList: Option[WishList] = u.wishlist
        wishList match {
          case Some(w) =>
            w
          case None =>
            WishList()
        }
      case None =>
        WishList()
    }
  }

  /*  Methods NOT implemented */
  /* private def addWishList(request: RequestCreateWishList): Response = {
    // IMPLEMENT!!!


    var successResponse = Response("WishList add successfully!")
    var errorResponse = Response("The wish list already exist!")
    return successResponse
  }

  private def removeWishList(request: RequestDeleteWishList): Response = {
    //  IMPLEMENT!!!

    var successResponse = Response("WishList remove successfully!")
    var errorResponse = Response("The wish list doesn't exist!")
    return successResponse
  }*/

  /*private def getWishLists(id: UserNameId): WishListsResponse = {
    //  IMPLEMENT!!!
    //  LOGIC HERE!

    //  Test Product
    var testProduct = Product(
      Option(ProductId(1)), "Alfajor", Seq(ProductDetail("Price","15"),
        ProductDetail("Brand","Gualmayen")))

    //  Test Seq[Product]
    var testProductsList: List[Product] = List(testProduct)

    //  Test wish lists
    var testWishList1 = WishList("TEST WISHLIST 1",testProductsList)
    var testWishList2 = WishList("TEST WISHLIST 2",testProductsList)

    var testWishLists = WishListsResponse(List(testWishList1,testWishList2))

    return testWishLists
  }*/

  private def putProductInWishList(request: RequestAddProductToWishList): Response = {
    //  Checks if the user exists
    val currentUser: Option[User] = users.get(request.userId)

    // Double pattern matching because currentUser is an Option and wishList is also an Option
    currentUser match {
      case Some(u) =>
        val wishList: Option[WishList] = u.wishlist
        wishList match {
          case Some(w) =>
            //  Creates a seq with the new product
            val newProductSeq = w.products :+ request.product.get
            //  Creates a wish list with the new product
            val newWishList = WishList(w.name,newProductSeq)
            //  Updates the user wish list
            users.update(u.userNameId, User(u.userNameId,u.name, Option(newWishList)))
            Response("Product add to wish list successfully!")
          case None =>
            Response("An error occur when adding the product to the wish list!")
        }
      case None =>
        Response("An error occur when adding the product to the wish list!")
    }
  }

  private def deleteProductFromWishList(request: RequestRemoveProductFromWishList): Response = {
    //  Checks if the user exists
    val currentUser: Option[User] = users.get(request.userId)

    // Double pattern matching because currentUser is an Option and wishList is also an Option
    currentUser match {
      case Some(u) =>
        val wishList: Option[WishList] = u.wishlist
        wishList match {
          case Some(w) =>
            //  Creates a seq without the product
            val newProductSeq = Seq()
            //  For each product on the user wish list, checks if it is the product to delete. Creates a new seq
            //  without the product
            w.products.foreach( p =>
              if(p.id != request.productId){
                newProductSeq :+ p
               })
            users.update(u.userNameId,User(u.userNameId,u.name,Option(WishList(w.name,newProductSeq))))
            Response("Product deleted from wish list successfully!")
          case None =>
            Response("An error occur when deleting the product from the wish list!")
        }
      case None =>
        Response("An error occur when deleting the product from the wish list!")
    }
  }
}
