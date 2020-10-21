package movieHelperCLI
import org.mongodb.scala.MongoClient

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex
import com.mongodb.client.model.{Filters, UpdateOptions}
import org.bson.types.ObjectId
import org.mongodb.scala.{MongoClient, MongoCollection, Observable}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}
import scala.io.StdIn
//Make Sure to start with imports on our own projects
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters._

class movieHelperCLI {

  val commandArgPattern : Regex = "(\\w+)\\s*(.*)".r
  var filename = ""
  var headers = Array("Title", "Genre", "Rating")
  var csvData  :ArrayBuffer[Array[String]] = ArrayBuffer.empty

  val client = MongoClient()
  val codecRegistry = fromRegistries(fromProviders(classOf[Movie]), MongoClient.DEFAULT_CODEC_REGISTRY)
  val db = client.getDatabase("movierankerdb").withCodecRegistry(codecRegistry) // this represents the database
  val collection: MongoCollection[Movie] = db.getCollection("Movie")

  //UpdateOptions object that has upsert set to true, for convenience
  val upsertTrue : UpdateOptions = (new UpdateOptions()).upsert(true)

  //prints out your welcome statement
  def printWelcome() : Unit = {
    println("Welcome to the Movie Helper Cli CSV Parser!")
  }

  def printOptions(): Unit = {
    println("[csv filename]: ")
    println("exit : exits the application")
    println("Enter an Option from above: ")

  }

  def parseCSV(filename : String): Unit ={
    val source = io.Source.fromFile(s"C:/Users/deleo/IdeaProjects/movieHelper/src/main/scala/$filename")
    println("CSV Read In")

    for(item <- source.getLines){
      var line = item.split(",").map(_.trim)
      csvData = csvData :+ line
    }
    println("Parse Done")
    println(csvData(0)(0))
  }

  // we make getResults private, since it's not a functionality anyone should use
  // ComicDao for.
  def getResults[T](obs: Observable[T]): Seq[T] = {
    Await.result(obs.toFuture(), Duration(10, SECONDS))
  }

  def printResults[T](obs: Observable[T]): Unit = {
    getResults(obs).foreach(println(_))
  }


  def storeInDatabase(): Unit ={
    for(i <- 0 to csvData.length - 1) {
      printResults(collection.insertOne(Movie(csvData(i)(0), csvData(i)(1), csvData(i)(2))))
    }
  }


  def runMovieHelper() : Unit = {
    printWelcome()

    var continueMenuLoop = true;
//    This loop will repeatedly prompt, listen, run code, and repeat
    while(continueMenuLoop) {
      printOptions()
      //get some user input with StdIn.readLine
      StdIn.readLine().toLowerCase() match {

        case "exit" => {
          continueMenuLoop = false

        }
//          println(FileUtil,getTextContent(arg))

        case "file" => {
            println("Enter Filename")
            filename = StdIn.readLine()
            //filename = "movieHelperCLI/Movie_Data.csv"
            parseCSV(filename)
            storeInDatabase()
        }
//          continueMenuLoop = false
        case "delete_all" => {
          // deleteAllFromDatabase()
        }

        case "detete_specific" =>
          val t = StdIn.readLine()
//          deleteSpecfic(t)

        case notRecognized => println(s"$notRecognized not a recognized command")

        //case // get Movie Title

//        case get rating over 10
      }
    }

    println("Program Done")
  }


}
