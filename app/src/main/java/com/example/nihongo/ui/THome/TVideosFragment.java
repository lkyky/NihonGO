package com.example.nihongo.ui.THome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nihongo.R;
import com.google.android.material.card.MaterialCardView;

public class TVideosFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_t_videos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialCardView video1Card = view.findViewById(R.id.btnTVideo1);
        MaterialCardView video2Card = view.findViewById(R.id.btnTVideo2);

        video1Card.setOnClickListener(v -> openYouTubeLink("https://youtu.be/9Jn6AIkd0Pw"));
        video2Card.setOnClickListener(v -> openYouTubeLink("https://youtu.be/u_cHcefYV-o"));
    }

    private void openYouTubeLink(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setPackage("com.android.chrome");

        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(webIntent);
        }
    }
}