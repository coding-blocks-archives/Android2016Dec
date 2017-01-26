package in.championswimmer.volleygson.models;

/**
 * Created by championswimmer on 26/01/17.
 */

public class Post {
    public Post(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    int id;
    int userId;
    String title;
    String body;
}
