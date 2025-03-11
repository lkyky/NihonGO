package com.example.nihongo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nihongo.R;

import java.util.ArrayList;

public class ScoreFragment extends Fragment {

    private static final String ARG_SCORE = "score";
    private static final String ARG_CORRECT_QUESTIONS = "correct_questions";
    private static final String ARG_WRONG_QUESTIONS = "wrong_questions";
    private int score;
    private ArrayList<String> correctQuestions;
    private ArrayList<String> wrongQuestions;

    public ScoreFragment() {}

    public static ScoreFragment newInstance(int score, ArrayList<String> correctQuestions, ArrayList<String> wrongQuestions) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putStringArrayList(ARG_CORRECT_QUESTIONS, correctQuestions);
        args.putStringArrayList(ARG_WRONG_QUESTIONS, wrongQuestions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(ARG_SCORE);
            correctQuestions = getArguments().getStringArrayList(ARG_CORRECT_QUESTIONS);
            wrongQuestions = getArguments().getStringArrayList(ARG_WRONG_QUESTIONS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        TextView txtFinalScore = view.findViewById(R.id.txtFinalScore);
        TextView txtCorrectAnswers = view.findViewById(R.id.txtCorrectAnswers);
        TextView txtWrongAnswers = view.findViewById(R.id.txtWrongAnswers);

        txtFinalScore.setText("Your score: " + score);

        // Display correct answers
        if (correctQuestions != null && !correctQuestions.isEmpty()) {
            StringBuilder correctText = new StringBuilder("✔ Correct:\n");
            for (String question : correctQuestions) {
                correctText.append("- ").append(question).append("\n");
            }
            txtCorrectAnswers.setText(correctText.toString());
        } else {
            txtCorrectAnswers.setText("No correct answers.");
        }

        // Display wrong answers
        if (wrongQuestions != null && !wrongQuestions.isEmpty()) {
            StringBuilder wrongText = new StringBuilder("❌ Incorrect:\n");
            for (String question : wrongQuestions) {
                wrongText.append("- ").append(question).append("\n");
            }
            txtWrongAnswers.setText(wrongText.toString());
        } else {
            txtWrongAnswers.setText("No incorrect answers.");
        }

        return view;
    }
}