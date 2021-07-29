package com.turk.footballrepository.dataSource.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.turk.dtos.footballmatch.FootBallMatch;
import com.turk.footballrepository.dataSource.local.source.FootBallMatchDao;

@Database(entities = {FootBallMatch.class,}, version = 1, exportSchema = false)

public abstract class AppLocalDatabase extends RoomDatabase {


    public abstract FootBallMatchDao getFootBallMatchDao();

    

}


