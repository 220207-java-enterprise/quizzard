package com.revature.quizzard.models;

import java.util.Objects;

public class Flashcard {

    private String id;
    private String questionText;
    private String answerText;
    private String creatorId;

    // TODO create Category enum

    public Flashcard() {
        super();
    }

    public Flashcard(String questionText, String answerText, String creatorId) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.creatorId = creatorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(id, flashcard.id) && Objects.equals(questionText, flashcard.questionText) && Objects.equals(answerText, flashcard.answerText) && Objects.equals(creatorId, flashcard.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, answerText, creatorId);
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id='" + id + '\'' +
                ", questionText='" + questionText + '\'' +
                ", answerText='" + answerText + '\'' +
                ", creatorId='" + creatorId + '\'' +
                '}';
    }

}
