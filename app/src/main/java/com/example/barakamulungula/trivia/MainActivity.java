package com.example.barakamulungula.trivia;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CallBack {


    @BindView(R.id.take_quiz_button)
    Button takeQuizButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "is in layout", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.add_question_button)
    protected void addQuestion(View view) {
        QuestionCreatorFragment questionCreatorFragment = QuestionCreatorFragment.newInstance();
        questionCreatorFragment.attachParent(MainActivity.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.trivia_layout, questionCreatorFragment).setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();
    }

    @OnClick(R.id.take_quiz_button)
    protected void takeQuiz(View view) {
        QuizFragment takeQuiz = QuizFragment.newInstance();
        takeQuiz.attachParent(MainActivity.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.trivia_layout, takeQuiz).setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();
    }

    @OnClick(R.id.delete_quiz_button)
    protected void toastt() {
        makeToast("Delete QUIZ");
    }


    @Override
    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();

    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
