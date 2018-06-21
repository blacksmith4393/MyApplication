package org.zwsmith.myapplication.presentation;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyViewModel {
    public Observable<ArrayList<String>> viewInfoStream;

    MyViewModel() {
        Observable<Long> intervalStream = Observable.interval(0L, 2L, TimeUnit.SECONDS);

        viewInfoStream = intervalStream.map(
                new Function<Long, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(Long aLong) throws Exception {
                        return getAnimeTitles();
                    }
                }
        );

    }

    private ArrayList<String> getAnimeTitles() {
        ArrayList<String> newTitles = new ArrayList<>();
        int randomLength = (int) (Math.random() * 100);
        for (int i = 0; i < randomLength; i++) {
            newTitles.add("Title " + i);
        }
        return newTitles;
    }
}