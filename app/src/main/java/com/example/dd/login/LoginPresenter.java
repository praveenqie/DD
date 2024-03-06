package com.example.dd.login;

import com.example.dd.login.dto.LoginRequest;

public class LoginPresenter {

    private LoginView view;
    private LoginModel model;



    public LoginPresenter(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    public void validateCredentials(LoginRequest loginRequest) {
        model.login(loginRequest, new LoginModel.OnLoginFinishedListener() {
            @Override
            public void onUsernameError() {
                view.showUsernameError();
            }

            @Override
            public void onPasswordError() {
                view.showPasswordError();
            }

            @Override
            public void onSuccess() {

                view.showSuccess();
            }

            @Override
            public void onNetworkFailure(String error) {

            }
        });
    }
}
