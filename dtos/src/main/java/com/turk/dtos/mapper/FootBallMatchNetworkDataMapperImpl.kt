package com.turk.dtos.mapper

import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.dtos.footballmatch.FootballMatchDateTime
import com.turk.dtos.footballmatch.FootballTeam
import com.turk.dtos.serverObjects.FootballMatchResponse
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FootBallMatchNetworkDataMapperImpl : DataMapper<FootballMatchResponse,FootBallMatch> {

    override fun mapFromEntity(entity: FootballMatchResponse): FootBallMatch {

       val (teamAScore,teamBScore) =getTeamScores(entity.Score)
        return FootBallMatch(
            FootballTeam(entity.Team_A,entity.link_A,teamAScore),
            FootballTeam(entity.Team_B,entity.link_A,teamBScore),getDateTime(entity.Date))
    }

     fun mapFromEntity(dataList:List<FootballMatchResponse>):List<FootBallMatch>{

        return dataList.map {

             mapFromEntity(it)
         }


    }

    private fun getTeamScores( score:String):Pair<String,String>{

       val scoresArray= score.split("-")

        return Pair(scoresArray[0],scoresArray[1])
    }

    private fun getDateTime( dateTimeFromServer:String):FootballMatchDateTime{

        return FootballMatchDateTime(getDate(dateTimeFromServer),getTime(dateTimeFromServer),dateTimeFromServer)
    }

    private fun getDate(dateTime:String):String{

        val outputPattern = "dddd dd mmm"
        val inputPattern = "dd MMMM yyyy HH:mm"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)

        var date: Date? = null
        var str: String = dateTime

        try {
            date = inputFormat.parse(dateTime)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str

    }

    private fun getTime(dateTime:String):String{

        val outputPattern = "hh:mm a"
        val inputPattern = "dd MMMM yyyy HH:mm"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)

        var date: Date? = null
        var str: String = dateTime

        try {
            date = inputFormat.parse(dateTime)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str

    }
    //25 July 2021 21:00
}