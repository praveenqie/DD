package com.example.dd.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dd.home.HomeActivity;
import com.example.dd.R;
import com.example.dd.api.ApiUtils;
import com.example.dd.login.dto.LoginRequest;
import com.example.dd.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

//    private LoginPresenter presenter;
//
//    private ProgressBar progressBar;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressDialog progressDialog;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ApiUtils.initialize(getApplicationContext());
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton =  findViewById(R.id.signupButtonLink);
        presenter = new LoginPresenter(this,new LoginModel(this));
        //progressBar = findViewById(R.id.progressBar);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // progressBar.setVisibility(View.VISIBLE);
                presenter.validateCredentials( new LoginRequest("praveen@gmail.com","password@123"));
           }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }

    @Override
    public void showUsernameError() {
        usernameEditText.setError("Username cannot be empty");
    }

    @Override
    public void showPasswordError() {
        passwordEditText.setError("Password cannot be empty");
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

        // Start the home activity
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Finish the LoginActivity so that user cannot go back to it using back button
    }

    @Override
    public void onNetworkFailure(String error) {

        Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show();

    }
}


//    @Override
//    public void onSuccessLogin() {
//        if (progressBar != null) {
//            progressBar.setVisibility(View.GONE);
//        }
//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
//    }

//    @Override
//    public void onNetworkFailure(String error) {
////        Toast.makeText(getApplicationContext() ,"Login Failure", Toast.LENGTH_LONG).show();
//        Log.d("Login response",error);
//
//    }
