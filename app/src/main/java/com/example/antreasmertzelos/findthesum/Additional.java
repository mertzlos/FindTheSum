package com.example.antreasmertzelos.findthesum;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by antreasmertzelos on 08/10/16.
 */

public class Additional {
    //int numberIn;
    private ArrayList<Integer> addNumbers = new ArrayList<>();
    private ArrayList<Integer> setRndNumbers = new ArrayList<>();
    private Random random = new Random();
    private int minNum = 1;
    private int maxNum = 20;
    private int sum;

    public Additional(){

    }

    public void setNumberIn(int numberIn) {
        addNumbers.add(numberIn);
    }

    public int getSum() {
        for (int num:addNumbers) {
            sum += num;

        }
        return sum;
    }
    public void clearArray(){
        sum = 0;
        addNumbers.clear();

    }

    public ArrayList<Integer> getRndNumbers(int gameType){
        setRndNumbers.clear();
        for (int i = 0; i <gameType*gameType ; i++) {
            setRndNumbers.add(random.nextInt(maxNum-minNum+1)+minNum);

        }
        return setRndNumbers;
    }

    public int getArraySize(){
        return addNumbers.size();
    }
}
