package com.gb.myapplication_lesson10.repository;

import java.util.List;

public interface CardSource {
    int size();
    List<CardData> getAllCardsData();
    CardData getCardData(int position);
}
