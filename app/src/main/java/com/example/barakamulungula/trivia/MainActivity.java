package com.example.barakamulungula.trivia;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CallBack {

    private List<Question> questionsList = new ArrayList<Question>();
    public static final String QUESTIONS_LIST = "questions_list";

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
        if (!questionsList.isEmpty()) {
            QuizFragment takeQuiz = QuizFragment.newInstance();
            takeQuiz.attachParent(MainActivity.this);
            getSupportFragmentManager().beginTransaction().replace(R.id.trivia_layout, takeQuiz).setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).commit();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QUESTIONS_LIST, (ArrayList<? extends Parcelable>) questionsList);
            takeQuiz.setArguments(bundle);
        } else {
            Toast.makeText(this, "ENTER QUESTIONS", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.delete_quiz_button)
    protected void deleteQuiz() {
        if (!questionsList.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("DELETE QUIZ");
            builder.setMessage("Are you sure want to delete the quiz ?").setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Quiz Deleted", Toast.LENGTH_SHORT).show();
                    questionsList.clear();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


    @Override
    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();

    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveQuestion(Question question) {
        questionsList.add(question);
    }

    @Override
    public void quizFinished(int numCorrect, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        AlertDialog.Builder correctDialog = new AlertDialog.Builder(this);
        correctDialog.setMessage(getString(R.string.correct_questions, numCorrect));
        correctDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = correctDialog.create();
        dialog.show();
    }


}
