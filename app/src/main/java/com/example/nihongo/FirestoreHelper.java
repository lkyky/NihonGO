package com.example.nihongo;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreHelper {
    private FirebaseFirestore db;
    private static final String TAG = "FirestoreHelper";

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void addExerciseQuestions(String exerciseId) {
        List<Question> questionList = new ArrayList<>();
        switch (exerciseId) {
            case "BExercise1":
                questionList = Arrays.asList(
                        new Question("What is the Japanese word for 'one'?",
                                Arrays.asList("二（に）", "一（いち）", "三（さん）"), "一（いち）"),
                        new Question("How do you say 'three' in Japanese?",
                                Arrays.asList("四（よん）", "一（いち）", "三（さん）"), "三（さん）"),
                        new Question("What is the Japanese word for 'five'?",
                                Arrays.asList("二（に）", "五（ご）", "六（ろく）"), "五（ご）"),
                        new Question("How do you say 'four' in Japanese?",
                                Arrays.asList("四（よん）", "五（ご）", "七（なな）"), "四（よん）"),
                        new Question("What is the Japanese word for 'two' in Japanese?",
                                Arrays.asList("一（いち）", "二（に）", "三（さん）"), "二（に）")
                );
                break;

            case "BExercise2":
                questionList = Arrays.asList(
                        new Question("What is 'father' in Japanese?",
                                Arrays.asList("お父さん／ おとうさん (otousan)", "お兄さん／ おにいさん (oniisan)", "妹／ いもうと (imouto)"),
                                "お父さん／ おとうさん (otousan)"),
                        new Question("What is 'mother' in Japanese?",
                                Arrays.asList("お母さん／ おかあさん (okaasan)", "妹／ いもうと (imouto)", "お兄さん／ おにいさん (oniisan)"),
                                "お母さん／ おかあさん (okaasan)"),
                        new Question("What is 'older brother' in Japanese?",
                                Arrays.asList("弟／ おとうと (otouto)", "お姉さん／ おねえさん (oneesan)", "お兄さん／ おにいさん (oniisan)"),
                                "お兄さん／ おにいさん (oniisan)"),
                        new Question("What is 'older sister' in Japanese?",
                                Arrays.asList("お兄さん／ おにいさん (oniisan)", "お姉さん／ おねえさん (oneesan)", "弟／ おとうと (otouto)"),
                                "お姉さん／ おねえさん (oneesan)"),
                        new Question("What is 'younger brother' in Japanese?",
                                Arrays.asList("お父さん／ おとうさん (otousan)", "妹／ いもうと (imouto)", "弟／ おとうと (otouto)"),
                                "弟／ おとうと (otouto)")
                );
                break;

            case "TExercise1":
                questionList = Arrays.asList(
                        new Question("How do you write 'hello' in Japanese?",
                                Arrays.asList("こんにちは", "ありがとう", "さようなら"), "こんにちは"),
                        new Question("What does 'ありがとう' mean?",
                                Arrays.asList("Goodbye", "Thank you", "Excuse me"), "Thank you")
                );
                break;

            case "TExercise2":
                questionList = Arrays.asList(
                        new Question("How do you write 'excuse me' in Japanese?",
                                Arrays.asList("すみません", "こんにちは", "ありがとう"), "すみません"),
                        new Question("What does 'さようなら' mean?",
                                Arrays.asList("Good morning", "See you", "Goodbye"), "Goodbye")
                );
                break;
        }

        WriteBatch batch = db.batch();
        for (int i = 0; i < questionList.size(); i++) {
            String questionId = "Q" + (i + 1);
            batch.set(db.collection("exercises").document(exerciseId)
                    .collection("questions").document(questionId), questionList.get(i).toMap());
        }

        batch.commit()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "All questions added for " + exerciseId))
                .addOnFailureListener(e -> Log.e(TAG, "Error adding questions to " + exerciseId, e));
    }

    public interface FirestoreCallback {
        void onCallback(List<Question> questionList);
    }

    public void getQuestions(String exerciseId, FirestoreCallback callback) {
        db.collection("exercises").document(exerciseId).collection("questions")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Question> questionList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Question question = document.toObject(Question.class);
                            questionList.add(question);
                        }
                        callback.onCallback(questionList);
                    } else {
                        Log.e(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    public int checkAnswers(List<Question> questions, List<String> userAnswers) {
        int correctCount = 0;
        for (int i = 0; i < questions.size(); i++) {
            String userAnswer = userAnswers.get(i);
            if (userAnswer != null && questions.get(i).getCorrectAnswer().equals(userAnswers.get(i))) {
                correctCount++;
            }
        }
        return correctCount;
    }

    public void storeUserScore(String userId, String exerciseId, int score) {
        Map<String, Object> scoreData = new HashMap<>();
        scoreData.put("exerciseId", exerciseId);
        scoreData.put("score", score);
        scoreData.put("timestamp", System.currentTimeMillis());

        db.collection("scores").document(userId + "_" + exerciseId)
                .set(scoreData)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Score stored successfully"))
                .addOnFailureListener(e -> Log.e(TAG, "Error storing score", e));
    }
}