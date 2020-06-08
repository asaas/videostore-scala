sealed trait Movie {
  def title: String

  def calculateAmount(daysRented: Int): Double
  def calculateFrequentRenterPoint(daysRented: Int): Int
}

object Movie {
  case class NewReleaseMovie(
    title: String
  ) extends Movie {
    override def calculateAmount(
      daysRented: Int
    ): Double = daysRented * 3

    override def calculateFrequentRenterPoint(
      daysRented: Int
    ): Int =
      if (daysRented > 1) 2
      else 1
  }

  case class RegularMovie(
    title: String
  ) extends Movie {
    override def calculateAmount(
      daysRented: Int
    ): Double = {
      val baseAmount = 2

      if (daysRented > 2) baseAmount + (daysRented - 2) * 1.5
      else baseAmount
    }

    override def calculateFrequentRenterPoint(
      daysRented: Int
    ): Int = 1
  }

  case class ChildrenMovie(
    title: String
  ) extends Movie {
    override def calculateAmount(
      daysRented: Int
    ): Double = {
      val baseAmount = 1.5

      if (daysRented > 3) baseAmount + (daysRented - 3) * 1.5
      else baseAmount
    }

    override def calculateFrequentRenterPoint(
      daysRented: Int
    ): Int = 1
  }
}
