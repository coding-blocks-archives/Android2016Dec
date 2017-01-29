package in.championswimmer.databases.dbutils.tables;

import android.database.sqlite.SQLiteDatabase;

import in.championswimmer.databases.dbutils.DBConsts.*;

import static in.championswimmer.databases.dbutils.DBConsts.*;

/**
 * Created by championswimmer on 29/01/17.
 */

public class Courses {

    public static final String TABLE_NAME = "courses";

    public static final String COL_ID = "course_id";
    public static final String COL_NAME = "course_name";
    public static final String COL_FEES = "course_fees";


    public static final String CMD_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    LBR +
                    COL_ID + TYPE_INT + TYPE_PK +
                    COL_NAME + TYPE_TEXT +
                    RBR + SEMCOL;

    public static void createCourse (String courseName,
                                     int fees,
                                     SQLiteDatabase db) {



    }
}
