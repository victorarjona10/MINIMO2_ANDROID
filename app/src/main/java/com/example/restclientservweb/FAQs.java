package com.example.restclientservweb;

public class FAQs {
    String fecha;
    String question;
    String answer;
    String sender;


    public FAQs(String fecha, String question, String answer, String sender) {
        this.fecha = fecha;
        this.question = question;
        this.answer = answer;
        this.sender = sender;
    }
    public FAQs() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
