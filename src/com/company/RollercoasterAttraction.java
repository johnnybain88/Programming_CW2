package com.company;

public class RollercoasterAttraction extends Attraction
{
    private int minimumAge;
    private double topSpeed;

    public RollercoasterAttraction(String name, int basePrice, int minimumAge, double topSpeed)
    {
        super(name, basePrice);
        this.minimumAge = minimumAge;
        this.topSpeed = topSpeed;
    }

    //getters
    protected int getMinimumAge() {return minimumAge;}

    protected double getTopSpeed() {return topSpeed;}

    //setters
    protected void setMinimumAge(int minimumAge) {minimumAge = minimumAge;}

    protected void setTopSpeed(double topSpeed) {topSpeed = topSpeed;}

    protected int getOffPeakPrice() {return getBasePrice();}

    @Override
    public String toString()
    {
        return super.toString() + " Minimum age: " + minimumAge + " Top speed: " + topSpeed;
    }

    public static void main(String[] args)
    {
        RollercoasterAttraction test = new RollercoasterAttraction("space mountain",3,7, 80);
        System.out.println(test.toString());
    }
}
