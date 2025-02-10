package com.example.nihongo.ui;

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
import com.example.nihongo.databinding.FragmentLessonReadyBinding;

public class LessonReadyFragment extends Fragment {
    private FragmentLessonReadyBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLessonReadyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        Bundle args = getArguments();
        String lessonFragment = (args != null) ? args.getString("lessonFragment") : null;

        Log.d("LessonReadyFragment", "Received lesson fragment: " + lessonFragment);

        binding.btnNotReady.setOnClickListener(v -> {
            navController.popBackStack();
        });

        binding.btnReady.setOnClickListener(v -> {
            if(lessonFragment != null){
                int destinationId = getDestinationId(lessonFragment);
                if(destinationId != 0){
                    Log.d("LessonReadyFragment", "Navigating to" + lessonFragment);
                    navController.navigate(destinationId);
                }else{
                    Log.e("LessonReadyFragment", "Invalid destination for: " + lessonFragment);
                }
            }else{
                Log.e("LessonReadyFragment", "No lesson fragment found");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private int getDestinationId(String lesson){
        switch (lesson){
            case "BLesson1":
                return R.id.BLesson1Fragment;
            case "BLesson2":
                return R.id.BLesson2Fragment;
            default:
                return 0;
        }
    }
}