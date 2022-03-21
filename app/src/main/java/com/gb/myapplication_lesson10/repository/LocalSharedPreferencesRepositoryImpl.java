package com.gb.myapplication_lesson10.repository;

import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class LocalSharedPreferencesRepositoryImpl implements CardsSource {
    private List<CardData> dataSource;
    private SharedPreferences sharedPreferences;
    static final String KEY_CELL_1 = "cell_1";
    public static final String KEY_SP_2 = "key_sp_2";


    public LocalSharedPreferencesRepositoryImpl(SharedPreferences sharedPreferences) {
        dataSource = new ArrayList<CardData>();
        this.sharedPreferences = sharedPreferences;

    }

    public LocalSharedPreferencesRepositoryImpl init() {
        String savedCard = sharedPreferences.getString(KEY_CELL_1, null);
        if (savedCard != null) {
            dataSource.add(new GsonBuilder().create().fromJson(savedCard, CardData.class));
        }
        return this;
    }


    @Override
    public int size() {
        return dataSource.size();
    }


    @Override
    public List<CardData> getAllCardsData() {
        return dataSource;
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void clearCards() {
        dataSource.clear();

    }

    @Override
    public void adCardData(CardData cardData) {
        dataSource.add(cardData);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CELL_1, new GsonBuilder().create().toJson(cardData));
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData newCardDAta) {
        dataSource.set(position, newCardDAta);
    }
}
