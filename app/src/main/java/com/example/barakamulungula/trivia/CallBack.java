package com.example.barakamulungula.trivia;

import android.app.Fragment;

public interface CallBack {
    void removeQuizFragment();
    void removeQuestionCreatorFragment();
    void makeToast(String message);
}
