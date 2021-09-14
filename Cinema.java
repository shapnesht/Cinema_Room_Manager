package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeatsInRow = scanner.nextInt();
        int ticketsSold = 0;
        int totalIncome = total(numberOfRows, numberOfSeatsInRow);
        int currentIncome = 0;

        String[][] cinema = new String[numberOfRows + 1][numberOfSeatsInRow + 1];
        fill(cinema);
        printCommand();
        int val = scanner.nextInt();
        while (val != 0) {
            if (val == 1) {
                display(cinema);
            } else if (val == 2) {
                ticketsSold++;
                System.out.println("Enter a row number:");
                int rowNumber = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int colNumber = scanner.nextInt();
                int currentTicket = bookTicket(cinema, rowNumber, colNumber, numberOfRows, numberOfSeatsInRow);
                currentIncome += currentTicket;
            } else if (val == 3) {
                int totalTickets = numberOfRows * numberOfSeatsInRow;
                statistics(ticketsSold, totalTickets, currentIncome, totalIncome);
            }
            printCommand();
            val = scanner.nextInt();
        }
    }

    public static void statistics (int ticketsSold, int totalTickets, int currentIncome, int totalIncome) {
        double percentage = ((double) (ticketsSold) / totalTickets * 100);
        System.out.println("Number of purchased tickets: " + ticketsSold);
        String formattedString = String.format("%.02f", percentage);
        System.out.println("Percentage: "+ formattedString + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static int bookTicket(String[][] cinema, int rowNumber, int colNumber, int numberOfRows, int numberOfSeatsInRow) {
        while (true) {
            try {
                if (cinema[rowNumber][colNumber].equalsIgnoreCase("S")) {
                    cinema[rowNumber][colNumber] = "B";
                    System.out.println("Ticket price: $" + returnSeatCost(numberOfRows, numberOfSeatsInRow, rowNumber));
                    return returnSeatCost(numberOfRows, numberOfSeatsInRow, rowNumber);
                } else if (cinema[rowNumber][colNumber].equalsIgnoreCase("B")) {
                    System.out.println("That ticket has already been purchased!");
                    System.out.println("Enter a row number:");
                    rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    colNumber = scanner.nextInt();
                }
            } catch (Exception e) {
                System.out.println("Wrong input!");
                System.out.println("Enter a row number:");
                rowNumber = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                colNumber = scanner.nextInt();
            }
        }
    }

    public static void printCommand() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void fill(String[][] cinema) {
        cinema[0][0] = " ";
        for (int i = 1; i < cinema[0].length; i++) {
            cinema[0][i] = String.valueOf(i);
        }
        for (int i = 1; i < cinema.length; i++) {
            cinema[i][0] = String.valueOf(i);
            for (int j = 1; j < cinema[0].length; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    public static void display(String[][] cinema) {
        System.out.print("Cinema:\n");
        for (String[] strings : cinema) {
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(" " + strings[j]);
            }
            System.out.println();
        }
    }

    public static int total(int numberOfRows, int numberOfSeatsInRow) {
        int totalSeats = numberOfRows * numberOfSeatsInRow;
        int profit = 0;

        if (totalSeats <= 60) {
            profit = totalSeats * 10;
        } else {
            if (numberOfRows % 2 == 0) {
                profit = (numberOfRows / 2) * numberOfSeatsInRow * 10 + (numberOfRows / 2) * numberOfSeatsInRow * 8;
            } else {
                profit = (numberOfRows / 2) * numberOfSeatsInRow * 10 + (numberOfRows - numberOfRows / 2) * numberOfSeatsInRow * 8;
            }
        }
        return profit;
    }

    public static int returnSeatCost(int numberOfRows, int numberOfSeatsInRow, int row) {
        int totalSeats = numberOfRows * numberOfSeatsInRow;

        if (totalSeats <= 60) {
            return 10;
        } else {
            if (row <= numberOfRows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }
}