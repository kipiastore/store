package ru.store.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static <T> Map<Integer, List<T>> cutPie(List<T> inputList, int piecesNumber) {
        Map<Integer, List<T>> result = new HashMap<>();
        List<T> piece = new ArrayList<>();
        int pieceCounter = 0;
        int itemCounter = 0;
        for (T item : inputList) {
            if (itemCounter == piecesNumber) {
                result.put(pieceCounter, piece);
                piece = new ArrayList<>();
                pieceCounter++;
                itemCounter = 0;
            }
            piece.add(item);
            itemCounter++;
        }
        return result;
    }

}
