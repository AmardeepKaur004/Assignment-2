package com.assignment2.app.adapters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assignment2.app.omdbapi.Movie;


public class MovieAdapterViewModel extends ViewModel {
    public MutableLiveData<Movie> movieItem;
    private Movie current;

    public MovieAdapterViewModel(){
        movieItem = new MutableLiveData<>();
    }

    public void post(Movie item){
        current = item;
        movieItem.postValue(item);
    }

    public Movie getCurrent(){
        return current;
    }
}
