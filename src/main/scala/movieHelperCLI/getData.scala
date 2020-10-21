package movieHelperCLI

import com.mongodb.client.model.UpdateOptions
import org.mongodb.scala.{MongoClient, MongoCollection, Observable}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}
//Make Sure to start with imports on our own projects
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters._

class getData(mongoClient: MongoClient) {


/** A Data Access Object for Comics.  The goal of this class is to encapsulate
 * all the mongoDB related parts of retrieving Comics, so the rest of our
 * application doesn't have to concern itself with mongo. */



//  // we make getResults private, since it's not a functionality anyone should use
//  // ComicDao for.
//  def getResults[T](obs: Observable[T]): Seq[T] = {
//    Await.result(obs.toFuture(), Duration(10, SECONDS))
//  }
//
//  def printResults[T](obs: Observable[T]): Unit = {
//    getResults(obs).foreach(println(_))
//  }
//
//
//  def storeInDatabase(data_structure): Unit ={
//    for(i <- 0 to data_structure.length - 1)
//    collection.insertMany()
//  }



}
