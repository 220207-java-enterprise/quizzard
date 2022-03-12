package com.revature.quizzard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "flashcards")
public class Flashcard {

    @Id
    private String id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private AppUser creator;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    public Category category;

    public Flashcard() {
        super();
    }

    public Flashcard(String questionText, String answerText, AppUser creator) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.creator = creator;
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

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(id, flashcard.id) &&
                Objects.equals(questionText, flashcard.questionText) &&
                Objects.equals(answerText, flashcard.answerText) &&
                Objects.equals(creator, flashcard.creator) &&
                category == flashcard.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, answerText, creator, category);
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id='" + id + '\'' +
                ", questionText='" + questionText + '\'' +
                ", answerText='" + answerText + '\'' +
                ", creator=" + creator +
                ", category=" + category +
                '}';
    }

}
