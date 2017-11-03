package com.kmitl.natcha58070069.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by student on 11/3/2017 AD.
 */

//cls ที่จะไว้เก็บ data / MessageInfo = 1 table / abstract class
@Database(entities = {MessageInfo.class}, version = 1)
public abstract class MessageDB extends RoomDatabase{
    public abstract MessageInfoDAO getMessageInfoDAO(); //Interface
}
