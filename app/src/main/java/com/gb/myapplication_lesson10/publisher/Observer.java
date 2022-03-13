package com.gb.myapplication_lesson10.publisher;

import com.gb.myapplication_lesson10.repository.CardData;

public interface Observer {
    void receiveMassage(CardData cardData);
}
