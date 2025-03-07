package com.example.nihongo.ui.THome;

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
import com.example.nihongo.databinding.FragmentTExercisesBinding;
import com.google.android.material.card.MaterialCardView;

public class TExercisesFragment extends Fragment {

    private FragmentTExercisesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTExercisesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        MaterialCardView lessonsCard = binding.btnTExercise1;
        lessonsCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "TExercise1");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TExercise1Fragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with TExercise1");
        });

        MaterialCardView exercisesCard = binding.btnTExercise2;
        exercisesCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "TExercise2");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TExercise2Fragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with TExercise2");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}