case class Customer(
  name: String,
  rentals: Seq[Rental] = Seq.empty
) {
  def addRental(rental: Rental): Customer = {
    copy(
      rentals = rentals :+ rental
    )
  }

  lazy val statement: String =
    header + rentalLines + footer

  private lazy val header: String =
    s"Rental Record for $name\n"

  private lazy val rentalLines =
    rentals.map { rental =>
      s"\t${rental.movie.title}\t${rental.amount}\n"
    }.mkString

  private lazy val footer: String =
    s"You owed $totalAmount\nYou earned $frequentRenterPoints frequent renter points\n"

  private lazy val totalAmount =
    rentals.foldLeft(0d)(_ + _.amount)

  private lazy val frequentRenterPoints =
    rentals.foldLeft(0)(_ + _.frequentRenterPoints)
}
