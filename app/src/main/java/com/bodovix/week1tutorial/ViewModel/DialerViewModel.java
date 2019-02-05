package com.bodovix.week1tutorial.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class DialerViewModel extends ViewModel {

    private String numberEntered;
    private MutableLiveData<String> numberEnteredLiveData;

    DialerViewModel (){
        numberEntered = "";
        numberEnteredLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getNumberEntered(){
        numberEnteredLiveData.setValue(numberEntered);
        return numberEnteredLiveData;
    }

     public  void setNumberEntered(String  value){
        numberEntered = value;
        numberEnteredLiveData.setValue(numberEntered);
     }
}
