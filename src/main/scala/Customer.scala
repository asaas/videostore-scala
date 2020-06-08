import scala.collection.mutable.ArrayBuffer

class Customer(private var name: String) {
  private var rentals: ArrayBuffer[Rental] = ArrayBuffer()

  def addRental(rental: Rental): Unit = {
    rentals += rental
  }

  def statement(): String = {
    var totalAmount = 0d
    var frequentRenterPoints = 0
    var result = "Rental Record for " + name + "\n"

    for(each <- rentals) {
      var thisAmount = 0d

      each.movie.priceCode match {
        case Movie.REGULAR =>
          thisAmount += 2
          if (each.daysRented > 2)
            thisAmount += (each.daysRented - 2) * 1.5
        case Movie.NEW_RELEASE =>
          thisAmount += each.daysRented * 3
        case Movie.CHILDREN =>
          thisAmount += 1.5
          if (each.daysRented > 3)
            thisAmount += (each.daysRented - 3) * 1.5
      }

      frequentRenterPoints += 1
      if ((each.movie.priceCode == Movie.NEW_RELEASE) && each.daysRented > 1)
        frequentRenterPoints += 1

      result += "\t" + each.movie.title + "\t" + String.valueOf(thisAmount) + "\n"
      totalAmount += thisAmount
    }

    result += "You owed " + String.valueOf(totalAmount) + "\n"
    result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n"

    result
  }
}
