package com.naresh.kingupadhyay.mathsking;

public class dChapter {
    private String title;
    private String question;
    private String answer;
    private String image_concept;
    private String question_image;
    private String answer_image;

    //ALT+Insert and click on constructor
    public dChapter(String title, String question, String answer, String image_concept, String question_image, String answer_image) {
        this.title = title;
        this.question = question;
        this.answer = answer;
        this.image_concept = image_concept;
        this.question_image = question_image;
        this.answer_image = answer_image;
    }

    //ALT+Insert and click on getter and setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage_concept() {
        return image_concept;
    }

    public void setImage_concept(String image_concept) {
        this.image_concept = image_concept;
    }

    public String getQuestion_image() {
        return question_image;
    }

    public void setQuestion_image(String question_image) {
        this.question_image = question_image;
    }

    public String getAnswer_image() {
        return answer_image;
    }

    public void setAnswer_image(String answer_image) {
        this.answer_image = answer_image;
    }
    public dChapter(){

    }
}
