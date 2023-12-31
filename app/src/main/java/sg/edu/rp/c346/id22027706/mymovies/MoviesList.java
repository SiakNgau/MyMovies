package sg.edu.rp.c346.id22027706.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

public class MoviesList extends AppCompatActivity {

    Button btnPG13;
    ListView lvMovies;
    ArrayAdapter<String> aa;
    ArrayList<Movies> arrList;
    boolean filter;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new CustomAdapter(this, R.layout.row, arrList);
        lvMovies.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movielist);

        btnPG13 = findViewById(R.id.button);
        lvMovies = findViewById(R.id.lvMovies);

        filter = false;

        DBHelper db = new DBHelper(MoviesList.this);
        db.close();
        DBHelper lv = new DBHelper(MoviesList.this);
        arrList = lv.getMovies();
        lv.close();

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies selectedMovie = arrList.get(position);

                Intent intent = new Intent(MoviesList.this, movie.class);
                intent.putExtra("selectedMovie", selectedMovie);
                startActivity(intent);
            }
        });


        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MoviesList.this);
                arrList.clear();
                if (!filter) {
                    arrList.addAll(dbh.getPG13Movies());
                    filter = true;
                    btnPG13.setText("Show all movies");
                } else {
                    arrList.addAll(dbh.getMovies());
                    filter = false;
                    btnPG13.setText("Show all movies with PG13 rating");
                }
                aa.notifyDataSetChanged();
            }
        });

    }
}
