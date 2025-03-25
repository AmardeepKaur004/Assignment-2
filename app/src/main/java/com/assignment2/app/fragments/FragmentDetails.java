package com.assignment2.app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.assignment2.app.adapters.MovieAdapterViewModel;
import com.assignment2.app.omdbapi.Movie;
import com.assignment2.databinding.FragmentDetailsBinding;
import com.squareup.picasso.Picasso;

public class FragmentDetails extends Fragment {
    FragmentDetailsBinding binding;
    MovieAdapterViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflating the layout of the fragment
        // and returning the view component
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() == null) { return; }
        viewModel  = new ViewModelProvider(getActivity()).get(MovieAdapterViewModel.class);
        viewModel.movieItem.observe(getViewLifecycleOwner(), this::showResults);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showResults(Movie movie) {

        binding.tvTitle.setText(movie.getTitle());
        binding.tvStudio.setText(movie.getStudio());
        binding.tvDescription.setText(movie.getDescription());
        binding.tvYear.setText(String.valueOf(movie.getYear()));


        Picasso.get().load(movie.getPoster()).into(binding.imgPoster);
    }

}
