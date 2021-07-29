package com.turk.dtos.footballmatch

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FootBallMatch(val teamA:FootballTeam,
                         val teamB:FootballTeam,
                         val matchDateTime:FootballMatchDateTime
){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<FootBallMatch> =
            object : DiffUtil.ItemCallback<FootBallMatch>() {
                override fun areItemsTheSame(
                    @NonNull oldItem: FootBallMatch,
                    @NonNull newItem: FootBallMatch
                ): Boolean {
                    return oldItem.teamA == newItem.teamA
                }

                override fun areContentsTheSame(
                    @NonNull oldItem: FootBallMatch,
                    @NonNull newItem: FootBallMatch
                ): Boolean {
                    return oldItem == newItem
                }
            }


    }
}
