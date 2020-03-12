package com.company;

public abstract class Attraction
{
    private String name;
    private int BasePrice;

    public Attraction(String name, int basePrice)
    {
        this.name = name;
        BasePrice = basePrice;
    }

    protected abstract int getOffPeakPrice();

    //getters
    protected String getName() {return name;}

    protected int getBasePrice() {return BasePrice;}

    //setters
    protected void setName(String name) {this.name = name;}

    protected void setBasePrice(int basePrice) {BasePrice = basePrice;}

    @Override
    public String toString()
    {
        return "\n name: " + this.name + ", Base Price: " + this.BasePrice + " Off peak price: " + getOffPeakPrice();
    }

}
