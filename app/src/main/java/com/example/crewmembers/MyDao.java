package com.example.crewmembers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addUser(CrewMember crewMember);

    @Query("select * from crews")
    public List<CrewMember> getUsers();

    @Query("delete from crews")
    public void emptydb();

}
