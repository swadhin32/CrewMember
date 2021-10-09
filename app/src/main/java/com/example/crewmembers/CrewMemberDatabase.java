package com.example.crewmembers;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CrewMember.class}, version =1)
public abstract class CrewMemberDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
