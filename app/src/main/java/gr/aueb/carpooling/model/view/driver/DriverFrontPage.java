package gr.aueb.carpooling.model.view.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionActivity;

public class DriverFrontPage extends AppCompatActivity {

    private ImageButton back_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_front_page);

        back_button = (ImageButton) findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttributeSelectionActivity();
            }
        });
    }

    void openAttributeSelectionActivity() {
        Intent intent = new Intent(this, AttributeSelectionActivity.class);
        startActivity(intent);
    }
}