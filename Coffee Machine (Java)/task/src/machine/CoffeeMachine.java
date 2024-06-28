package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static int mlOfWater = 400;
    private static int mlOfMilk = 540;
    private static int gOfBeans = 120;
    private static int disposableCups = 9;
    private static int money = 550;

    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);

        while (isRunning) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String choice = input.nextLine();

            switch (choice) {
                case "remaining":
                    printCoffeeMachineStatus();
                    break;
                case "buy":
                    makeCoffee(input);
                    break;
                case "fill":
                    fillResources(input);
                    break;
                case "take":
                    takeMoney();
                    break;
                case "exit":
                    isRunning = false;
                    break;
            }
        }

        input.close();
    }

    private static void printCoffeeMachineStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(mlOfWater + " ml of water");
        System.out.println(mlOfMilk + " ml of milk");
        System.out.println(gOfBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    private static void makeCoffee(Scanner input) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to the main menu: ");
        String coffeeChoice = input.nextLine();

        if (coffeeChoice.equals("back")) {
            return;
        }

        int coffeeType = Integer.parseInt(coffeeChoice);
        int[] coffeeData = getCoffeeData(coffeeType);

        int requiredWater = coffeeData[0];
        int requiredMilk = coffeeData[1];
        int requiredBeans = coffeeData[2];
        int requiredCups = coffeeData[3];
        int cost = coffeeData[4];

        if (mlOfWater >= requiredWater && mlOfMilk >= requiredMilk && gOfBeans >= requiredBeans && disposableCups > 0) {
            System.out.println("I have enough resources, making you a coffee!");
            mlOfWater -= requiredWater;
            mlOfMilk -= requiredMilk;
            gOfBeans -= requiredBeans;
            disposableCups -= requiredCups;
            money += cost;
        } else {
            System.out.println("Sorry, not enough resources!");
        }
    }

    private static int[] getCoffeeData(int coffeeType) {
        int[] data = new int[5];
        switch (coffeeType) {
            case 1:
                data = new int[]{250, 0, 16, 1, 4};
                break;
            case 2:
                data = new int[]{350, 75, 20, 1, 7};
                break;
            case 3:
                data = new int[]{200, 100, 12, 1, 6};
                break;
        }
        return data;
    }

    private static void fillResources(Scanner input) {
        System.out.println("Write how many ml of water you want to add: ");
        mlOfWater += input.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        mlOfMilk += input.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        gOfBeans += input.nextInt();
        System.out.println("Write how many disposable cups you want to add: ");
        disposableCups += input.nextInt();
    }

    private static void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }
}
