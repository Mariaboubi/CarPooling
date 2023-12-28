package gr.aueb.carpooling.model.view.front_page;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.view.LogIn.LogInActivity;

public class FrontPageActivity extends Activity{

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(FrontPageActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }
}