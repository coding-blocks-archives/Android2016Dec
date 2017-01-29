package in.championswimmer.databases.dbutils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.championswimmer.databases.dbutils.tables.Courses;

/**
 * Created by championswimmer on 29/01/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DB_NAME =  "MyDatabase.db";
    public static final int DB_VER = 1;


    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Courses.CMD_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
