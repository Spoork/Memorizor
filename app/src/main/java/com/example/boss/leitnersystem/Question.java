package com.example.boss.leitnersystem;


public class Question {
    private int questionIndex;
    private boolean answer;
    private String catgeory;

    public Question(int questionIndex, boolean answer, String catgeory) {
        this.questionIndex = questionIndex;
        this.answer = answer;
        this.catgeory = catgeory;
    }



    public String getCatgeory() {
        return catgeory;
    }

    public void setCatgeory(String catgeory) {
        this.catgeory = catgeory;
    }

    public int getquestionIndex() {
        return questionIndex;
    }

    public void setquestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }


}