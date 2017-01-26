package in.championswimmer.volleygson;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import in.championswimmer.volleygson.models.Course;
import in.championswimmer.volleygson.models.TestJson;

public class JsonOpsActivity extends AppCompatActivity {
    public static final String TAG = "JSON";

    Button btnReadJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_ops);
        final Gson gson = new Gson();

        btnReadJson = (Button) findViewById(R.id.btnReadJson);

        btnReadJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String jsonData = getJsonFileAsString("test.json");
                    TestJson testJson = gson.fromJson(jsonData, TestJson.class);

                    Log.d(TAG, "onClick: " + testJson.getCourses().get(0));


                } catch (Exception e) {
                    Log.e(TAG, "onClick: some problem with json parsing");
                }
            }
        });


    }

    String getJsonFileAsString (String filename) {
        try {
            AssetManager am = getAssets();
            InputStream is = am.open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String buf = null;
            StringBuilder sb = new StringBuilder();
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            return sb.toString();


        } catch (Exception e) {
            Log.e(TAG, "error reading file: ");
        }
        return "";

    }
}
