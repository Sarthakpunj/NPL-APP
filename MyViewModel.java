package com.example.nplfinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    // In MyViewModel class
    private MutableLiveData<String> resultLiveData = new MutableLiveData<>();

    // Getter for the LiveData
    public LiveData<String> getResultLiveData() {
        return resultLiveData;
    }

    // Method to set the result
    public void setResult(String result) {
        resultLiveData.setValue(result);
    }

}


