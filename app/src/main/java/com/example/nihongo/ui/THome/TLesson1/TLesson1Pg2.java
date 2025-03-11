package com.example.nihongo.ui.THome.TLesson1;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.nihongo.R;
import com.example.nihongo.databinding.FragmentTLesson1Pg2Binding;

import java.util.Locale;

public class TLesson1Pg2 extends Fragment {

    private FragmentTLesson1Pg2Binding binding;
    private TextToSpeech textToSpeech;
    private ImageView btnSound, btnSound2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTLesson1Pg2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textToSpeech = new TextToSpeech(requireContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.JAPANESE);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(getContext(), "Japanese TTS is not supported!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSound = binding.btnSound;
        btnSound2 = binding.btnSound2;
        btnSound.setOnClickListener(v -> speak("おはようございます"));
        btnSound2.setOnClickListener(v -> speak("おはようございます。今日はいい天気ですね。 "));

        ImageView btnClose = binding.btnClose;
        btnClose.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TLessonsFragment);
        });

        ImageView btnBack = binding.btnBack;
        btnBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TLesson1Fragment);
        });

        ImageView btnForward = binding.btnForward;
        btnForward.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.TLesson1Pg3Fragment);
        });
    }
    private void speak(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        binding = null;
    }
}