import java.util.Scanner;
import java.util.*;

public class CoffeeMachine {
    int water = 400;
    int milk = 540;
    int coffeeBeans = 120;
    int money = 550;
    int cups = 9;


    static Scanner scanner = new Scanner(System.in);

    public void start () {

        while (true) {
            String action = getInput();

            //check if input is EXIT and terminates the program if it is
            if (action.equalsIgnoreCase("exit")) {
                return;
            }

            doAction(action);
        }
    }

    public void showState () {

        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.coffeeBeans + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println(this.money + " of money");

    }

    public void menuOptions() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    public String getInput() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");

        String action = scanner.next();

        while (true) {
            if (action.equalsIgnoreCase("buy")) {
                return "buy";
            } else if (action.equalsIgnoreCase("fill")) {
                return "fill";
            } else if (action.equalsIgnoreCase("take")) {
                return "take";
            } else if (action.equalsIgnoreCase("remaining")) {
                return "remaining";
            } else if (action.equalsIgnoreCase("exit")) {
                return "exit";
            }

            System.out.println("Please select a correct action: buy, fill, take");
            action = getInput();
        }

    }

    public void doAction(String action) {

        if (action.equalsIgnoreCase("buy")) {
            buyCoffee();
        } else if (action.equalsIgnoreCase("fill")) {
            fillMachine();
        } else if (action.equalsIgnoreCase("take")) {
            takeMoney();
        } else if (action.equalsIgnoreCase("remaining")) {
            showState();
        }

    }

    private void buyCoffee() {
        String coffeeType = getCoffeeType();

        if (coffeeType.equalsIgnoreCase("return")) {
            return;
        }

        boolean areIngredientsEnough = checkAmountOfIngredients(coffeeType);

        if (areIngredientsEnough == true) {
            System.out.println("I have enough resources, making you a coffee!");

            if (coffeeType.equalsIgnoreCase("espresso")) {
                deductIngredients(250, 0, 16, 4);
            } else if (coffeeType.equalsIgnoreCase("latte")) {
                deductIngredients(350, 75, 20, 7);
            } else if (coffeeType.equalsIgnoreCase("cappuccino")) {
                deductIngredients(200, 100, 12, 6);
            }
        }

    }

    private boolean checkAmountOfIngredients(String coffeeType) {
        boolean result = false;

        if (coffeeType.equalsIgnoreCase("espresso")) {
            if (water >= 250 ) {
                if (coffeeBeans >= 16) {
                    result = true;
                } else {
                    System.out.println("Sorry, not enough coffee beans!");
                }
            } else {
                System.out.println("Sorry, not enough water!");
            }

        } else if (coffeeType.equalsIgnoreCase("latte")) {
            if (water >= 350) {
                if (coffeeBeans >= 20) {
                    if (milk >= 75) {
                        result = true;
                    } else {
                        System.out.println("Sorry, not enough milk!");
                    }
                } else {
                    System.out.println("Sorry, not enough coffee beans!");
                }
            } else {
                System.out.println("Sorry, not enough water!");
            }

        } else if (coffeeType.equalsIgnoreCase("cappuccino")) {
            if (water >= 200) {
                if (coffeeBeans >= 12) {
                    if (milk >= 100) {
                        result = true;
                    } else {
                        System.out.println("Sorry, not enough milk!");
                    }
                } else {
                    System.out.println("Sorry, not enough coffee beans!");
                }
            } else {
                System.out.println("Sorry, not enough water!");
            }
        }

        if (cups < 1) {
            result = false;
            System.out.println("Sorry, not enough disposable cups!");
        }

        return result;
    }

    private void deductIngredients(int water, int milk, int coffee, int money) {
        this.water -= water;
        this.milk -= milk;
        this.coffeeBeans -= coffee;
        this.cups -= 1;
        this.money += money;
    }

    private String getCoffeeType() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String answer = scanner.next();

        while (true) {
            if (answer.equals("1")) {
                return "espresso";
            } else if (answer.equals("2")) {
                return  "latte";
            } else if (answer.equals("3")) {
                return  "cappuccino";
            } else if (answer.equalsIgnoreCase("back")){
                return "back";
            }
            System.out.println("Please enter a valid option...");
        }
    }

    private void fillMachine() {
        System.out.println("Write how many ml of water do you want to add:");
        this.water += scanner.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        this.milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.cups += scanner.nextInt();
    }

    private void takeMoney() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.start();
    }


}
