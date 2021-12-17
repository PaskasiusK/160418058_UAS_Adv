package com.ubaya.uts_160418058.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.model.TodoDatabase
import java.lang.Exception
val DB_NAME = "newtododb"
fun buildDb(context: Context): TodoDatabase {
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME)
//       .addMigrations(MIGRATION_1_2)
        .build()
    return db
}
//val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "DROP TABLE time ;")
//    }
//}
@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoUrl(view:ImageView,url: String?,progressBar: ProgressBar)
{
    view.loadImage(url,progressBar)
}
fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }


        })
}