package in.championswimmer.databases2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import in.championswimmer.databases2.db.TodoDbHelper;
import in.championswimmer.databases2.db.tables.Todos;
import in.championswimmer.databases2.models.Todo;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MA";

    Button addButton;
    EditText etNewTodo;
    RecyclerView todoListView;
    TodoRecyclerViewAdapter todoAdapter;
    TodoDbHelper dbHelper;

    ArrayList<Todo>  todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TodoDbHelper(this);

        addButton = (Button) findViewById(R.id.btnAdd);
        etNewTodo = (EditText) findViewById(R.id.etNewTodo);
        todoListView = (RecyclerView) findViewById(R.id.listTodos);

        todoAdapter = new TodoRecyclerViewAdapter(this, todos);

        todoListView.setLayoutManager(new LinearLayoutManager(this));
        todoListView.setAdapter(todoAdapter);

        refreshTodos();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todos.addTask(
                        etNewTodo.getText().toString(),
                        dbHelper.getWritableDatabase()
                );
                refreshTodos();

            }
        });

    }

    void refreshTodos () {
        todos = Todos.getAllTasks(dbHelper.getReadableDatabase());
        Log.d(TAG, "refreshTodos: " + todos.size());
        todoAdapter.updateTodos(todos);
    }
}
