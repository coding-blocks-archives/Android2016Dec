package in.championswimmer.databases2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.championswimmer.databases2.db.tables.Todos;

/**
 * Created by championswimmer on 05/02/17.
 */

public class TodoDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Todos.db";
    public static final int DB_VER = 1;

    public interface Consts {
        String LBR = " ( ";
        String RBR = " ) ";
        String COMMA = " , ";
        String SEMICOL = " ; ";

        String TYPE_INT = " INTEGER ";
        String TYPE_TEXT = " TEXT ";
        String TYPE_PK = " PRIMARY KEY ";
        String TYPE_AI = " AUTOINCREMENT ";
    }


    public TodoDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Todos.CMD_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
