package com.example.nihongo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questionList;
    private String[] userAnswers;

    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
        userAnswers = new String[questionList.size()]; // Store user answers
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (questionList == null || questionList.isEmpty()) {
            Log.e("QuestionAdapter", "Question list is empty");
            return;
        }

        Question question = questionList.get(position);
        holder.txtQuestion.setText(question.getQuestionText());

        holder.radioGroup.setOnCheckedChangeListener(null); // Prevent re-triggering events
        holder.radioGroup.clearCheck(); // Reset selection

        // Set radio button options dynamically
        for (int i = 0; i < holder.radioButtons.length; i++) {
            if (i < question.getOptions().size()) {
                holder.radioButtons[i].setText(question.getOptions().get(i));
                holder.radioButtons[i].setVisibility(View.VISIBLE);

                // Restore selected option
                if (question.getOptions().get(i).equals(userAnswers[position])) {
                    holder.radioButtons[i].setChecked(true);
                }
            } else {
                holder.radioButtons[i].setVisibility(View.GONE);
            }
        }

        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedBtn = group.findViewById(checkedId);
            if (selectedBtn != null) {
                userAnswers[position] = selectedBtn.getText().toString();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public List<String> getUserAnswers() {
        return Arrays.asList(userAnswers); // Convert array to list
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuestion;
        RadioGroup radioGroup;
        RadioButton[] radioButtons = new RadioButton[3];

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            radioGroup = itemView.findViewById(R.id.radioGroup);

            radioButtons[0] = itemView.findViewById(R.id.option1);
            radioButtons[1] = itemView.findViewById(R.id.option2);
            radioButtons[2] = itemView.findViewById(R.id.option3);
        }
    }
}