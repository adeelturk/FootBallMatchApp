package com.turk.footballrepository.dataSource.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.turk.dtos.footballmatch.FootBallMatch

@Dao
interface FootBallMatchDao {

    @get:Query("SELECT * FROM FootBallMatch")
    val getAllFootballMatchesList: List<FootBallMatch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: FootBallMatch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addResult(items:  List<FootBallMatch>)

    @Query("DELETE FROM FootBallMatch")
    fun clearTable()

}