package com.myapplicationdev.android.c346_l13_ps;

import androidx.appcompat.app.AppCompatActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    ListView lvTransport;
    AsyncHttpClient client;
    ArrayAdapter<Carpark> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTransport = findViewById(R.id.lvTransport);
        client = new AsyncHttpClient();
    }
    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList<Carpark>();

        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            int lots;
            int available;
            String carnum;
            String type;


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrCarpark = firstObj.getJSONArray("carpark_data");
                    for(int i = 0; i < jsonArrCarpark.length(); i++) {
                        JSONObject jsonObjcarpark = jsonArrCarpark.getJSONObject(i);
                        JSONArray jsonArrInfo = jsonObjcarpark.getJSONArray("carpark_info");
                        JSONObject firstObjcarpark = jsonArrInfo.getJSONObject(0);


                        lots = Integer.parseInt(firstObjcarpark.getString("total_lots"));
                        available =Integer.parseInt(firstObjcarpark.getString("lots_available"));
                        carnum = jsonObjcarpark.getString("carpark_number");
                        type=firstObjcarpark.getString("lot_type");

                        Carpark carpark = new Carpark(lots,type,available,carnum);
                        alCarpark.add(carpark);
                    }
                }
                catch(JSONException e){
                    Log.d("exception", e.toString());
                }

                //POINT X – Code to display List View
                ArrayAdapter<Carpark> adapter = new ArrayAdapter<Carpark>(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                lvTransport.setAdapter(adapter);

            }//end onSuccess
        });
    }//end onResume
}