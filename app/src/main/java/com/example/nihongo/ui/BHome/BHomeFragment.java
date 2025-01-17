package com.example.nihongo.ui.BHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.nihongo.R;
import com.example.nihongo.databinding.FragmentBHomeBinding;
import com.google.android.material.card.MaterialCardView;

public class BHomeFragment extends Fragment {

    private FragmentBHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BHomeViewModel homeViewModel =
                new ViewModelProvider(this).get(BHomeViewModel.class);

        MaterialCardView lessonsCard = binding.btnBLessons;
        lessonsCard.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.BLessonsFragment);
        });

        MaterialCardView exercisesCard = binding.btnBExercises;
        exercisesCard.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.BExercisesFragment);
        });

        MaterialCardView videosCard = binding.btnBVideos;
        videosCard.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.BVideosFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}