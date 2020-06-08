import Movie.{ChildrenMovie, NewReleaseMovie, RegularMovie}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CustomerSpec extends AnyFlatSpec with Matchers {
  behavior of "statement"

  it should "return single new release statement" in {
    val customer =
      Customer("Fred")
        .addRental(Rental(NewReleaseMovie("The Cell"), 3))

    customer.statement should equal(
      "Rental Record for Fred\n" +
        "\tThe Cell\t9.0\n" +
        "You owed 9.0\n" +
        "You earned 2 frequent renter points\n"
    )
  }

  it should "return dual new release statement" in {
    val customer =
      Customer("Fred")
        .addRental(Rental(NewReleaseMovie("The Cell"), 3))
        .addRental(Rental(NewReleaseMovie("The Tigger Movie"), 3))

    customer.statement should equal(
      "Rental Record for Fred\n" +
        "\tThe Cell\t9.0\n" +
        "\tThe Tigger Movie\t9.0\n" +
        "You owed 18.0\n" +
        "You earned 4 frequent renter points\n"
    )
  }

  it should "return single children statement" in {
    val customer =
      Customer("Fred")
        .addRental(Rental(ChildrenMovie("The Tigger Movie"), 3))

    customer.statement should equal(
      "Rental Record for Fred\n" +
        "\tThe Tigger Movie\t1.5\n" +
        "You owed 1.5\n" +
        "You earned 1 frequent renter points\n"
    )
  }

  it should "return multiple regular statement" in {
    val customer =
      Customer("Fred")
        .addRental(Rental(RegularMovie("Plan 9 from Outer Space"), 1))
        .addRental(Rental(RegularMovie("8 1/2"), 2))
        .addRental(Rental(RegularMovie("Eraserhead"), 3))

    customer.statement should equal(
      "Rental Record for Fred\n" +
        "\tPlan 9 from Outer Space\t2.0\n" +
        "\t8 1/2\t2.0\n" +
        "\tEraserhead\t3.5\n" +
        "You owed 7.5\n" +
        "You earned 3 frequent renter points\n"
    )
  }
}
