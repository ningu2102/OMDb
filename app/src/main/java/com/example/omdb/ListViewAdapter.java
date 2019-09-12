package com.example.omdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.example.omdb.R;
import com.squareup.picasso.Picasso;

import static com.example.omdb.R.layout.movie_layout_view;

public class ListViewAdapter extends ArrayAdapter<MovieDetails>
{
    private List<MovieDetails> movieDetailsList;
    private Context context;

    public ListViewAdapter(List<MovieDetails> movieDetailsList, Context context) {
        super(context, movie_layout_view, movieDetailsList);

        this.context = (Context) context;
        this.movieDetailsList = (List<MovieDetails>) movieDetailsList;
        //requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(context);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(movie_layout_view, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.moviename);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.movieyear);
        ImageView imageViewPoster = listViewItem.findViewById(R.id.moviephoto);


        //Getting the moviess for the specified position
        MovieDetails movieDetails = movieDetailsList.get(position);

        //setting moviess values to textviews
        textViewName.setText(movieDetails.getTitle());
        textViewImageUrl.setText(movieDetails.getYear());
        Picasso.with(context).load(movieDetails.getImageurl()).into(imageViewPoster);


        //returning the listitem
        return listViewItem;
    }
}
