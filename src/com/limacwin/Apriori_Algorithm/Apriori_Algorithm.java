package com.limacwin.Apriori_Algorithm;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Apriori_Algorithm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the amount of transactions: ");
        int n_transactions = input.nextInt();

        String item = "";
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        for (int i = 0; i < n_transactions; i++) {
            ArrayList<String> temp = new ArrayList<>();
            while (true) {
                System.out.print("Enter the content of itemset " + (i+1) + ", or enter 'end' to move to the next transaction: ");
                item = input.next();
                if (Objects.equals(item, "end"))
                    break;
                temp.add(item);
            }
            items.add(temp);
            System.out.println();
        }

        for (int i = 0; i < n_transactions; i++) {
            System.out.println((i+1) + " " + items.get(i));
        }

        System.out.print("Enter the minimum support count value (integer): ");
        int min_support_count = input.nextInt();
        System.out.print("Enter the minimum confidence value (percentage): ");
        int min_confidence = input.nextInt();

        for (int i = 0; i < n_transactions; i++) {
            ArrayList<String> temp = items.get(i);
            for (int k = 0; k < temp.size(); k++) {
                System.out.println();
            }
        }
    }
}
