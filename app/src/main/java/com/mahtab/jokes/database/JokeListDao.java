package com.mahtab.jokes.database;import android.arch.persistence.room.Dao;import android.arch.persistence.room.Insert;import android.arch.persistence.room.OnConflictStrategy;import android.arch.persistence.room.Query;import java.util.List;import io.reactivex.Flowable;@Daopublic interface JokeListDao {    @Insert(onConflict = OnConflictStrategy.REPLACE)    void insert(List<EntityValue> valueEntities);/*    @Query("SELECT * FROM jokeListTable")    Flowable<List<EntityValue>> getAll();*/}