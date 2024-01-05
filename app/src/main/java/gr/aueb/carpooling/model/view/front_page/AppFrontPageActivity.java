package gr.aueb.carpooling.model.view.front_page;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;
import gr.aueb.carpooling.model.view.log_in.LogInActivity;

public class AppFrontPageActivity extends Activity{

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_front_page);

        MemoryInitialized dataHelper = new MemoryInitialized();
        dataHelper.prepareData();

        handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent=new Intent(AppFrontPageActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        },4000);

    }
}