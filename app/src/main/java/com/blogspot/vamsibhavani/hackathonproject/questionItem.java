package com.blogspot.vamsibhavani.hackathonproject;

import android.widget.TextView;

public class questionItem {

    private String questionValue, authorValue;

    public questionItem(String questionValue, String authorValue) {
        this.questionValue = questionValue;
        this.authorValue = authorValue;
    }

    public String getQuestionValue() {
        return questionValue;
    }

    public String getAuthorValue() {
        return authorValue;
    }

}
