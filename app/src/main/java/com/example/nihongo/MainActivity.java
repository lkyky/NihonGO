package com.example.nihongo;

import android.os.Bundle;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nihongo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private String userCourseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_nihongo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        userCourseType = getIntent().getStringExtra("course");
        Log.d("MainActivity", "Course Type: " + userCourseType);

        Bundle args  = new Bundle();
        args.putString("courseType", userCourseType);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = navHostFragment.getNavController();

        navController.setGraph(R.navigation.mobile_navigation, args);
        Log.d("MainActivity", "Navigation Graph Arguments: " + args);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.Bhome, R.id.Thome, R.id.nav_kana_chart, R.id.nav_profile,
                R.id.BLessonsFragment, R.id.BExercisesFragment, R.id.BVideosFragment,
                R.id.TLessonsFragment, R.id.TExercisesFragment, R.id.TVideosFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.navView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.nav_home){
                if ("Beginner".equalsIgnoreCase(userCourseType)) {
                    navController.navigate(R.id.Bhome);
                } else {
                    navController.navigate(R.id.Thome);
                }
                return true;
            }
            else if(itemId == R.id.nav_kana_chart) {
                navController.navigate(R.id.nav_kana_chart);
                return true;
            }
            else if (itemId == R.id.nav_profile) {
                navController.navigate(R.id.nav_profile);
                return true;
            }
            return false;
        });
    }
}