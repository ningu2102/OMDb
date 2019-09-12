package com.example.omdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    private String URL="";
    ImageView imageView;
    TextView namee,yearr,desc;
    String plot,rating;
    RatingBar simpleRatingBar;
    int rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        plot="";

   //    String imdbID=getIntent().getStringExtra("IMDBID");
        String imdbIDd=getIntent().getStringExtra("IMDBIDD");
        String name=getIntent().getStringExtra("NAME");
        String img=getIntent().getStringExtra("IMG");
        //String des=getIntent().getStringExtra("DES");
        String year=getIntent().getStringExtra("YEAR");
       Toast.makeText(getApplicationContext(),imdbIDd,Toast.LENGTH_LONG).show();
        imageView = (ImageView) findViewById(R.id.photo);
        namee=(TextView) findViewById(R.id.namemovie);
        yearr=(TextView) findViewById(R.id.yearmovie);
        desc=(TextView) findViewById(R.id.description);
        simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar);

        namee.setText(name);
        yearr.setText(year);
       // desc.setText(des);
        Picasso.with(getApplicationContext()).load(img).into(imageView);

       URL = "http://www.omdbapi.com/?i="+imdbIDd+"&apikey=d51966c1";
       loaddata(URL);



    }

    private void loaddata(String url)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    plot = obj.getString("Plot");
                    rating = obj.getString("imdbRating");
                    desc.setText(plot);
                   Float rate = Float.parseFloat(rating);
                    rate=rate/2;

                    setstar(rate);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            private void setstar(Float rate)
            {
                Log.i("ffffffffffffffffffff",String.valueOf(rate));
                simpleRatingBar.setRating(rate);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
