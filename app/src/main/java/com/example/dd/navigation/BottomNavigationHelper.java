package com.example.dd.navigation;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import com.example.dd.R;
import com.example.dd.cource.CourseActivity;
import com.example.dd.home.HomeActivity;
import com.example.dd.profile.ProfileActivity;
import com.example.dd.saved.SavedActivity;
import com.example.dd.search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelper {

    public static void setupBottomNavigation(Activity activity, BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    navigateToActivity(activity, HomeActivity.class);
                    return true;
                } else if (itemId == R.id.navigation_search) {
                    navigateToActivity(activity, SearchActivity.class);
                    return true;
                } else if (itemId == R.id.navigation_course) {
                    navigateToActivity(activity, CourseActivity.class);
                    return true;
                } else if (itemId == R.id.navigation_saved) {
                    navigateToActivity(activity, SavedActivity.class);
                    return true;
                } else if (itemId == R.id.navigation_profile) {
                    navigateToActivity(activity, ProfileActivity.class);
                    return true;
                }
                return false;
            }
        });
    }

    private static void navigateToActivity(Activity activity, Class<?> destinationActivity) {
        Intent intent = new Intent(activity, destinationActivity);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        activity.finish(); // Optional: Finish the current activity if you don't want it to be kept in the back stack
    }
}
