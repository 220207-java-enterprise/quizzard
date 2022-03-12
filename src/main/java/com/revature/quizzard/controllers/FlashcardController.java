package com.revature.quizzard.controllers;

import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.repos.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flashcards")
public class FlashcardController {

    private final FlashcardRepository cardRepo;

    @Autowired
    public FlashcardController(FlashcardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    @GetMapping(produces = "application/json")
    public List<Flashcard> getAllCards() {
        return cardRepo.findAll();
    }

}
