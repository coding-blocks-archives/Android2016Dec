package in.championswimmer.databases2.models;

/**
 * Created by championswimmer on 05/02/17.
 */

public class Todo {
    int id;
    String taskName;

    public Todo(int id, String taskName) {
        this.id = id;
        this.taskName = taskName;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }
}
