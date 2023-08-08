package sg.edu.rp.c346.id22027706.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class movie extends AppCompatActivity {

    EditText editTextMovieTitle, editTextGenre, editTextYear;
    Spinner spinner;
    Button buttonUpdate, buttonDelete, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();
        final Movies selectedMovie = (Movies) intent.getSerializableExtra("selectedMovie");

        editTextMovieTitle = findViewById(R.id.editTextMovieTitle);
        editTextGenre = findViewById(R.id.editTextGenre);
        editTextYear = findViewById(R.id.editTextYear);
        spinner = findViewById(R.id.spinner);


        editTextMovieTitle.setText(selectedMovie.getTitle());
        editTextGenre.setText(selectedMovie.getGenre());
        //editTextYear.setText(selectedMovie.getYear());//


        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditTexts and Spinner
                String updatedTitle = editTextMovieTitle.getText().toString();
                String updatedGenre = editTextGenre.getText().toString();
                String updatedYear = editTextYear.getText().toString();
                // Get selected rating from the spinner

                // Update the selectedMovie with the new values
                selectedMovie.setTitle(updatedTitle);
                selectedMovie.setGenre(updatedGenre);
                selectedMovie.setYear(Integer.parseInt(updatedYear));
                // Update the rating for the selectedMovie

                // Notify the MoviesList activity about the update

                // Finish the current activity
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the selected movie from the list
                MoviesList.removeMovie(selectedMovie);

                // Finish the current activity
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to MoviesList
                finish();
            }
        });
    }
}
