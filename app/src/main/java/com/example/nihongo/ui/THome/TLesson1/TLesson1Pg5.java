package com.example.nihongo.ui.THome.TLesson1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.nihongo.R;
import com.example.nihongo.databinding.FragmentTLesson1Pg5Binding;

public class TLesson1Pg5 extends Fragment {

    private FragmentTLesson1Pg5Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTLesson1Pg5Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView btnClose = binding.btnClose;
        btnClose.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TLessonsFragment);
        });

        ImageView btnBack = binding.btnBack;
        btnBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TLesson1Pg4Fragment);
        });

        ImageView btnForward = binding.btnForward;
        btnForward.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TLesson1EndFragment);
        });
    }

}