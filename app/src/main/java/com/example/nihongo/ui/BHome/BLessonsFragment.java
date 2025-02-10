package com.example.nihongo.ui.BHome;

import android.os.Bundle;
import android.util.Log;
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
import com.example.nihongo.databinding.FragmentBLessonsBinding;
import com.google.android.material.card.MaterialCardView;

public class BLessonsFragment extends Fragment {

    private FragmentBLessonsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBLessonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        BHomeViewModel homeViewModel =
                new ViewModelProvider(this).get(BHomeViewModel.class);

        MaterialCardView lessonsCard = binding.btnBLesson1;
        lessonsCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "BLesson1");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.LessonReadyFragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with BLesson1");
        });

        MaterialCardView exercisesCard = binding.btnBLesson2;
        exercisesCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "BLesson2");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.LessonReadyFragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with BLesson2");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}