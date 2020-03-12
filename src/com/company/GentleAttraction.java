package com.company;

public class GentleAttraction extends Attraction
{
    private int capacity;

    public GentleAttraction(String name, int basePrice, int capacity)
    {
        super(name, basePrice);
        this.capacity = capacity;
    }


    //getters
    protected int getCapacity() {return capacity;}

    //setters
    protected void setCapacity(int capacity) {capacity = capacity;}


    @Override
    protected int getOffPeakPrice()
    {
        return (int)(getBasePrice()*0.8);
    }

    @Override
    public String toString() {
        return super.toString() + " Capacity: " + this.capacity;
    }

    public static void main(String[] args)
    {
        GentleAttraction test = new GentleAttraction("Rubber Dingy Rapids",3,4);
        System.out.println(test.toString());
    }

}
