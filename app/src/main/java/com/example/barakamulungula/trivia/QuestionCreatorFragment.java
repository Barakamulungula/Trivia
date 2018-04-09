package com.example.barakamulungula.trivia;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionCreatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionCreatorFragment extends Fragment {

    private List<Question> Questions;
    private CallBack callBack;

    @BindView(R.id.question)
    EditText questionEditview;

    @BindView(R.id.correct)
    EditText correctAnswerEditview;

    @BindView(R.id.wrong1)
    EditText wrong1Editview;

    @BindView(R.id.wrong2)
    EditText wrong2Editview;

    @BindView(R.id.wrong3)
    EditText wrong3Editview;




    public QuestionCreatorFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QuestionCreatorFragment newInstance() {
        QuestionCreatorFragment fragment = new QuestionCreatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void attachParent(CallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_question_creator, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    
    @OnClick(R.id.cancel_question)
    protected void cancelQuestion(){
        callBack.removeQuestionCreatorFragment();
    }

    @OnClick(R.id.save_question) protected void saveQuestion(){

    }


}
