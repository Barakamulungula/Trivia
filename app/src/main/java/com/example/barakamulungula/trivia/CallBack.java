package com.example.barakamulungula.trivia;


import android.support.v4.app.Fragment;

public interface CallBack {
    void removeFragment(Fragment fragment);

    void makeToast(String message);

    void saveQuestion(Question question);

    void quizFinished(int numCorrect,int numQuestion, Fragment fragment);

    void fragmentAlertDialog(String message, Fragment fragmentToRemove);
}
