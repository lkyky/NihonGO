package com.example.nihongo.ui.THome;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nihongo.FirestoreHelper;
import com.example.nihongo.Question;
import com.example.nihongo.QuestionAdapter;
import com.example.nihongo.R;
import com.example.nihongo.ui.ScoreFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class TExercise1 extends Fragment {

    private FirestoreHelper firestoreHelper;
    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> questionList = new ArrayList<>();
    private Button btnSubmit;
    private ImageView btnClose;

    public TExercise1() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_exercise1, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewQuestions);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnClose = view.findViewById(R.id.btnClose);

        firestoreHelper = new FirestoreHelper();

        firestoreHelper.addExerciseQuestions("TExercise1");

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new QuestionAdapter(questionList);
        recyclerView.setAdapter(adapter);

        // Load Questions Dynamically from Firestore
        firestoreHelper.getQuestions("TExercise1", questions -> {
            if (questions != null && !questions.isEmpty()) {
                questionList.clear();
                questionList.addAll(questions);
                adapter = new QuestionAdapter(questionList);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), "No questions found!", Toast.LENGTH_SHORT).show();
            }
        });

        // Submit Button Listener
        btnSubmit.setOnClickListener(v -> checkAnswers());

        // Close Button Listener
        btnClose.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }

    private void checkAnswers() {
        List<String> userAnswers = adapter.getUserAnswers();
        int score = firestoreHelper.checkAnswers(questionList, userAnswers);

        ArrayList<String> correctQuestions = new ArrayList<>();
        ArrayList<String> wrongQuestions = new ArrayList<>();

        for (int i = 0; i < questionList.size(); i++) {
            String questionText = questionList.get(i).getQuestionText(); // Ensure getQuestionText() is correct
            String correctAnswer = questionList.get(i).getCorrectAnswer(); // Ensure getCorrectAnswer() exists
            String userAnswer = userAnswers.get(i); // User's selected answer

            Log.d("CheckAnswers", "Q: " + questionText + " | Correct: " + correctAnswer + " | User: " + userAnswer);

            if (correctAnswer.equals(userAnswer)) {
                correctQuestions.add(questionText);
            } else {
                wrongQuestions.add(questionText);
            }
        }

        Log.d("CheckAnswers", "Correct Questions: " + correctQuestions.toString());
        Log.d("CheckAnswers", "Wrong Questions: " + wrongQuestions.toString());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid(); // Get current user's unique ID
            firestoreHelper.storeUserScore(userId, "TExercise1", score);
        } else {
            Toast.makeText(getContext(), "User not logged in!", Toast.LENGTH_SHORT).show();
        }

        // Navigate to ScoreFragment
        ScoreFragment scoreFragment = ScoreFragment.newInstance(score, correctQuestions, wrongQuestions);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        bundle.putString("exerciseId", "TExercise1");
        bundle.putStringArrayList("correct_questions", correctQuestions);
        bundle.putStringArrayList("wrong_questions", wrongQuestions);

        navController.navigate(R.id.ScoreFragment, bundle);
    }
}