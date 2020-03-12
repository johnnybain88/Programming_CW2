package com.company;


public class Customer
{


    private int accountNumber;
    private String name;
    private int age;
    private int accountBalance;
    private String personalDiscount;

    public Customer(int accountNumber, String name, int age, int accountBalance, String personalDiscount)
    {
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.accountBalance = accountBalance;
        this.personalDiscount = personalDiscount;
    }

    protected void addFunds(int amount)
    {
        accountBalance = accountBalance + amount;
    }

    public void useAttracction(int basePrice) throws InsufficientBalanceException
    {
        int discountedPrice = basePrice;
        if (personalDiscount.equals("FAMILY"))
        {
            discountedPrice = (int)(basePrice*0.85);
        }
        else if (personalDiscount.equals("STUDENT"))
        {
            discountedPrice = (int)(basePrice*0.9);
        }
        if (accountBalance - discountedPrice < 0)
        {
            throw new InsufficientBalanceException("your balance " + accountBalance + " is insufficient" );
        }else{
        accountBalance = accountBalance - discountedPrice;
        }
    }

    protected void useAttracction(int basePrice, int ageLimit) throws InsufficientBalanceException, AgeRestrictionException
    {
        if (age >= ageLimit)
        {
            useAttracction(basePrice);
        }
        else
        {
            throw new AgeRestrictionException(age + " is not above the age limit for this ride");
        }
    }

    public static String getAvailableDiscountInfo()
    {
        return "The different available discounts for the theme Park are\n family: %15\n student: %10 ";
    }

    @Override
    public String toString()
    {
        return "\n account number : " + this.accountNumber + " name: " + this.name + " age: " + this.age + " account balance: " + this.accountBalance + " personal discount: " + this.personalDiscount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public String getPersonalDiscount() {
        return personalDiscount;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setPersonalDiscount(String personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public static void main(String[] args)
    {
        Customer test = new Customer(532432, "John", 24, 25, "Family");
        System.out.println(test.toString());
    }

}
