package com.ubaya.uts_160418058.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String?,
    @ColumnInfo(name="description")
    var description:String?,
    @ColumnInfo(name="time")
    var time:Int,
    @ColumnInfo(name="category")
    var category:String?,
    @ColumnInfo(name="priority")
    var priority:String?,
    @ColumnInfo(name="photo_url")
    var photo_url:String?,
    @ColumnInfo(name="tipe")
    var tipe:String?,
    @ColumnInfo(name="is_done")
    var is_done:Int
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

//data class weekly(
//    val title:String?,
//    val description:String?,
//    val date:String?,
//    val category:String?,
//    val priority:String?,
//    val photo_url:String?,
//    var is_done:Int
//)
//
//data class monthly(
//    val title:String?,
//    val description:String?,
//    val month:String?,
//    val category:String?,
//    val priority:String?,
//    val photo_url:String?,
//    var is_done:Int
//)
//data class history(
//    val title:String?,
//    val date:String?,
//    val category:String?,
//    val priority:String?,
//    val photo_url:String?,
//    var is_done:Int
//)