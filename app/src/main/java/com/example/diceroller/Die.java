package com.example.diceroller;

public class Die{
    private String name;
    private int numOfSides;
    private int currentSideUp;

    public Die(String name, int numOfSides){
        this.name = name;
        this.numOfSides = numOfSides;
        roll();
    }

    public String getName(){
        return name;
    }
    public int getNumOfSides(){
        return numOfSides;
    }
    public void setNumOfSides(int numOfSides){
        name = String.format("d%s", numOfSides);
        this.numOfSides = numOfSides;
    }
    public int getCurrentSideUp(){
        return currentSideUp;
    }
    public void setCurrentSideUp(int currentSideUp){
        this.currentSideUp = currentSideUp;
    }
    public int roll(){
        currentSideUp = (int)((Math.random() * numOfSides) + 1);
        return this.getCurrentSideUp();
    }
    public String toString(){
        return name;
    }
}