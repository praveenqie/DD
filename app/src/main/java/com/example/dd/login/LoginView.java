package com.example.dd.login;

import android.content.Context;

public interface LoginView {
//    void onSuccessLogin();
//
//    void onNetworkFailure(String error);

    void showUsernameError();
    void showPasswordError();
    void showSuccess();
    void onNetworkFailure(String error);

}

