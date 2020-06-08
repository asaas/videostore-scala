case class Rental(
  movie: Movie,
  daysRented: Int
) {
  lazy val amount: Double =
    movie.calculateAmount(daysRented)

  lazy val frequentRenterPoints: Int =
    movie.calculateFrequentRenterPoint(daysRented)
}
