package com.example.nihongo.ui.BHome;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.nihongo.R;
import com.example.nihongo.databinding.FragmentBExercisesBinding;
import com.example.nihongo.databinding.FragmentBLessonsBinding;
import com.google.android.material.card.MaterialCardView;

public class BExercisesFragment extends Fragment {

    private FragmentBExercisesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBExercisesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        MaterialCardView lessonsCard = binding.btnBExercise1;
        lessonsCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "BExercise1");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.BExercise1Fragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with BExercise1");
        });

        MaterialCardView exercisesCard = binding.btnBExercise2;
        exercisesCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "BLesson2");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.BExercise2Fragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with BExercise2");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}