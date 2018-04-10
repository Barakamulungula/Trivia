package com.example.barakamulungula.trivia;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.barakamulungula.trivia.MainActivity.QUESTIONS_LIST;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private CallBack callBack;
    private List<Question> questionList;
    private Question question;
    private int questionIndex = 0;
    private int correctAnswers = 0;
    private List<Button> buttonList = new ArrayList<>();
    List<String> answers = new ArrayList<String>();

    @BindView(R.id.question)
    TextView questionTextView;
    @BindView(R.id.answer1)
    Button option1;
    @BindView(R.id.answer2)
    Button option2;
    @BindView(R.id.answer3)
    Button option3;
    @BindView(R.id.answer4)
    Button option4;
    @BindView(R.id.next_question)
    Button nextQuestion;

    public QuizFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void attachParent(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        questionList = new ArrayList<Question>();
        questionList = getArguments().getParcelableArrayList(QUESTIONS_LIST);

        //checks if the list is empty
        assert questionList != null;
        Collections.shuffle(questionList);
        populateQuiz();

    }

    private void populateQuiz() {
        question = questionList.get(questionIndex);
        questionTextView.setText(question.getQuestion());

        buttonList = new ArrayList<>();
        buttonList.add(option1);
        buttonList.add(option2);
        buttonList.add(option3);
        buttonList.add(option4);

        List<String> answerChoices = new ArrayList<>();
        answerChoices.add(question.getCorrectAnswer());
        answerChoices.add(question.getWrong1());
        answerChoices.add(question.getWrong2());
        answerChoices.add(question.getWrong3());

        for (Button button : buttonList) {
            int random = (int) (Math.random() * answerChoices.size() - 1);
            button.setText(answerChoices.get(random));
            answerChoices.remove(random);
        }
    }

    @SuppressLint("StringFormatMatches")
    private void checkAnswer(String choice) {
        disableButtons();
        questionIndex++;
        for (Button button : buttonList) {
            if (button.getText().toString().toLowerCase().trim().equals(question.getCorrectAnswer().trim().toLowerCase())) {
                button.setBackgroundColor(Color.GREEN);
            } else {
                button.setBackgroundColor(Color.RED);
            }
        }
        if (choice.toLowerCase().trim().equals(question.getCorrectAnswer().toLowerCase().trim())) {
            questionTextView.setText(R.string.correct);
            correctAnswers++;
        } else {
            questionTextView.setText(getString(R.string.wrong, question.getCorrectAnswer().toUpperCase()));
        }
    }


    @OnClick(R.id.quit_quiz)
    protected void quitQuiz() {
        callBack.fragmentAlertDialog("Are you sure you want to quit the current quiz?", this);
    }

    @OnClick(R.id.next_question)
    protected void nextQuestion() {
        if (questionIndex <= questionList.size()-1) {
            enableButtons();
            resetBackground();
            populateQuiz();
        } else {
            callBack.quizFinished(correctAnswers, this);
        }

    }

    @OnClick(R.id.answer1)
    protected void buttonOneClick() {
        checkAnswer(option1.getText().toString());
        callBack.makeToast(option1.getText().toString());
    }

    @OnClick(R.id.answer2)
    protected void buttonTwoClick() {
        checkAnswer(option2.getText().toString());
        callBack.makeToast(option2.getText().toString());
    }

    @OnClick(R.id.answer3)
    protected void buttonThreeClick() {
        checkAnswer(option3.getText().toString());
        callBack.makeToast(option3.getText().toString());
    }

    @OnClick(R.id.answer4)
    protected void buttonFourClick() {
        checkAnswer(option4.getText().toString());
        callBack.makeToast(option4.getText().toString());
    }

    private void disableButtons() {
        nextQuestion.setEnabled(true);
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }

    private void resetBackground() {
        option1.setBackgroundColor(Color.TRANSPARENT);
        option2.setBackgroundColor(Color.TRANSPARENT);
        option3.setBackgroundColor(Color.TRANSPARENT);
        option4.setBackgroundColor(Color.TRANSPARENT);
    }


    private void enableButtons() {
        nextQuestion.setEnabled(false);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }
}

