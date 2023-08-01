package sg.edu.rp.c346.id22027706.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Button btnInsert, btnList;
    Spinner spRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.editTextMovieTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        btnInsert = findViewById(R.id.buttonInsert);
        btnList = findViewById(R.id.buttonList);
        spRatings = findViewById(R.id.spinner);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                String rating = spRatings.getSelectedItem().toString();

                db.InsertMovies(etTitle.getText().toString(), etGenre.getText().toString(), etYear.getText().toString(), rating);
                db.close();
                Toast.makeText(MainActivity.this, "New movie inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MoviesList.class);
                startActivity(intent);
            }
        });

    }
}

