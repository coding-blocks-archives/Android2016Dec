package in.championswimmer.volleygson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.championswimmer.volleygson.models.Post;

public class JsonDataActivity extends AppCompatActivity {

    Button btnSendReq;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    ArrayList<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data);
        final Gson gson = new Gson();

        requestQueue = Volley.newRequestQueue(this);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                "https://jsonplaceholder.typicode.com/posts",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        postList = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject postJsonObj = null;
                            try {
                                postJsonObj = response.getJSONObject(i);
                                postList.add(gson.fromJson(postJsonObj.toString(), Post.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //Post post = gson.fromJson(postJsonObj, Post.class);


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        btnSendReq = (Button) findViewById(R.id.btnSendReq);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPosts);

        btnSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(jsonArrayRequest);
            }
        });



    }
}
