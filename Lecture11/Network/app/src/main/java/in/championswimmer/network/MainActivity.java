package in.championswimmer.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import in.championswimmer.network.models.Post;

public class MainActivity extends AppCompatActivity {

    EditText etUrl;
    Button btnGo;
//    TextView tvResult;
    ListView lvPosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = (EditText) findViewById(R.id.etUrl);
        btnGo = (Button) findViewById(R.id.btnGo);
//        tvResult = (TextView) findViewById(R.id.tvResult);
        lvPosts = (ListView) findViewById(R.id.lvPosts);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isConnected()) {
                    Toast.makeText(MainActivity.this,
                            "Not connected to internet",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //String myUrl = etUrl.getText().toString();
                new LoadUrlDataTask().execute("http://jsonplaceholder.typicode.com/posts");

            }
        });

    }

    class LoadUrlDataTask extends AsyncTask<String, Void, ArrayList<Post>> {

        @Override
        protected ArrayList<Post> doInBackground(String... params) {
            URL url = null;
            HttpURLConnection httpURLConnection = null;

            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(10000);
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            OutputStream os = null;
//            try {
//                os = httpURLConnection.getOutputStream();
//                os.write("hello".getBytes());
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            try {
                //int respCode = httpURLConnection.getResponseCode();

                InputStreamReader ir = new InputStreamReader(
                        httpURLConnection.getInputStream()
                );
                BufferedReader br = new BufferedReader(ir);
                StringBuilder sb = new StringBuilder();
                String str = null;
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }

                ArrayList<Post> respPostList = getPostListFromResponse(sb.toString());


                return respPostList;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Post> postList) {



            // show postList in listview
        }
    }


    ArrayList<Post> getPostListFromResponse (String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);

        ArrayList<Post> postArrayList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject postObj = jsonArray.getJSONObject(i);
            postArrayList.add(new Post(
                    postObj.getInt("userId"),
                    postObj.getInt("id"),
                    postObj.getString("title"),
                    postObj.getString("body")
            ));
        }

        return postArrayList;
    }

    boolean isConnected () {
        ConnectivityManager connMan =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }

        return false;

    }
}
