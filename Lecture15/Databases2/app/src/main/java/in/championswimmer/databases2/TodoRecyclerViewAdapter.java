package in.championswimmer.databases2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.championswimmer.databases2.models.Todo;

/**
 * Created by championswimmer on 05/02/17.
 */

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder> {

    private ArrayList<Todo> todoList;
    private Context context;

    public TodoRecyclerViewAdapter(Context ctx, ArrayList<Todo> todos) {
        this.context = ctx;
        this.todoList = todos;
    }


    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View todoItemView = li.inflate(android.R.layout.simple_list_item_1, parent, false);


        return new TodoViewHolder(todoItemView);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {

        holder.tvTask.setText(todoList.get(position).getTaskName());


    }

    @Override
    public int getItemCount() {
        if (todoList == null) {
            return 0;
        }
        return todoList.size();
    }

    public void updateTodos (ArrayList<Todo> todos) {
        this.todoList = todos;
        notifyDataSetChanged();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView tvTask;

        public TodoViewHolder(View itemView) {
            super(itemView);

            tvTask = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
