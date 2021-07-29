package com.turk.footballrepository.dataSource.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.turk.dtos.footballmatch.FootBallMatch;
import com.turk.dtos.serverObjects.FootballMatchResponse;
import com.turk.footballrepository.dataSource.local.source.FootBallMatchDao;

@Database(entities = {FootballMatchResponse.class,}, version = 1, exportSchema = false)

public abstract class AppLocalDatabase extends RoomDatabase {


    public abstract FootBallMatchDao getFootBallMatchDao();

    

}


