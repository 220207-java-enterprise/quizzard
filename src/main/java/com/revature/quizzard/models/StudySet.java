package com.revature.quizzard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "study_sets")
public class StudySet {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AppUser owner;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    public StudySet() {
        super();
    }

    public StudySet(String name, AppUser owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
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
        StudySet studySet = (StudySet) o;
        return Objects.equals(id, studySet.id) &&
                Objects.equals(name, studySet.name) &&
                Objects.equals(owner, studySet.owner) &&
                category == studySet.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, category);
    }

    @Override
    public String toString() {
        return "StudySet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", category=" + category +
                '}';
    }

}
