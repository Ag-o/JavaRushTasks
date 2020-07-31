package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        Integer previousAmountByNominal = denominations.get(denomination);

        if (previousAmountByNominal != null) {
            count += previousAmountByNominal;
        }

        denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int totalAmount = denominations.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue()).sum();

        return totalAmount;
    }

    public boolean hasMoney() {

        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        
        int amountToWithdraw = expectedAmount;
        Map<Integer, Integer> temp = new TreeMap<>(Comparator.reverseOrder());
        temp.putAll(denominations);
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> pair : temp.entrySet())
            list.add(pair.getKey()); //номинал

        TreeMap<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());

        for (Integer i : list) {
            int key = i;
            int value = temp.get(key);
            while (true) {
                if (amountToWithdraw < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                amountToWithdraw -= key;
                value--;

                if (result.containsKey(key))
                    result.put(key, result.get(key) + 1);
                else
                    result.put(key, 1);
            }
        }

        if (amountToWithdraw > 0)
            throw new NotEnoughMoneyException();

        else {
            denominations.clear();
            denominations.putAll(temp);
            return result;
        }
    }
}
