package com.gb.myapplication_lesson10.publisher;

import com.gb.myapplication_lesson10.repository.CardData;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    public List<Observer> observers;

    public Publisher(){
        observers = new ArrayList<>();
    }
    public void subScribe(Observer observer) {
        observers.add(observer);
    }

    public void unSubScribe(Observer observer) {
        observers.remove(observer);
    }


    public void sendMassage(CardData cardData) {
        for (Observer observer : observers) {
            observer.receiveMassage(cardData);
        }
    }
}
