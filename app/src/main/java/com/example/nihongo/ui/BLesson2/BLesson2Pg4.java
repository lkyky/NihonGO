package com.example.nihongo.ui.BLesson2;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nihongo.R;

public class BLesson2Pg4 extends Fragment {

    private BLesson2Pg4ViewModel mViewModel;

    public static BLesson2Pg4 newInstance() {
        return new BLesson2Pg4();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b_lesson2_pg4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BLesson2Pg4ViewModel.class);
        // TODO: Use the ViewModel
    }

}