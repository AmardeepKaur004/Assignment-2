package com.assignment2.app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.assignment2.R;
import com.assignment2.app.adapters.MovieAdapterViewModel;
import com.assignment2.app.fragments.FragmentDetails;
import com.assignment2.app.fragments.FragmentSearch;
import com.assignment2.app.omdbapi.Movie;
import com.assignment2.app.omdbapi.DownloadManager;
import com.assignment2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DownloadManager downloadManager;
    Fragment[] listFragments;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listFragments = new Fragment[]{
                new FragmentSearch(),
                new FragmentDetails()
        };


        showSearchFragment();
    }


    void showFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();

        // fragment transaction to add or replace
        // fragments while activity is running
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.ll_container, fragment);

        // making a commit after the transaction
        // to assure that the change is effective
        fragmentTransaction.commit();
    }

    public void showSearchFragment(){
        page = 1;
        showFragment(listFragments[0]);
    }

    public void showDetailsFragment(Movie result){
        MovieAdapterViewModel posClickModel = new ViewModelProvider(this).get(MovieAdapterViewModel.class);
        posClickModel.post(result);

        page = 2;
        showFragment(listFragments[1]);



    }

    @Override
    public void onBackPressed() {
        if(page == 2){
            showSearchFragment();
        } else {
            super.onBackPressed();
        }
    }
}