package com.example.dd.signup;

public interface SignupView {
    void showFirstNameError();
    void showLastNameError();
    void showEmailError();
    void showPasswordError();
    void showSuccess();
    void onNetworkFailure(String error);

}
