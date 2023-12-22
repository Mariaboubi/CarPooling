package gr.aueb.carpooling.model.view.front_page;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;

public class FrontPageActivity extends Activity {

    private static final int SPLASH_TIME_OUT = 4000;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(FrontPageActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

}