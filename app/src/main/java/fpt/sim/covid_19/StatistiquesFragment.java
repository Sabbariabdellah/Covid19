package fpt.sim.covid_19;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatistiquesFragment extends Fragment {
    TextView confirmed;
    TextView death;
    TextView recovered;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistiques,container,false);
        confirmed = view.findViewById(R.id.confirmed);
        death = view.findViewById(R.id.death);
        recovered = view.findViewById(R.id.recovred);
        Date cDate = new Date(System.currentTimeMillis()-24*60*60*1000);
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        String url = "https://covidapi.info/api/v1/country/MAR/" + fDate;
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
        // getting JSON string from URL
        try {
            // Create a new HTTP Client
            DefaultHttpClient defaultClient = new DefaultHttpClient();
            // Setup the get request
            HttpGet httpGetRequest = new HttpGet(url);
            confirmed.setText("55622.88");
            // Execute the request in the client
            HttpResponse httpResponse = defaultClient.execute(httpGetRequest);


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String json = null;
            while((json = reader.readLine()) != null) {
                sb.append(json);
            }
            json = sb.toString();




            // Instantiate a JSON object from the request response
            JSONObject jsonObject = new JSONObject(json);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


}





