package com.ubaya.uts_160418058.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE tipe='Daily'")
    suspend fun selectAllDaily(): List<Todo>

    @Query("SELECT * FROM todo WHERE tipe='Weekly'")
    suspend fun selectAllWeekly(): List<Todo>

    @Query("SELECT * FROM todo WHERE tipe='Monthly'")
    suspend fun selectAllMonthly(): List<Todo>

    @Query("SELECT * FROM todo WHERE is_done='1'")
    suspend fun selectAllHistory(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title=:title, description=:description, category=:category,priority=:priority, photo_url=:photo_url, tipe=:tipe WHERE uuid =:id")
    suspend fun update(title:String?, description:String?, id:Int, category:String?, priority:String?, photo_url:String?,tipe:String?)


    @Query("UPDATE todo SET is_done=1 WHERE uuid=:id")
    suspend fun updatedone(id:Int)

    @Delete
    suspend fun deleteTodo(todo:Todo)

    @Query("DELETE FROM todo")
    suspend fun DeleteAll()

}
