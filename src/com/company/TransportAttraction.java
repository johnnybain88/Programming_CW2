package com.company;

public class TransportAttraction extends Attraction
{
    private int distance;

    public TransportAttraction(String name, int basePrice, int distance)
    {
        super(name, basePrice);
        this.distance = distance;
    }

    //getters
    protected int getDistance() {return distance;}

    //setters
    protected void setDistance(int distance) {distance = distance;}

    @Override
    protected int getOffPeakPrice()
    {
        return (int)(getBasePrice()*0.5);
    }

    @Override
    public String toString()
    {
        return super.toString() + " Distance: " + this.distance;
    }

    public static void main(String[] args)
    {
        TransportAttraction test = new TransportAttraction("Hogwarts express", 2, 3000);
        System.out.println(test.toString());
    }

}
