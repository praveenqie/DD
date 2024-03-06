package com.example.dd.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dd.R;
import com.example.dd.home.HomeActivity;
import com.example.dd.login.LoginActivity;
import com.example.dd.login.dto.UsersData;

public class SignupActivity extends AppCompatActivity implements SignupView {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signupButton;
    private ProgressDialog progressDialog;
    private SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signButton);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing up...");
        presenter = new SignupPresenter(this, new SignupModel(this));

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                presenter.validateSignup(new UsersData(firstName, lastName, email, password));
            }
        });
    }
    @Override
    public void showFirstNameError() {
        firstNameEditText.setError("First name cannot be empty");
    }

    @Override
    public void showLastNameError() {
        lastNameEditText.setError("Last name cannot be empty");
    }

    @Override
    public void showEmailError() {
        emailEditText.setError("Invalid email address");
    }

    @Override
    public void showPasswordError() {
        passwordEditText.setError("Password should be at least 6 characters long");
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onNetworkFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


}