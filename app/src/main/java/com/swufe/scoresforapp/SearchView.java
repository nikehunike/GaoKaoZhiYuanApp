package com.swufe.scoresforapp;

import com.swufe.scoresforapp.bean.SearchHistoryModel;

import java.util.ArrayList;



public interface SearchView {

    void showHistories(ArrayList<SearchHistoryModel> results);

    void searchSuccess(String value);
}
