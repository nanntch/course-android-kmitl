package com.kmitl.natcha58070069.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by student on 11/3/2017 AD.
 */

@Dao
interface MessageInfoDAO {

    @Insert
    void insert(MessageInfo messageInfo);

    @Query("SELECT * FROM MESSAGE_INFO") //ใช้เป็นชื่อคลาส หรือเทเบิ้ลก็ได้
    List<MessageInfo> findAll();
}
