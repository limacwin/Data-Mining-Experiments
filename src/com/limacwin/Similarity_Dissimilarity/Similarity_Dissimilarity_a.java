package com.limacwin.Similarity_Dissimilarity;

import java.util.Scanner;

public class Similarity_Dissimilarity_a {
    static void euclidean_distance (int[] x_values, int[] y_values, int arr_size) {
        System.out.println("\n\t\t:: EUCLIDEAN DISTANCE ::\n");

        double[][] distance = new double[arr_size][arr_size];
        for (int i = 0; i < arr_size; i++) {
            if (i == 0) {
                for (int p = 0; p < arr_size; p++)
                    System.out.print("\t\t  P" + (p + 1));
                System.out.println();
            }
            System.out.print("P" + (i+1));
            for (int j = 0; j < arr_size; j++) {
                if (i == j)
                    distance[i][j] = 0;
                else
                    distance[i][j] = Math.sqrt(Math.pow((x_values[i] - x_values[j]), 2.0) + Math.pow((y_values[i] - y_values[j]), 2.0));
                System.out.printf("\t\t%.2f", distance[i][j]);
            }
            System.out.println();
        }

        double least_distance = distance[0][1];
        for (int i = 0; i < arr_size; i++) {
            for (int j = 0; j < arr_size; j++) {
                if (i != j && distance[i][j] < least_distance)
                    least_distance = distance[i][j];
            }
        }

        System.out.println();
        for (int i = 0; i < arr_size; i++) {
            for (int j = 0; j < arr_size; j++) {
                if (i != j && distance[i][j] == least_distance) {
                    System.out.println("P" + (i+1) + "P" + (j+1) + " has the least distance: " + least_distance);
                    distance[j][i] = -1;
                }
            }
        }
    }

    static void manhattan_distance (int[] x_values, int[] y_values, int arr_size) {
        System.out.println("\n\t\t:: MANHATTAN DISTANCE ::\n");

        int[][] distance = new int[arr_size][arr_size];
        for (int i = 0; i < arr_size; i++) {
            if (i == 0) {
                for (int p = 0; p < arr_size; p++)
                    System.out.print("\t\tP" + (p + 1));
                System.out.println();
            }
            System.out.print("P" + (i+1));
            for (int j = 0; j < arr_size; j++) {
                if (i == j)
                    distance[i][j] = 0;
                else
                    distance[i][j] = Math.abs(x_values[i] - x_values[j]) + Math.abs(y_values[i] - y_values[j]);
                System.out.printf("\t\t%d", distance[i][j]);
            }
            System.out.println();
        }

        double least_distance = distance[0][1];
        for (int i = 0; i < arr_size; i++) {
            for (int j = 0; j < arr_size; j++) {
                if (i != j && distance[i][j] < least_distance)
                    least_distance = distance[i][j];
            }
        }

        System.out.println();
        for (int i = 0; i < arr_size; i++) {
            for (int j = 0; j < arr_size; j++) {
                if (i != j && distance[i][j] == least_distance) {
                    System.out.println("P" + (i+1) + "P" + (j+1) + " has the least distance: " + least_distance);
                    distance[j][i] = -1;
                }
            }
        }
    }

    static void coefficients (int[] x_values, int[] y_values, int arr_size) {
        int m00 = 0, m01 = 0, m10 = 0, m11 = 0;
        for (int i = 0; i < arr_size; i++) {
            if (x_values[i] == 0 && y_values[i] == 0)
                m00++;
            else if (x_values[i] == 0 && y_values[i] == 1)
                m01++;
            else if (x_values[i] == 1 && y_values[i] == 0)
                m10++;
            else
                m11++;
        }

        double jaccard_coefficient = (double) m11 / (double) (m01 + m10 + m11);
        double simple_matching_coefficient = (double) (m00 + m11) / (double) (m00 + m01 + m10 + m11);

        System.out.println("\nJaccard Coefficient from the entered two datasets: " + jaccard_coefficient);
        System.out.println("Simple Matching Coefficient from the entered two datasets: " + simple_matching_coefficient);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\t\tCALCULATION OF THE EUCLIDEAN AND MANHATTAN DISTANCES\n");

        System.out.print("Enter the size of the x and y arrays: ");
        int arr_size = input.nextInt();

        int[] x_values = new int[arr_size];
        int[] y_values = new int[arr_size];

        for (int i = 0; i < arr_size; i++) {
            System.out.print("Enter value " + (i + 1) + " of the x array: ");
            x_values[i] = input.nextInt();
            System.out.print("Enter value " + (i + 1) + " of the y array: ");
            y_values[i] = input.nextInt();
        }

        euclidean_distance(x_values, y_values, arr_size);
        manhattan_distance(x_values, y_values, arr_size);

        System.out.println("\n\t\tCALCULATION OF THE JACCARD AND THE SIMPLE MATCHING COEFFICIENTS\n");

        System.out.print("Enter the size of the p and q arrays: ");
        arr_size = input.nextInt();

        for (int i = 0; i < arr_size; i++) {
            System.out.print("Enter value " + (i + 1) + " of the x array: ");
            x_values[i] = input.nextInt();
            System.out.print("Enter value " + (i + 1) + " of the y array: ");
            y_values[i] = input.nextInt();
        }
        coefficients(x_values, y_values, arr_size);
    }
}