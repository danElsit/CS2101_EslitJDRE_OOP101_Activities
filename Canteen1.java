import java.util.Scanner;

class Menu {

    String food;
    int item;
    double price;

    Menu(String food, int item, double price) {
        this.food = food;
        this.item = item;
        this.price = price;
    }
}

class Student {

    double discountRate;
    double discountAmount;
    double total;

    Student(double subtotal, double discountRate) {
        this.discountRate = discountRate;
        this.discountAmount = subtotal * discountRate;
        this.total = subtotal - this.discountAmount;
    }
}

public class Canteen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Menu carrotSalad = new Menu("Grated carrot salad", 1, 20);
        Menu beefBourguignon = new Menu("Beef Bourguignon", 2, 50);
        Menu porkSausage = new Menu("Sliced dry pork sausage", 3, 30);
        Menu potatoSalad = new Menu("Homemade potato salad", 4, 25);
        Menu chocoYogurt = new Menu("Chocolate yogurt", 5, 15);

        int totalItems = 0;
        double totalBeforeDiscount = 0;
        double totalDiscount = 0;
        double finalAmount = 0;

        System.out.println("=====   MENU    =====");
        System.out.printf("1. %-40s- $%.2f%n", carrotSalad.food, carrotSalad.price);
        System.out.printf("2. %-40s- $%.2f%n", beefBourguignon.food, beefBourguignon.price);
        System.out.printf("3. %-40s- $%.2f%n", porkSausage.food, porkSausage.price);
        System.out.printf("4. %-40s- $%.2f%n", potatoSalad.food, potatoSalad.price);
        System.out.printf("5. %-40s- $%.2f%n", chocoYogurt.food, chocoYogurt.price);

        String again = "Y";

        while (again.equalsIgnoreCase("Y")) {

            System.out.println();
            System.out.print("Enter item number: ");
            int itemChoice = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            // Validate: item must be 1-5, quantity must be 1-10
            boolean validItem = (itemChoice >= 1 && itemChoice <= 5);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);

            if (!validItem || !validQuantity) {
                System.out.println("\nInvalid order! Please enter a valid item and quantity.");
            } else {

                System.out.print("Are you a Student? (Y/N): ");
                String answer = input.next();
                boolean isStudent = answer.equalsIgnoreCase("Y");

                double price = 0;
                switch (itemChoice) {
                    case 1: price = carrotSalad.price; break;
                    case 2: price = beefBourguignon.price; break;
                    case 3: price = porkSausage.price; break;
                    case 4: price = potatoSalad.price; break;
                    case 5: price = chocoYogurt.price; break;
                }

                double subtotal = price * quantity;

                // Determine discount rate: student 10%, bulk (>=$500) 5%, both = 15%
                double discountRate = 0.0;
                if (isStudent && subtotal >= 500) {
                    discountRate = 0.15;
                } else if (isStudent) {
                    discountRate = 0.10;
                } else if (subtotal >= 500) {
                    discountRate = 0.05;
                }

                Student order = new Student(subtotal, discountRate);

                System.out.println();
                System.out.printf("Subtotal: $%.2f%n", subtotal);
                System.out.printf("Discount: $%.2f%n", order.discountAmount);
                System.out.printf("Total: $%.2f%n", order.total);

                // Add this order to the running totals
                totalItems += quantity;
                totalBeforeDiscount += subtotal;
                totalDiscount += order.discountAmount;
                finalAmount += order.total;
            }

            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            again = input.next();
        }

        System.out.println();
        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalItems);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thank you for ordering!");

        input.close();
    }
}
