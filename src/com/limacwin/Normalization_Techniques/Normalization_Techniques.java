package com.limacwin.Normalization_Techniques;

import java.util.Scanner;

public class Normalization_Techniques {
    static int min (int[] arr, int arr_size) {
        int min = arr[0];
        for (int i = 1; i < arr_size; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
    }

    static int max (int[] arr, int arr_size) {
        int max = arr[0];
        for (int i = 1; i < arr_size; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    static void min_max_normalization (int[] dataset, int dataset_size) {
        System.out.println("\n\t\t:: MIN-MAX NORMALIZATION ::\n");

        int old_min = min (dataset, dataset_size);
        int old_max = max (dataset, dataset_size);

        System.out.println("Old min from entered dataset: " + old_min);
        System.out.println("Old max from entered dataset: " + old_max);

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the new min value: ");
        int new_min = input.nextInt();

        System.out.print("Enter the new max value: ");
        int new_max = input.nextInt();

        double[] new_values = new double[dataset_size];

        System.out.println();
        for (int i = 0; i < dataset_size; i++) {
            new_values[i] = ( ((dataset[i] - old_min)/(double)(old_max - old_min)) * (double)(new_max + ((-1)*new_min)) + (double)new_min);
            System.out.printf("New value of %d after min-max normalisation: %.4f\n", dataset[i], new_values[i]);
        }
    }

    static void decimal_scaling (int[] dataset, int dataset_size) {
        System.out.println("\n\t\t:: DECIMAL SCALING ::\n");

        int max = max(dataset, dataset_size);
        int max_length = String.valueOf(max).length();
        double[] new_values = new double[dataset_size];
        for (int i = 0; i < dataset_size; i++) {
            new_values[i] = ( (double) dataset[i]/Math.pow(10, max_length) );
            System.out.printf("New value of %d after decimal scaling: %.4f\n", dataset[i], new_values[i]);
        }
    }

    static void z_score_normalization (int[] dataset, int dataset_size) {
        System.out.println("\n\t\t:: Z-SCORE NORMALIZATION ::\n");

        double mean = 0;
        for (int i = 0; i < dataset_size; i++)
            mean += dataset[i];
        mean = mean/dataset_size;
        System.out.println("Mean: " + mean);

        double standard_deviation = 0;
        for (int i = 0; i < dataset_size; i++)
            standard_deviation += Math.pow(((double)dataset[i] - mean), 2.0);
        standard_deviation /= dataset_size;
        standard_deviation = Math.sqrt(standard_deviation);
        System.out.printf("Standard Deviation: %.4f\n", standard_deviation);

        double[] new_values = new double[dataset_size];
        for (int i = 0; i < dataset_size; i++) {
            new_values[i] = ((dataset[i] - mean)/standard_deviation);
            System.out.printf("New value of %d after z-score normalisation: %.4f\n", dataset[i], new_values[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("\n\t\t:: DATA NORMALIZATION TECHNIQUES ::\n");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of elements of the dataset: ");
        int dataset_size = input.nextInt();
        int[] dataset = new int[dataset_size];

        System.out.print("\nEnter the dataset values: ");
        for(int i = 0; i < dataset_size; i++)
            dataset[i] = input.nextInt();

        System.out.println("\n\t\tPerforming the 3 NORMALIZATION TECHNIQUES on the entered dataset.");
        min_max_normalization(dataset, dataset_size);
        decimal_scaling(dataset, dataset_size);
        z_score_normalization(dataset, dataset_size);
    }
}