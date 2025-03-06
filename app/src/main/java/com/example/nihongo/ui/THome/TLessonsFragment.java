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
import com.example.nihongo.databinding.FragmentTLessonsBinding;
import com.google.android.material.card.MaterialCardView;

public class TLessonsFragment extends Fragment {

    private FragmentTLessonsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTLessonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        MaterialCardView lessonsCard = binding.btnTLesson1;
        lessonsCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "TLesson1");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.LessonReadyFragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with TLesson1");
        });

        MaterialCardView exercisesCard = binding.btnTLesson2;
        exercisesCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("lessonFragment", "TLesson2");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.LessonReadyFragment, bundle);
            Log.d("Navigation", "Navigating to LessonReadyFragment with TLesson2");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}