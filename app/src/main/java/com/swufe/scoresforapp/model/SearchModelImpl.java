package com.swufe.scoresforapp.model;

import android.content.Context;

import com.swufe.scoresforapp.HistoryStorage.BaseHistoryStorage;
import com.swufe.scoresforapp.HistoryStorage.SpHistoryStorage;



public class SearchModelImpl implements SearchModel {

    private BaseHistoryStorage historyStorage;

    public SearchModelImpl(Context context, int historyMax) {
        historyStorage = SpHistoryStorage.getInstance(context, historyMax);
//        historyStorage = DbHistoryStorage.getInstance(context, historyMax);
        //historyStorage = GreenDaoHistoryStorage.getInstance(context, historyMax);
    }

    @Override
    public void save(String value) {
        historyStorage.save(value);
    }

    @Override
    public void search(String value, OnSearchListener onSearchListener) {
        onSearchListener.searchSuccess(value);
    }

    @Override
    public void remove(String key) {
        historyStorage.remove(key);
    }

    @Override
    public void clear() {
        historyStorage.clear();
    }

    @Override
    public void sortHistory(OnSearchListener onSearchListener) {
        onSearchListener.onSortSuccess(historyStorage.sortHistory());
    }
}
