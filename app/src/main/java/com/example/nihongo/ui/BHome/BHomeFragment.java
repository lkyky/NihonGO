package com.example.nihongo.ui.BHome;

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
import com.example.nihongo.databinding.FragmentBHomeBinding;

public class BHomeFragment extends Fragment {
    private FragmentBHomeBinding binding;
    private NavController navController;
    private boolean isFirstSelection = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        binding = FragmentBHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
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

                    if(selectedCourse.equals("Traveler")){
                        navController.navigate(R.id.action_BHome_to_THome);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });

            binding.btnBLessons.setOnClickListener(v -> {
                navController.navigate(R.id.BLessonsFragment);
            });

            binding.btnBExercises.setOnClickListener(v -> {
                navController.navigate(R.id.BExercisesFragment);
            });

            binding.btnBVideos.setOnClickListener(v -> {
                navController.navigate(R.id.BVideosFragment);
            });
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}