package com.turk.dtos.serverObjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FootballMatchResponse(val Team_A:String,
                                 val Team_B:String,
                                 val Score:String,
                                 val link_A:String,
                                 val link_B:String,
                                 val Date:String,
){


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

