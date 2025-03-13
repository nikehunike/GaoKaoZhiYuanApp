package com.swufe.scoresforapp.model;

import com.swufe.scoresforapp.bean.SearchHistoryModel;

import java.util.ArrayList;


public interface OnSearchListener {

    void onSortSuccess(ArrayList<SearchHistoryModel> results);

    void searchSuccess(String value);
}
