package com.e.fight_corona.models;

public class Answer
{
    private String questionId;
    private String answer;
    private  Object time;
    private String doctorId;
    private String id;
    public Answer()
    {

    }

    public Answer(String questionId, String answer, Object time, String doctorId, String id)
    {
        this.questionId = questionId;
        this.answer = answer;
        this.time = time;
        this.doctorId = doctorId;
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
