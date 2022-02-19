package com.revature.quizzard.models;

import java.util.Objects;

public class StudySet {

    private String id;
    private String name;
    private String ownerId;

    // TODO Create category enum

    public StudySet() {
        super();
    }

    public StudySet(String name, String ownerId) {
        this.name = name;
        this.ownerId = ownerId;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudySet studySet = (StudySet) o;
        return Objects.equals(id, studySet.id) && Objects.equals(name, studySet.name) && Objects.equals(ownerId, studySet.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerId);
    }

    @Override
    public String toString() {
        return "StudySet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }

}
