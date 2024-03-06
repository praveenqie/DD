package com.example.dd.signup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dd.api.ApiService;
import com.example.dd.api.ApiUtils;
import com.example.dd.database.DatabaseHelper;
import com.example.dd.login.dto.LoginResponse;
import com.example.dd.login.dto.UsersData;
import com.example.dd.signup.dto.SignUpRequest;
import com.example.dd.signup.dto.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupModel {

    private Context mContext;

    public SignupModel(Context context) {
        this.mContext = context;
    }
    public interface OnSignupFinishedListener {
        void onFirstNameError();
        void onLastNameError();
        void onEmailError();
        void onPasswordError();
        void onSuccess();
        void onNetworkFailure(String error);
    }

    public void signup(UsersData usersData, OnSignupFinishedListener listener) {
        // Perform validation and signup process
        if (usersData.getFirstname().isEmpty()) {
            listener.onFirstNameError();
            return;
        }
        if (usersData.getLastname().isEmpty()) {
            listener.onLastNameError();
            return;
        }
        if (usersData.getEmail().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(usersData.getEmail()).matches()) {
            listener.onEmailError();
            return;
        }
        if (usersData.getPassword().isEmpty() || usersData.getPassword().length() < 6) {
            listener.onPasswordError();
            return;
        }

        ApiService apiService = ApiUtils.getApiService(true);
        Call<SignUpResponse> call = apiService.signUp(new SignUpRequest(usersData.getFirstname(),usersData.getLastname(),
                usersData.email,usersData.getPassword(),"USER"));
        // Dummy signup process, replace it with your actual signup logic
        // Assuming signup is successful
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    SignUpResponse signUpResponse = response.body();
                    if (signUpResponse != null) {
                        listener.onSuccess();
                    } else {
                        listener.onNetworkFailure("Username/password wrong");
                    }
                } else {
                    listener.onNetworkFailure("API error");
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                //   listener.onLoginError("Network Error");
                listener.onNetworkFailure("Network error");
            }
        });



    }

    public void onSuccess(SignUpResponse signUpResponse) {
        // Save user data to database
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMAIL, signUpResponse.getUsersData().getEmail());
        values.put(DatabaseHelper.COLUMN_ACCESS_TOKEN,signUpResponse.getAccessToken());
        values.put(DatabaseHelper.COLUMN_REFRESH_TOKEN,signUpResponse.getRefreshToken());
        values.put(DatabaseHelper.COLUMN_FIRSTNAME,signUpResponse.getUsersData().getFirstname());
        values.put(DatabaseHelper.COLUMN_LASTNAME,signUpResponse.getUsersData().getLastname());
        long rowId = database.insert(DatabaseHelper.TABLE_CREDENTIALS, null, values);
        database.close();
    }
}
