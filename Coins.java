import java.util.Scanner;
import java.util.StringJoiner;
/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 1: coins.java
 * Student Name:   Baidi,Liu
 * Student cse account:  liubd
 * Student ID number:  211559093
 **********************************************************/
/**
 * Only use constant memory array for this implementation
 *
 */
public class Coins {

    // the value of coins
    private int[] coins = {1, 5, 10};
    private String[] name = {"pennies", "nickel", "dime"};
    // the result number of each coin
    private int[] coinNums = new int[coins.length];

    // solution num
    int index = 1;

    // clear the record in order to make print correctly
    public void clear() {
        index = 1;
    }

    // the method
    public void ways(int money) {
        this.findAllComb(0, coins, money, coinNums);
    }

    // recursively find out the solutions
    private void findAllComb(int start, int[] coins, int money, int[] coinNums) {
        if (money == 0) {
            printResult(coinNums);
            return;
        }
        if (start == (coins.length)) {
            return;
        }
        int currCoin = coins[start];
        for (int i = 0; i <= (money / currCoin); i++) {
            coinNums[start] = i;
            findAllComb(start + 1, coins, money - i * currCoin, coinNums);
        }
    }

    // print one solution, from back to end (according to assignment requirements)
    private void printResult(int[] coinNums) {
        StringJoiner sj = new StringJoiner(",");
        for (int i = coinNums.length - 1; i >= 0; i--) {
            if (coinNums[i] != 0) {
                sj.add(" " + coinNums[i] + " " + name[i]);
            }
        }
        System.out.println(index + ") " + sj.toString());
        index++;
    }

    // Only for test
    public static void main(String[] args) {
        Coins coins = new Coins();
        Scanner scanner = new Scanner(System.in);
        while (true) { // ctrl + c to exit
            System.out.println("Enter an amount in cents:");
            int money = scanner.nextInt(); // read the user input
            if (money <= 0) {
                System.out.println("Illegal input, try again!");
                continue;
            }
            System.out.println("This amount can be changed in the following ways:");
            coins.ways(money); // print result
            coins.clear();
        }
    }
}
