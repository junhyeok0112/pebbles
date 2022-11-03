package com.example.pebbles.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.data.remote.model.Habit
import com.google.gson.Gson


@Database(entities = [Habit::class], version = 2, exportSchema = false)
@TypeConverters(value = [TodoListTypeConverter::class])
abstract class PEBBLEDataBase : RoomDatabase() {

    abstract fun habitDao(): HabitDAO

    //DB를 싱글톤으로 만들어서 메모리 관리해야함 -> 이 때 Application에 따로 할필요 없이 여기서 Companion object로 처리 -> 공식문서
    //이 DB.DAO로 접근 가능
    companion object {

        val gson = Gson()
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `new_Habit_List`(`cons_days` INTEGER NOT NULL," +
                        " `end` TEXT NOT NULL," +
                        " `id` INTEGER NOT NULL, " +
                        "`name` TEXT NOT NULL," +
                        " `seq` INTEGER NOT NULL," +
                        " `start` TEXT NOT NULL," +
                        " `status` TEXT NOT NULL," +
                        " `today` TEXT NOT NULL," +
                        " `today_status` TEXT NOT NULL," +
                        " `todos` TEXT NOT NULL," +
                        " `weeks` TEXT NOT NULL," +
                        " PRIMARY KEY(`id`) )")

                database.execSQL("INSERT INTO new_Habit_List(cons_days," +
                        " `end`," +
                        " id," +
                        " name," +
                        " seq," +
                        " start," +
                        " status," +
                        " today," +
                        " today_status," +
                        " todos," +
                        " weeks)" +
                        " SELECT cons_days, `end`, id, name, seq ,start ,status,today ,today_status,todos ,weeks FROM Habit_List")

                //drop old table
                database.execSQL("DROP TABLE Habit_List")

                //rename new table to the old table name
                database.execSQL("ALTER TABLE new_Habit_List RENAME TO Habit_List")
            }
        }

        fun getInstance(context: Context): PEBBLEDataBase {

            return Room.databaseBuilder(
                context,
                PEBBLEDataBase::class.java, "pebble_database"
            ).addTypeConverter(TodoListTypeConverter(gson)).addMigrations(MIGRATION_1_2).build()
        }

    }

}