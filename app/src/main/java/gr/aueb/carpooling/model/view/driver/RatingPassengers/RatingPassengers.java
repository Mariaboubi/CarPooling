package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.sign_up.SignUpActivity;

public class RatingPassengers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_passengers);
    }

    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(RatingPassengers.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }



}