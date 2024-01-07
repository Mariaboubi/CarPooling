package gr.aueb.carpooling.model.view.log_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import gr.aueb.carpooling.R;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionActivity;
import gr.aueb.carpooling.model.view.sign_up.SignUpActivity;

public class LogInActivity extends AppCompatActivity implements LogInView{
    private LoginViewModel viewModel;
    private Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        login_button = (Button) findViewById(R.id.btnLogIn);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        viewModel.getPresenter().setView(this);


        findViewById(R.id.txtSignUp).setOnClickListener(v -> viewModel.getPresenter().onSignup()); // Goes to sign up page
        login_button.setOnClickListener(v -> viewModel.getPresenter().authenticate()); // Authenticates the user

    }

    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(LogInActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }


    public String extractUsername()
    {
        return ((EditText)findViewById(R.id.usernameText)).getText().toString().trim();
    }

    public String extractPassword()
    {
        return ((EditText)findViewById(R.id.password_text)).getText().toString().trim();
    }

    public void openSignupActivity(){ //goes to sign up page
        Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void openAttributeSelectionActivity(String username){
        Intent intent = new Intent(LogInActivity.this, AttributeSelectionActivity.class);
        intent.putExtra("Id", extractUsername());
        startActivity(intent);
    }
}