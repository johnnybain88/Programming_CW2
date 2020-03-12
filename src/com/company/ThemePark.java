package com.company;

import java.util.ArrayList;

public class ThemePark
{
    private String parkName;
    private ArrayList<Attraction> attractionList;
    private ArrayList<Customer> customerList;

    public ThemePark(String parkName)
    {
        this.parkName = parkName;
        attractionList = new ArrayList<>();
        customerList = new ArrayList<>();
    }


    //calculating total distance of transport rides

    public int calculateTotalTransportDistance()
    {
        int totalDistance = 0;
        for (Attraction attraction: attractionList)
        {
            if (attraction instanceof TransportAttraction)
            {
                totalDistance = totalDistance + ((TransportAttraction)attraction).getDistance();
            }
        }
        return totalDistance;
    }

    public double calculateAverageGentleCapacity()
    {
        int totalCapacity = 0;
        int numOfGentle = 0;
        for (Attraction attraction: attractionList)
        {
            if (attraction instanceof GentleAttraction)
            {
                totalCapacity = totalCapacity + ((GentleAttraction)attraction).getCapacity();
                numOfGentle+=1;
            }
        }
        return totalCapacity/numOfGentle;
    }

    public double calculateMedianCoasterSpeed()
    {
        ArrayList<Double> speeds = new ArrayList<>();
        for (Attraction attraction: attractionList)
        {
            if (attraction instanceof RollercoasterAttraction)
            {
                speeds.add(((RollercoasterAttraction)attraction).getTopSpeed());
            }
        }
        int index = (speeds.size()-1)/2;
        double medianSpeed;
        if ( ((speeds.size())%2) == 0 )
        {
            medianSpeed = ((speeds.get(index) + speeds.get(index+1))/2);
        }
        else
        {
            medianSpeed = speeds.get(index);
        }
        return medianSpeed;
    }


    //adding new customer to the customer list
    public void addCustomer(int accountNumber, String name, int age, int accountBalance, String personalDiscount  )
    {
        Customer newCustomer = new Customer(accountNumber, name, age, accountBalance, personalDiscount);
        customerList.add(newCustomer);
    }

    //adding new attractions to the attraction list
    public void addRollercoasterAttraction(String name, int basePrice, int minimumAge, double topSpeed)
    {
        RollercoasterAttraction newRollercoasterAttraction = new RollercoasterAttraction(name, basePrice, minimumAge, topSpeed);
        attractionList.add(newRollercoasterAttraction);
    }
    public void addGentleAttraction(String name, int basePrice, int capacity)
    {
        GentleAttraction newGentleAttraction = new GentleAttraction(name, basePrice, capacity);
        attractionList.add(newGentleAttraction);
    }
    public void addTransportAttraction(String name, int basePrice, int distance)
    {
        TransportAttraction newTransportAttraction = new TransportAttraction(name, basePrice, distance);
        attractionList.add(newTransportAttraction);
    }

    //getting customers from the customer list
    public Customer getCustomer(String accountNumber) throws CustomerNotFoundException
    {
        for (Customer customer : customerList)
        {
            if (Integer.toString(customer.getAccountNumber()).equals(accountNumber))
            {
                return customer;
            }
        }
        throw new CustomerNotFoundException("the customer linked to account number: " + accountNumber + " was not found");
    }

    //removing customer from customer list
    public void removeCustomer(String accountNumber) throws CustomerNotFoundException
    {
        boolean ifFound = false;
        for (Customer customer : customerList)
        {
            if (Integer.toString(customer.getAccountNumber()).equals(accountNumber))
            {
                customerList.remove(customer);
                ifFound = true;
                break;
            }
        }
        if(ifFound == false)
        {
            throw new CustomerNotFoundException("the customer linked to account number: " + accountNumber + " was not found");
        }
    }

    //getting attraction from attraction list
    public Attraction getRide(String rideName) throws RideNotFoundExceptions
    {
        for (Attraction attraction : attractionList)
        {
            if(attraction.getName().equals(rideName))
            {
                return attraction;
            }
        }
        throw new RideNotFoundExceptions("the attraction named: " + rideName + " was not found");
    }

    //removing attraction from attraction list
    public void removeRide(String rideName) throws RideNotFoundExceptions
    {
        boolean ifFound = false;
        for (Attraction attraction : attractionList)
        {
            if(attraction.getName().equals(rideName))
            {
                attractionList.remove(attraction);
                ifFound = true;
                break;
            }
        }
        if(ifFound == false)
        {
            throw new RideNotFoundExceptions("the attraction named: " + rideName + " was not found");
        }
    }

    @Override
    public String toString()
    {
        return " park name: " + parkName + "\n the total transport distance: " + calculateTotalTransportDistance() + "\n the median coaster speed: " + calculateMedianCoasterSpeed() + "\n the total transport distance: " + calculateTotalTransportDistance();
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public void setAttractionList(ArrayList<Attraction> attractionList) {
        this.attractionList = attractionList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public String getParkName() {
        return parkName;
    }

    public ArrayList<Attraction> getAttractionList() {
        return attractionList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public static void main(String[] args) throws Exception
    {
        //test to create an instance of the ThemePark class
        ThemePark test = new ThemePark("testPark");

        //test to add a customer to the ThemePark
        test.addCustomer(1,"john",19,2000, "Family");

        //test to add two of each type of ride
        test.addGentleAttraction("rubber dingy rapids", 300, 40);
        test.addGentleAttraction("tractor  trail", 250,26);

        test.addRollercoasterAttraction("space mountain", 400,8,80);
        test.addRollercoasterAttraction("anxiety attack",350,18,119);

        test.addTransportAttraction("hogwarts express", 150,2000);
        test.addTransportAttraction("runaway train", 100, 4500);

        //testing the median of two coasters
        System.out.println(test.calculateMedianCoasterSpeed());

        //testing the top speed median calculates correctly with an odd number of coasters
        test.addRollercoasterAttraction("stealth",450,12,65);
        System.out.println(test.calculateMedianCoasterSpeed());

        //outputting the lists
        System.out.println(test.getCustomerList());
        System.out.println(test.getAttractionList());

        //printing the test theme park data

        System.out.println(test.toString());

        //testing both the remove methods (removeRide, removeCustomer)

        test.removeCustomer("1");
        test.removeRide("stealth");

        System.out.println(test.getCustomerList());
        System.out.println(test.getAttractionList());
        System.out.println(test.toString());

    }

}
