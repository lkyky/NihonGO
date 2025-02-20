package com.example.nihongo.ui.THome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.nihongo.R;
import com.example.nihongo.databinding.FragmentTHomeBinding;
import com.google.android.material.card.MaterialCardView;

public class THomeFragment extends Fragment {

    private FragmentTHomeBinding binding;
    private NavController navController;
    private boolean isFirstSelection = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.courseOption,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.courseDropdown.setAdapter(adapter);

        binding.courseDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                if(isFirstSelection){
                    isFirstSelection = false;
                    return;
                }

                String selectedCourse = parent.getItemAtPosition(position).toString();

                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("courseType", selectedCourse);
                editor.apply();

                if(selectedCourse.equals("Beginner")){
                    navController.navigate(R.id.action_THome_to_BHome);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });

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