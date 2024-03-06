package com.example.dd.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dd.api.ApiService;
import com.example.dd.api.ApiUtils;
import com.example.dd.database.DatabaseHelper;
import com.example.dd.login.dto.LoginRequest;
import com.example.dd.login.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel {

    private Context mContext;

    public LoginModel(Context context) {
        this.mContext = context;
    }
    public interface OnLoginFinishedListener {
        void onUsernameError();
        void onPasswordError();
        void onSuccess();

        void onNetworkFailure(String error);
    }
    public void login(LoginRequest loginRequest, OnLoginFinishedListener listener) {
        ApiService apiService = ApiUtils.getApiService(true);
        if (loginRequest.getEmail().isEmpty()) {
            listener.onUsernameError();
            return;
        }
        if (loginRequest.getPassword().isEmpty()) {
            listener.onPasswordError();
            return;
        }
        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        onSuccess(loginResponse);
                        listener.onSuccess();
                    } else {
                        listener.onNetworkFailure("Username/password wrong");
                    }
                } else {
                    listener.onNetworkFailure("API error");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
             //   listener.onLoginError("Network Error");
                listener.onNetworkFailure("Network error");
            }
        });
    }

    public void onSuccess(LoginResponse loginResponse) {
        // Save user data to database
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMAIL, loginResponse.getUsersData().getEmail());
        values.put(DatabaseHelper.COLUMN_ACCESS_TOKEN,loginResponse.getAccess_token());
        values.put(DatabaseHelper.COLUMN_REFRESH_TOKEN,loginResponse.getRefresh_token());
        values.put(DatabaseHelper.COLUMN_FIRSTNAME,loginResponse.getUsersData().getFirstname());
        values.put(DatabaseHelper.COLUMN_LASTNAME,loginResponse.getUsersData().getLastname());
        long rowId = database.insert(DatabaseHelper.TABLE_CREDENTIALS, null, values);
        database.close();
    }
}
