package com.example.dd.signup;

import com.example.dd.login.dto.UsersData;

public class SignupPresenter {
    private SignupView view;
    private SignupModel model;

    public SignupPresenter(SignupView view, SignupModel model) {
        this.view = view;
        this.model = model;
    }

    public void validateSignup(UsersData usersData) {
        model.signup(usersData, new SignupModel.OnSignupFinishedListener() {
            @Override
            public void onFirstNameError() {
                view.showFirstNameError();
            }

            @Override
            public void onLastNameError() {
                view.showLastNameError();
            }

            @Override
            public void onEmailError() {
                view.showEmailError();
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
                view.onNetworkFailure(error);
            }

        });
    }
}
