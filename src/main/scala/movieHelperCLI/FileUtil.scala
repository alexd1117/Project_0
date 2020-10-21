package movieHelperCLI

import scala.io.{BufferedSource, Source}

object FileUtil {

  def getTextContent(filename: String): Option[String] = {

    //the way we open files is using Source.fromFile.
    //you can write short version of opening + reading from a file
    //ours will be a little longer so we can properly close the file
    //we'll use a try finally for this

    //both of these are just to declare outside of the try block

    var openedFile : BufferedSource = null

    try {
      openedFile = Source.fromFile(filename)
      //return this:
      Some(openedFile.mkString(""))
    } finally {
      if(openedFile != null) openedFile.close
    }


  }



}
