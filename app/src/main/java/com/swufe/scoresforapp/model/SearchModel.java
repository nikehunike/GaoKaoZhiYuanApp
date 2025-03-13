package com.swufe.scoresforapp.model;


public interface SearchModel {

    void save(String value);

    void search(String value,OnSearchListener onSearchListener);

    void remove(String key);

    void clear();

    void sortHistory(OnSearchListener onSearchListener);
}
