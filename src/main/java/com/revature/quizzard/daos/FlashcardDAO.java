package com.revature.quizzard.daos;

import com.revature.quizzard.models.Flashcard;

public class FlashcardDAO implements CrudDAO<Flashcard> {

    @Override
    public void save(Flashcard newObject) {

    }

    @Override
    public Flashcard getById(String id) {
        return null;
    }

    @Override
    public Flashcard[] getAll() {
        return new Flashcard[0];
    }

    @Override
    public void update(Flashcard updatedObject) {

    }

    @Override
    public void deleteById(String id) {

    }

}
