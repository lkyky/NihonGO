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

public class HomeFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        String userCourseType = getArguments() != null ? getArguments().getString("userCourseType") : null;
        if (userCourseType == null) {
            Log.e("HomeFragment", "courseType is null. Ensure it is passed correctly from the previous screen.");
            return;
        }

        Log.d("HomeFragment", "Received Course Type: " + userCourseType);

            if ("Beginner".equalsIgnoreCase(userCourseType)) {
                navController.navigate(R.id.action_home_to_Bhome);
            }
            else if ("Traveler".equalsIgnoreCase(userCourseType)) {
                navController.navigate(R.id.action_home_to_Thome);
            }
        else{
            Log.e("Home.fragment", "Arguments are null.");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("HomeFragment", "onCreateView: Arguments = " + getArguments());
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}