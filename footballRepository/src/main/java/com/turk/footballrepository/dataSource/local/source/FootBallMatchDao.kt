package com.turk.footballrepository.dataSource.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.turk.dtos.serverObjects.FootballMatchResponse

@Dao
interface FootBallMatchDao {

    @get:Query("SELECT * FROM FootballMatchResponse")
    val getAllFootballMatchesList: List<FootballMatchResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: FootballMatchResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addResult(items:  List<FootballMatchResponse>)

    @Query("DELETE FROM FootballMatchResponse")
    fun clearTable()

}