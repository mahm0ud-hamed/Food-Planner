package com.example.yummy.Model.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;

@Database(entities = {MealDetails.class , MealPlan.class}, version = 4)

/*this is abstarct class that you cant create any object from it */
public abstract class DataBase extends RoomDatabase {
    /*this is for the singltone*/
    private static DataBase dataBase = null ;
    public abstract favouriteDao getMealDAO();
    public abstract WeekPlanDao getWeekPlanDao();

    /* this method is to return an object form  calss that implement DataBaseDAO interface */
    public static synchronized DataBase getInstance(Context context){
        if(dataBase==null){
            dataBase = Room.databaseBuilder(context.getApplicationContext(),DataBase.class, "favourite_database").fallbackToDestructiveMigration().build();
        }
        return  dataBase;
    }

}