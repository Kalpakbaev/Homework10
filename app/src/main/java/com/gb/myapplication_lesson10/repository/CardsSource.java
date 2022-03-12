package com.gb.myapplication_lesson10.repository;

import java.util.List;

public interface CardsSource {
    int size();
    List<CardData> getAllCardsData();
    CardData getCardData(int position);

    void clearCards();
    void adCardData(CardData cardData);


    void  deleteCardData(int position);
    void updateCardData(int position,CardData newCardDAta);

}
