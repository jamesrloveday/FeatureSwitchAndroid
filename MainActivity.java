package features.application.com.featureswitchandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Features features;

    private Button firstBtn;
    private Button scndBtn;

    private String url = "http://192.168.0.3:6000/features";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstBtn = (Button) findViewById(R.id.button);
        firstBtn.setVisibility(View.INVISIBLE);

        scndBtn = (Button) findViewById(R.id.button2);
        scndBtn.setVisibility(View.INVISIBLE);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                executeFeaaturesRequest();
            }
        });
    }

    private void executeFeaaturesRequest() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL twitter = new URL(url);
                    URLConnection tc = twitter.openConnection();
                    BufferedReader in =
                            new BufferedReader(new InputStreamReader( tc.getInputStream()));

                    String line;
                    StringBuilder builder = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        builder.append(line);
                        Log.i("Response", line);
                    }
                    Gson gson = new Gson();
                    features = gson.fromJson(builder.toString(), Features.class);

                    if(features.getFeaturesList().contains("feature_1")) {
                        firstBtn.setVisibility(View.VISIBLE);
                    }
                    if(features.getFeaturesList().contains("feature_2")) {
                        scndBtn.setVisibility(View.VISIBLE);
                    }
                } catch (MalformedURLException e) {
                    Log.i("URLEXception", e.getMessage());
                } catch (IOException e) {
                    Log.i("IOException", e.getMessage());
                } catch(Exception ex) {
                    Log.i("Exception", ex.getMessage());
                }
            }
        });
    }
}
