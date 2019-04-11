package products

import product.{Product, ProductDetail, ProductId, ProductServiceGrpc}

import scala.concurrent.Future

class ProductService extends ProductServiceGrpc.ProductService{

  //  Create 3 product to test
  var product1 = Product(Option(ProductId(1)), "Alfajor", Seq(ProductDetail("Price","15"),
    ProductDetail("Brand","Gualmayen")))
  var product2 = Product(Option(ProductId(2)), "Turron", Seq(ProductDetail("Price","10"),
    ProductDetail("Brand","Arcor")))
  var product3 = Product(Option(ProductId(3)), "Gaseosa", Seq(ProductDetail("Price","10"),
    ProductDetail("Brand","CocaCola")))

  //  Create the product list
  var productsList: List[Product] = List(product1, product2, product3)

  //  Override the method required for the product search by id service.
  override def requestProductById(request: ProductId): Future[Product] =
    Future.successful(findProductById(request))

  private def findProductById(id: ProductId): Product =  {
    //  Search the product by id
    val product: Option[Product] = productsList.find{p => p.getId == id}

    //  In the case that the product is found it returns it.
    //  In the case that the product is NOT found it returns a Product Object with the id -1.
    product match {
      case Some(p) =>
        p
      case None =>
        Product(Option(ProductId(-1)), "none", Seq())
    }
  }
}
