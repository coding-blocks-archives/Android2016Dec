package in.championswimmer.databases2.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import in.championswimmer.databases2.db.TodoDbHelper;
import in.championswimmer.databases2.models.Todo;

import static in.championswimmer.databases2.db.TodoDbHelper.Consts.*;

/**
 * Created by championswimmer on 05/02/17.
 */

public class Todos {

    public static final String TAG = "TODOS";

    public static final String TABLE_NAME = "todos";

    public interface Columns {
        String ID = "todo_id";
        String TASK = "todo_task";
        String DONE = "todo_done";
    }

    public static final String CMD_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    LBR +
                    Columns.ID + TYPE_INT + TYPE_PK + TYPE_AI + COMMA +
                    Columns.TASK + TYPE_TEXT +
                    RBR + SEMICOL;

    public static final String CMD_UPDATE_1_2_ADD_COL =
            "ALTER TABLE " + TABLE_NAME +
                    " ADD " +
                    Columns.DONE + TYPE_BOOLEAN + SEMICOL;


    public static boolean addTask (String task, SQLiteDatabase db) {
        if (db.isReadOnly()) {
            return false;
        }

        ContentValues taskObj = new ContentValues();
        taskObj.put(Columns.TASK, task);

        db.insert(TABLE_NAME, null, taskObj);
        db.close();
        return true;

    }

    public static boolean updateTaskAsDone(int taskId, SQLiteDatabase db) {
        if (db.isReadOnly()) {
            return false;
        }

        ContentValues todoObj = new ContentValues();
        todoObj.put(Columns.TASK, "changed task");
        String whereClause = Columns.ID + " = ?";

        db.update(
                TABLE_NAME,
                todoObj,
                whereClause,
                new String[] {
                        String.valueOf(taskId)
                }
        );
        return true;
    }

    public static boolean removeTask (int taskId, SQLiteDatabase db) {

        if (db.isReadOnly()) {
            return false;
        }
        String whereClause = Columns.ID + " = ?";

        db.delete(TABLE_NAME,
                whereClause,
                new String[] {
                        String.valueOf(taskId)
                });
        return true;
    }

    public static ArrayList<Todo> getAllTasks (SQLiteDatabase db) {
        String[] PROJECTION = {
                Columns.ID, Columns.TASK
        };


        Cursor cur = db.query(
                TABLE_NAME,
                PROJECTION,
                null, null, null, null, null
        );

        ArrayList<Todo> todos = new ArrayList<>();
        cur.moveToFirst();
        int idIndex = cur.getColumnIndex(Columns.ID);
        int taskIndex = cur.getColumnIndex(Columns.TASK);

        while(cur.moveToNext()) {
            Log.d(TAG, "getAllTasks: " +  cur.getString(taskIndex));
            todos.add(
                    new Todo(
                            cur.getInt(idIndex),
                            cur.getString(taskIndex)
                    )
            );
        }
        cur.close();

        return todos;
    }

}
