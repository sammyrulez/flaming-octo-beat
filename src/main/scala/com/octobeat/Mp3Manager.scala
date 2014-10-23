package com.octobeat

import com.mpatric.mp3agic.Mp3File

/**
 * Created by sam on 23/10/14.
 */
object Mp3Manager {

  def loadFile(path:String):Mp3File = {
      new Mp3File(path)
  }

  val passBy: PartialFunction[Mp3File, Mp3File] = {
    case mp3file: Mp3File => mp3file
  }

  val removeId3v1Tag: PartialFunction[Mp3File, Mp3File] = {
    case mp3file: Mp3File if mp3file.hasId3v1Tag => mp3file.removeId3v1Tag();mp3file
  }
  val removeId3v2Tag: PartialFunction[Mp3File, Mp3File] = {
    case mp3file: Mp3File if mp3file.hasId3v2Tag => mp3file.removeId3v2Tag();mp3file
  }
  val removeCustomTag: PartialFunction[Mp3File, Mp3File] = {
    case mp3file: Mp3File if mp3file.hasCustomTag => mp3file.removeCustomTag();mp3file
  }

  val _removeTags = ( removeId3v1Tag orElse passBy) andThen (removeId3v2Tag orElse passBy) andThen (removeCustomTag orElse passBy)

  def removeTags(mp3file : Mp3File):Mp3File = {
     _removeTags(mp3file)
  }

  val id3v1TagInfo : PartialFunction[Mp3File, Map[String,String]] = {
    case mp3file: Mp3File if mp3file.hasId3v1Tag => Map("Title" -> mp3file.getId3v1Tag.getTitle ,"Artist" -> mp3file.getId3v1Tag.getArtist )
  }
  val id3v2TagInfo : PartialFunction[Mp3File, Map[String,String]] = {
    case mp3file: Mp3File if mp3file.hasId3v2Tag => Map("Title" -> mp3file.getId3v2Tag.getTitle ,"Artist" -> mp3file.getId3v2Tag.getArtist )
  }

  val id3TagInfo = id3v1TagInfo orElse id3v2TagInfo orElse Map()

  def extractInfo(mp3file : Mp3File):Map[String,String] = {

    id3v2TagInfo(mp3file) //+ ("bitrate" -> mp3file.getBitrate)

  }

}
