package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation
{
    public Simulation(){}

    public void createThemePark(String parkName) throws FileNotFoundException, InsufficientBalanceException, CustomerNotFoundException, RideNotFoundExceptions, AgeRestrictionException {
        ThemePark testPark = new ThemePark(parkName);
        System.out.println("loading customer------------------------------------------------------");
        load_file("U:\\Documents\\Programming_CW2\\customers.txt","#", testPark);
        System.out.println("loading attractions---------------------------------------------------");
        load_file("U:\\Documents\\Programming_CW2\\attractions.txt","@",testPark);
        //System.out.println("loading transactions--------------------------------------------------");
        load_file("U:\\Documents\\Programming_CW2\\transactions.txt",",",testPark);
        //System.out.println(testPark.getAttractionList());
        //System.out.println(testPark.getCustomerList());

    }
    public void load_file(String filePath, String separator, ThemePark Park) throws FileNotFoundException, InsufficientBalanceException, CustomerNotFoundException, RideNotFoundExceptions, AgeRestrictionException {
        try
        {
            System.out.println("reached load file method");
            File file = new File(filePath);
            Scanner scan = new Scanner(file);
            System.out.println("attempting to cycle through lines in file");
            ArrayList customers = Park.getCustomerList();
            ArrayList attractions = Park.getAttractionList();


            //if the file is a customer file -----------------------------------------
            if (separator.equals("#"))
            {
                while (scan.hasNext())
                {
                    String line = scan.nextLine();
                    Scanner lineScan = new Scanner(line);
                    lineScan.useDelimiter(separator);
                    if (line.contains("FAMILY") || line.contains("STUDENT"))
                    {
                        Park.addCustomer(lineScan.nextInt(), lineScan.next(), lineScan.nextInt(), lineScan.nextInt(), lineScan.next());
                    }
                    else
                    {
                        Park.addCustomer(lineScan.nextInt(), lineScan.next(), lineScan.nextInt(), lineScan.nextInt(), "");
                    }
                }
            }
            //if the file is an attraction file ---------------------------------------
            else if (separator.equals("@"))
            {
                while (scan.hasNext())
                {
                    String line = scan.nextLine();
                    Scanner lineScan = new Scanner(line);
                    lineScan.useDelimiter(separator);
                    if (line.contains("TRA"))
                    {
                        line = line.replace("@TRA@", "@");
                        Scanner newlineScan = new Scanner(line);
                        newlineScan.useDelimiter(separator);
                        Park.addTransportAttraction(newlineScan.next().trim(), newlineScan.nextInt(), newlineScan.nextInt());
                    }
                    else if (line.contains("GEN"))
                    {
                        line = line.replace("@GEN@", "@");
                        Scanner newlineScan = new Scanner(line);
                        newlineScan.useDelimiter(separator);
                        Park.addGentleAttraction(newlineScan.next().trim(), newlineScan.nextInt(), newlineScan.nextInt());
                    }
                    else if (line.contains("ROL"))
                    {
                        line = line.replace("@ROL@", "@");
                        Scanner newlineScan = new Scanner(line);
                        newlineScan.useDelimiter(separator);
                        Park.addRollercoasterAttraction(newlineScan.next().trim(), newlineScan.nextInt(), newlineScan.nextInt(), newlineScan.nextDouble());
                    }
                }
            }
            //if the file is a transactions file -----------------------------------
            else if (separator.equals(","))
            {
                int profit = 0;
                while (scan.hasNext())
                {
                    String line = scan.nextLine();
                    Scanner lineScan = new Scanner(line);
                    lineScan.useDelimiter(separator);
                    if (line.contains("USE_ATTRACTION"))
                    {
                        try
                        {
                            if (line.contains("STANDARD_PRICE")) //run use attraction at base price
                            {
                                line = line.replace("USE_ATTRACTION,STANDARD_PRICE,", "");
                                Scanner newlineScan = new Scanner(line);
                                newlineScan.useDelimiter(separator);
                                Customer customer = (Customer) Park.getCustomer(newlineScan.next());
                                Attraction attraction = (Attraction) Park.getRide(newlineScan.next());
                                if (attraction instanceof RollercoasterAttraction)
                                {
                                    int initialBalance = customer.getAccountBalance();
                                    customer.useAttracction(attraction.getBasePrice(), ((RollercoasterAttraction) attraction).getMinimumAge());
                                    profit = profit + (initialBalance - customer.getAccountBalance());
                                }
                                else
                                {
                                    int initialBalance = customer.getAccountBalance();
                                    customer.useAttracction(attraction.getBasePrice());
                                    profit = profit + (initialBalance - customer.getAccountBalance());
                                }
                            }
                            else //run useAttraction at off peak price
                            {
                                line = line.replace("USE_ATTRACTION,OFF_PEAK,", "");
                                Scanner newlineScan = new Scanner(line);
                                newlineScan.useDelimiter(separator);
                                Customer customer = (Customer) Park.getCustomer(newlineScan.next());
                                Attraction attraction = (Attraction) Park.getRide(newlineScan.next());
                                if (attraction instanceof RollercoasterAttraction)
                                {
                                    int initialBalance = customer.getAccountBalance();
                                    customer.useAttracction(attraction.getOffPeakPrice(), ((RollercoasterAttraction) attraction).getMinimumAge());
                                    profit = profit + (initialBalance - customer.getAccountBalance());
                                }
                                else
                                {
                                    int initialBalance = customer.getAccountBalance();
                                    customer.useAttracction(attraction.getOffPeakPrice());
                                    profit = profit + (initialBalance - customer.getAccountBalance());
                                }
                            }
                        }
                        catch (InsufficientBalanceException | CustomerNotFoundException | RideNotFoundExceptions | AgeRestrictionException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else if (line.contains("ADD_FUNDS"))
                    {
                        try
                        {
                            line = line.replace("ADD_FUNDS,", "");
                            Scanner newlineScan = new Scanner(line);
                            newlineScan.useDelimiter(separator);
                            Customer customer = (Customer) Park.getCustomer(newlineScan.next());
                            customer.addFunds(newlineScan.nextInt());
                        }
                        catch(CustomerNotFoundException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else if (line.contains("NEW_CUSTOMER"))
                    {
                        line = line.replace("NEW_CUSTOMER,", "");
                        Scanner newlineScan = new Scanner(line);
                        newlineScan.useDelimiter(",");
                        if (line.contains("FAMILY") || line.contains("STUDENT"))
                        {
                            Park.addCustomer(newlineScan.nextInt(), newlineScan.next(), newlineScan.nextInt(), newlineScan.nextInt(), newlineScan.next());
                        }
                        else
                        {
                            Park.addCustomer(newlineScan.nextInt(), newlineScan.next(), newlineScan.nextInt(), newlineScan.nextInt(), "");
                        }
                    }
                }
                System.out.println("\n"+Park.toString());
                System.out.println(" Total profit for the simulation is: " + profit + " pence");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception
    {
        Simulation test = new Simulation();
        test.createThemePark("DisneyLand");

    }
}


