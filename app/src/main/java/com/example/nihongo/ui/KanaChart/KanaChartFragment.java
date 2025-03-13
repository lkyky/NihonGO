package com.example.nihongo.ui.KanaChart;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nihongo.R;

public class KanaChartFragment extends Fragment {

    private TextView txtKanaDesc1, txtKanaDesc2, txtKanaDesc3, txtKanaDesc4, txtKanaDesc5;
    private ImageView imgKanaIntro, imgHiraganaChart, imgKatakanaChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_kana_chart, container, false);

        txtKanaDesc1 = view.findViewById(R.id.txtKanaDesc1);
        txtKanaDesc2 = view.findViewById(R.id.txtKanaDesc2);
        txtKanaDesc3 = view.findViewById(R.id.txtKanaDesc3);
        txtKanaDesc4 = view.findViewById(R.id.txtKanaDesc4);
        txtKanaDesc5 = view.findViewById(R.id.txtKanaDesc5);
        imgKanaIntro = view.findViewById(R.id.imgKanaIntro);
        imgHiraganaChart = view.findViewById(R.id.imgHiraganaChart);
        imgKatakanaChart = view.findViewById(R.id.imgKatakanaChart);

        txtKanaDesc1.setText(getString(R.string.txtKanaDesc1));
        txtKanaDesc2.setText(Html.fromHtml(getString(R.string.txtKanaDesc2), Html.FROM_HTML_MODE_LEGACY));
        txtKanaDesc3.setText(Html.fromHtml(getString(R.string.txtKanaDesc3), Html.FROM_HTML_MODE_LEGACY));
        txtKanaDesc4.setText(Html.fromHtml(getString(R.string.txtKanaDesc4), Html.FROM_HTML_MODE_LEGACY));
        txtKanaDesc5.setText(getString(R.string.txtKanaDesc5));

        Glide.with(this)
                .load(R.drawable.kana_intro)
                .into(imgKanaIntro);

        Glide.with(this)
                .load(R.drawable.hiraganachart)
                .into(imgHiraganaChart);

        Glide.with(this)
                .load(R.drawable.katakanachart)
                .into(imgKatakanaChart);

        return view;
    }
}