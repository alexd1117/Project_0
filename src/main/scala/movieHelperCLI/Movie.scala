package movieHelperCLI

import org.bson.types.ObjectId

case class Movie(_id: ObjectId, title: String, genre: String, rating: String) {

}

object Movie {
  def apply(title: String, genre: String, rating: String): Movie =  Movie(new ObjectId(), title, genre, rating)

}


