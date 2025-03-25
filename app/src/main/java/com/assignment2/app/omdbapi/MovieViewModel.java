package com.assignment2.app.omdbapi;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {
    public  MutableLiveData<Movie> movieItems;

    public MovieViewModel(){
        movieItems = new MutableLiveData<>();
    }

    public void search(Context context, String term, String apiKey){
       // String apiKey =  getString(R.string.api_key);
        //showSearchResults(data);
        Log.d("App", term);
        DownloadManager downloadManager = new DownloadManager(apiKey, new DownloadManager.IComplete() {
            @Override
            public void onComplete(Movie data) {
                //showSearchResults(data);
                movieItems.postValue(data);
            }
        });
        downloadManager.getSearch(context, term);
    }
}
