package com.androidtasks.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizQuestions {

    private static List<Question> allQuestions = new ArrayList<>();

    static {
        // Add questions here or in future get questions from Llama 2
        allQuestions.add(new Question(
                "General Knowledge:\nWhat is the largest planet in our solar system?",
                new String[]{"Mars", "Jupiter", "Saturn", "Neptune"},
                1
        ));

        allQuestions.add(new Question(
                "Simple Math:\nWhat is 10 minus 4?",
                new String[]{"5", "6", "7", "8"},
                2
        ));

        allQuestions.add(new Question(
                "Everyday Life:\nWhat do you wear on your feet when you go outside?",
                new String[]{"Hat", "Gloves", "Shoes", "Scarf"},
                2
        ));

        allQuestions.add(new Question(
                "Basic Science:\nWhat gas do humans primarily breathe in?",
                new String[]{"Carbon Dioxide", "Oxygen", "Nitrogen", "Helium"},
                1
        ));

        allQuestions.add(new Question(
                "Common Knowledge:\nWhat is the color of a ripe banana?",
                new String[]{"Green", "Red", "Yellow", "Purple"},
                2
        ));

        allQuestions.add(new Question(
                "Geography:\nWhat is the capital city of France?",
                new String[]{"London", "Paris", "Berlin", "Rome"},
                1
        ));

        allQuestions.add(new Question(
                "Simple Math:\nWhat is 2 multiplied by 8?",
                new String[]{"14", "16", "18", "20"},
                1
        ));

        allQuestions.add(new Question(
                "Everyday Life:\nWhat do you use to wash your hands?",
                new String[]{"Towel", "Sponge", "Soap", "Brush"},
                2
        ));

        allQuestions.add(new Question(
                "Basic Science:\nWhat is water made of?",
                new String[]{"Carbon Dioxide", "Oxygen", "Hydrogen and Oxygen", "Nitrogen"},
                2
        ));

        allQuestions.add(new Question(
                "Common Knowledge:\nWhat animal is known as the 'king of the jungle'?",
                new String[]{"Elephant", "Tiger", "Lion", "Giraffe"},
                2
        ));

        allQuestions.add(new Question(
                "General Knowledge:\nWhat is the chemical symbol for water?",
                new String[]{"Co2", "O2", "H2O", "N2"},
                2
        ));

        allQuestions.add(new Question(
                "Simple Math:\nWhat is 20 divided by 4?",
                new String[]{"4", "5", "6", "7"},
                1
        ));

        allQuestions.add(new Question(
                "Everyday Life:\nWhat do you use to see things far away?",
                new String[]{"Microscope", "Telescope", "Magnifying glass", "Mirror"},
                1
        ));

        allQuestions.add(new Question(
                "Basic Science:\nWhat force keeps us on the ground?",
                new String[]{"Magnetism", "Electricity", "Gravity", "Friction"},
                2
        ));

        allQuestions.add(new Question(
                "Common Knowledge:\nWhat is the primary source of light for Earth?",
                new String[]{"Moon", "Stars", "Sun", "Fire"},
                2
        ));

        allQuestions.add(new Question(
                "Simple Math:\nWhat is 7 plus 9?",
                new String[]{"14", "15", "16", "17"},
                2
        ));

        allQuestions.add(new Question(
                "Everyday Life:\nWhat do you use to cut paper?",
                new String[]{"Hammer", "Wrench", "Scissors", "Screwdriver"},
                2
        ));

        allQuestions.add(new Question(
                "Basic Science:\nWhat is the process of plants making their own food called?",
                new String[]{"Respiration", "Digestion", "Photosynthesis", "Transpiration"},
                2
        ));

        allQuestions.add(new Question(
                "Geography:\nWhat is the capital city of Japan?",
                new String[]{"Tokyo", "Seoul", "Beijing", "Bangkok"},
                0
        ));
        allQuestions.add(new Question(
                "Geography:\nWhich continent is Brazil located in?",
                new String[]{"North America", "Europe", "South America", "Africa"},
                2
        ));
        allQuestions.add(new Question(
                "Common Knowledge:\nWhat is the currency used in the United States?",
                new String[]{"Dollar", "Pound", "Yen", "Euro"},
                0
        ));
        allQuestions.add(new Question(
                "Geography:\nWhat is the largest desert in the world?",
                new String[]{"Sahara Desert", "Arabian Desert", "Gobi Desert", "Antarctic Desert"},
                3
        ));
        allQuestions.add(new Question(
                "Common Knowledge:\nWhich language has the most native speakers worldwide?",
                new String[]{"English", "Spanish", "Mandarin Chinese", "Hindi"},
                2
        ));
    }

    public static List<Question> getRandomQuestions(int numberOfQuestions) {
        List<Question> shuffledQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(shuffledQuestions); // Randomize the order

        if (numberOfQuestions > shuffledQuestions.size()) {
            return shuffledQuestions; // Return all if requested number is more than available
        } else {
            return shuffledQuestions.subList(0, numberOfQuestions); // Get the first 'numberOfQuestions' from the shuffled list
        }
    }

    public static List<Question> getQuestions() {
        return getRandomQuestions(5); // Default to 5 random questions
    }
}