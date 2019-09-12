package com.example.omdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private static final String JSON_URL = "http://www.omdbapi.com/?s=captain&apikey=d51966c1";
    ListView listView;
    EditText editText;
    Button search;
    String imdbid;
    List<MovieDetails> movieDetailsList;
    //List<MovieDetails> movieDetailsList11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.searchmovie);
        search = (Button) findViewById(R.id.buttonsrch);
        listView = (ListView) findViewById(R.id.listView);
        movieDetailsList = new ArrayList<>();
      //  movieDetailsList11 = new ArrayList<>();


        //this method will fetch and parse the data
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString();
                loadData(query);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Log.i("elemnt",String.valueOf(i));
               newpage(i);

            }
        });
    }
//movieDetailsList.get(i).imdbid
    private void newpage(int i)
    {
        //Toast.makeText(getApplicationContext(),i,Toast.LENGTH_LONG).show();
        //String imdbid = movieDetailsList.get(i).imdbid;
        String year = movieDetailsList.get(i).year;
      //  String des = movieDetailsList11.get(i).des;
        String name = movieDetailsList.get(i).title;
        String img = movieDetailsList.get(i).imageurl;
        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
      //  intent.putExtra("IMDBID",imdbid);
        intent.putExtra("YEAR",year);
     //  intent.putExtra("DES",des);
        intent.putExtra("IMDBIDD",imdbid);
        intent.putExtra("NAME",name);
        intent.putExtra("IMG",img);
        startActivity(intent);
    }


    private void loadData(String query)
    {
     //   movieDetailsList = null;

        String JSON_URL = "http://www.omdbapi.com/?s="+query+"&apikey=d51966c1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //getting the whole json object from the response
                             JSONObject obj = null;
                            obj = new JSONObject(response);
                            Log.i("dddddddddddddddddd",response);

                            JSONArray moviessArray = obj.getJSONArray("Search");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < moviessArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject moviessObject = moviessArray.getJSONObject(i);

                                //creating a moviess object and giving them the values from json object
                                MovieDetails movieDetails = new MovieDetails(moviessObject.getString("Title"),
                                        moviessObject.getString("Year"),moviessObject.getString("Poster"));

                            /*    MovieDetails movieDetails1 = new MovieDetails(moviessObject.getString("Title"),
                                        moviessObject.getString("Year"),moviessObject.getString("Poster"),
                                        moviessObject.getString("imdbID"));*/

                                imdbid = moviessObject.getString("imdbID");


                                //adding the moviess to movieDetails
                                movieDetailsList.add(movieDetails);
                             //   movieDetailsList11.add(movieDetails1);

                            }

                            //creating custom adapter object
                            ListViewAdapter adapter = new ListViewAdapter(movieDetailsList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
    }

