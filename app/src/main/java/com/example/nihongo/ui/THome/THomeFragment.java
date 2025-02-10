package com.example.nihongo.ui.THome;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongo.R;
import com.example.nihongo.databinding.FragmentTHomeBinding;
import com.google.android.material.card.MaterialCardView;

public class THomeFragment extends Fragment {

    private FragmentTHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        THomeViewModel homeViewModel =
                new ViewModelProvider(this).get(THomeViewModel.class);

        MaterialCardView lessonsCard = binding.btnTLessons;
        lessonsCard.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TLessonsFragment);
        });

        MaterialCardView exercisesCard = binding.btnTExercises;
        exercisesCard.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TExercisesFragment);
        });

        MaterialCardView videosCard = binding.btnTVideos;
        videosCard.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TVideosFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}