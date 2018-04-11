package com.example.barakamulungula.trivia;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    private String question;
    private String correctAnswer;
    private String wrong1;
    private String wrong2;
    private String wrong3;


    public Question(String question, String correctAnswer, String wrong1, String wrong2, String wrong3) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.wrong3 = wrong3;
    }

    protected Question(Parcel in) {
        question = in.readString();
        correctAnswer = in.readString();
        wrong1 = in.readString();
        wrong2 = in.readString();
        wrong3 = in.readString();
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }


    public String getWrong1() {
        return wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public String getWrong3() {
        return wrong3;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(correctAnswer);
        dest.writeString(wrong1);
        dest.writeString(wrong2);
        dest.writeString(wrong3);
    }
}
