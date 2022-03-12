package com.gb.myapplication_lesson10.repository;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.provider.ContactsContract;

import com.gb.myapplication_lesson10.R;

import java.util.ArrayList;
import java.util.List;

public class LocalRepositoryImpl implements CardsSource {
    private List<CardData> dataSource;
    private Resources resources;

    public LocalRepositoryImpl(Resources resources) {
        dataSource = new ArrayList<CardData>();
        this.resources = resources;

    }

    public LocalRepositoryImpl init() {
        String[] titles = resources.getStringArray(R.array.titles);
        String[] description = resources.getStringArray(R.array.descriptions);
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        for (int i = 0; i < titles.length; i++) {
            dataSource.add(new CardData(titles[i], description[i], pictures.getResourceId(i, 0), false));
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
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData newCardDAta) {
        dataSource.set(position,newCardDAta);
    }
}
