package com.assignment2.app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.assignment2.app.MainActivity;
import com.assignment2.R;
import com.assignment2.app.adapters.MovieAdapter;
import com.assignment2.app.adapters.MovieAdapterViewModel;
import com.assignment2.app.omdbapi.Movie;
import com.assignment2.app.omdbapi.MovieViewModel;
import com.assignment2.databinding.FragmentSearchBinding;

public class FragmentSearch extends Fragment  {

    FragmentSearchBinding binding;
    MovieViewModel searchModel;
    MovieAdapterViewModel posClickModel;
    MovieAdapter movieAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflating the layout of the fragment
        // and returning the view component
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() == null) { return; }
        showSearchResults();

        searchModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        searchModel.movieItems.observe(getViewLifecycleOwner(), this::updateSearchResults);

        String apiKey = getActivity().getString(R.string.api_key);

        binding.btnSearch.setOnClickListener(v -> {
            if(binding.edtTerm.getText() == null){ return ;}
            String term = binding.edtTerm.getText().toString();
            if(term.isEmpty()) { return; }

            if(movieAdapter != null){
                movieAdapter.clear();
            }

            searchModel.search(getContext(), term, apiKey);
        });

    }

    private void updateSearchResults(Movie movie) {
        if(movieAdapter == null){return; }
        movieAdapter.add(movie);

    }

    public void showSearchResults(){

        binding.rvOmdb.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set Adapter
        movieAdapter = new MovieAdapter(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie item, int pos) {
                //switch fragment

                MainActivity activity =  (MainActivity)getActivity();
                if(activity==null){ return ; }
                activity.showDetailsFragment(item);

            }
        });
        binding.rvOmdb.setAdapter(movieAdapter);

    }
}
