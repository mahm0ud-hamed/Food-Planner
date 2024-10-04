package com.example.yummy.Model.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yummy.Model.Pojos.MealDetails;

@Database(entities = {MealDetails.class}, version =  1)
/*this is abstarct class that you cant create any object from it */
public abstract class DataBase extends RoomDatabase {
    /*this is for the singltone*/
    private static DataBase dataBase = null ;
    public abstract MealDao getMealDAO();

    /* this method is to return an object form  calss that implement DataBaseDAO interface */
    public static synchronized DataBase getInstance(Context context){
        if(dataBase==null){
            dataBase = Room.databaseBuilder(context.getApplicationContext(),DataBase.class, "favourite_database").build();
        }
        return  dataBase;
    }

}