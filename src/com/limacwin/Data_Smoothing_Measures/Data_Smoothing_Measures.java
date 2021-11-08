package com.limacwin.Data_Smoothing_Measures;

import java.util.*;
import java.lang.Math;

public class Data_Smoothing_Measures {
    static int[] BubbleSort (int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

    static void bin_means (ArrayList<ArrayList <Integer>> bin_arrays, int bin_amount) {

        for(int i = 0; i < bin_amount; i++)
        {
            int sum = 0;
            for(int j = 0; j < bin_arrays.get(i).size(); j++)
                sum  += bin_arrays.get(i).get(j);

            System.out.print("Bin " + (i+1) + ": ");

            float bin_mean = (float) sum/(bin_arrays.get(i).size());

            for(int j = 0; j < bin_arrays.get(i).size(); j++) {
                System.out.printf("%.2f", bin_mean);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void bin_boundaries (ArrayList<ArrayList <Integer>> bin_arrays, int bin_amount) {

        for(int i = 0; i < bin_amount; i++)
        {
            int left = bin_arrays.get(i).get(0);
            int right = bin_arrays.get(i).get(bin_arrays.get(i).size() - 1);

            System.out.print("Bin " + (i+1) + ": ");
            for(int j = 0; j < bin_arrays.get(i).size(); j++)
            {
                if(Math.abs(bin_arrays.get(i).get(j)-left) <= Math.abs(bin_arrays.get(i).get(j) - right))
                    System.out.print(left + " ");
                else
                    System.out.print(right + " ");
            }
            System.out.println();
        }
    }

    static void bin_medians (ArrayList<ArrayList <Integer>> bin_arrays, int bin_amount) {

        int bin_median = 0;
        for(int i = 0; i < bin_amount; i++)
        {
            if(bin_arrays.get(i).size() % 2 == 0)
                bin_median = (bin_arrays.get(i).get(bin_arrays.get(i).size()/2 - 1) + bin_arrays.get(i).get(bin_arrays.get(i).size()/2)) / 2;
            else
                bin_median = bin_arrays.get(i).get(bin_arrays.get(i).size() / 2);

            System.out.print("Bin " + (i+1) + ": ");
            for(int j = 0; j < bin_arrays.get(i).size(); j++)
                System.out.print(bin_median + " ");
            System.out.println();
        }
        System.out.println();
    }

    static void equal_width_binning() {

        System.out.println("\n\t\t:: EQUAL WIDTH BINNING ::\n");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of elements of the array: ");
        int arr_size = input.nextInt();
        int[] arr = new int[arr_size];

        System.out.println("\nEnter the array elements.\n");
        for(int i = 0; i < arr_size; i++)
            arr[i] = input.nextInt();

        arr = BubbleSort(arr);

        System.out.println("\nDisplaying the formed array after sorting.\n");
        for (int i = 0; i < arr_size; i++)
            System.out.print(arr[i] + "\t");

        System.out.print("\n\nEnter the number of bins: ");
        int bin_amount = input.nextInt();

        int min_value = arr[0];
        int max_value = arr[arr_size - 1];

        double width = Math.ceil((float) (max_value - min_value) / (float) bin_amount);
        System.out.println("Width calculated: " + width + "\n");

        ArrayList<ArrayList<Integer>> bin_arrays = new ArrayList<ArrayList<Integer>>();

        int k = 0;
        for (int i = 0; i < bin_amount; i++) {

            ArrayList<Integer> temp_interval_elements = new ArrayList<Integer>();

            for (int j = 0; j < arr_size; j++) {

                if (arr[j] >= (min_value + (i * width)) && arr[j] < (min_value + ((i + 1) * width))) {
                    temp_interval_elements.add(arr[k]);
                    k++;
                } else if ((i == bin_amount - 1) && (arr[j] >= (min_value + (i * width)) && arr[j] <= (min_value + ((i + 1) * width)))) {
                    temp_interval_elements.add(arr[k]);
                    k++;
                }
            }
            bin_arrays.add(temp_interval_elements);
        }

        System.out.println("Displaying the constructed bins using EQUAL-WIDTH binning.\n");
        for (int i = 0; i < bin_arrays.size(); i++) {
            System.out.println("Bin " + (i + 1) + ": " + bin_arrays.get(i));
        }

        System.out.println("\nDisplaying the bin means.\n");
        bin_means(bin_arrays, bin_amount);

        System.out.println("\nDisplaying the bin boundaries.\n");
        bin_boundaries(bin_arrays, bin_amount);

        System.out.println("\nDisplaying the bin medians.\n");
        bin_medians(bin_arrays, bin_amount);
    }

    static void equal_depth_binning() {

        System.out.println("\n\t\t:: EQUAL DEPTH BINNING ::\n");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of elements of the array: ");
        int arr_size = input.nextInt();
        int[] arr = new int[arr_size];

        System.out.println("\nEnter the array elements.\n");
        for(int i = 0; i < arr_size; i++)
            arr[i] = input.nextInt();

        arr = BubbleSort(arr);

        System.out.println("\nDisplaying the formed array after sorting.\n");
        for (int i = 0; i < arr_size; i++)
            System.out.print(arr[i] + "\t");

        System.out.print("\n\nEnter the number of bins: ");
        int bin_amount = input.nextInt();

        int bin_size = (int) Math.ceil((float) arr_size / (float) bin_amount);
        System.out.println("Size of bin calculated: " + bin_size + "\n");

        ArrayList<ArrayList<Integer>> bin_arrays = new ArrayList<ArrayList<Integer>>();

        int k = 0;
        for (int i = 0; i < bin_amount; i++) {

            ArrayList<Integer> temp_interval_elements = new ArrayList<Integer>();

            for (int j = 0; j < bin_size ; j++) {
                if(k != arr_size) {
                    temp_interval_elements.add(arr[k]);
                    k++;
                }
                else
                    break;
            }
            bin_arrays.add(temp_interval_elements);
        }

        System.out.println("Displaying the constructed bins using EQUAL-DEPTH binning.\n");
        for (int i = 0; i < bin_arrays.size(); i++) {
            System.out.println("Bin " + (i + 1) + ": " + bin_arrays.get(i));
        }

        System.out.println("\nDisplaying the bin means.\n");
        bin_means(bin_arrays, bin_amount);

        System.out.println("\nDisplaying the bin boundaries.\n");
        bin_boundaries(bin_arrays, bin_amount);

        System.out.println("\nDisplaying the bin medians.\n");
        bin_medians(bin_arrays, bin_amount);
    }

    static float p_ij (int m_ij, int m_i) {

        float p_ij = ((float)m_ij/m_i);

        return p_ij;
    }

    static final float log2(double value)
    {
        if (value <= 0)
            return 0;
        else
            return (float)(Math.log(value)/Math.log(2.0f));
    }

    static void supervised_binning_entropy_based () {

        System.out.println("\n\t\t:: SUPERVISED BINNING: ENTROPY-BASED APPROACH ::\n");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of records of the dataset: ");
        int num_records = input.nextInt();

        int[] hours_studied = new int[num_records];
        char[] A_on_test = new char[num_records];
        int y_count = 0, n_count = 0;
        float entropy_dataset = 0;

        for (int i = 0; i < num_records; i++) {
            System.out.print("\nEnter the hours studied for record " + (i + 1) + ": ");
            hours_studied[i] = input.nextInt();
            System.out.print("\nEnter the A on test (Y/N) bool for record " + (i + 1) + ": ");
            A_on_test[i] = input.next().charAt(0);
        }

        // to count number of Yes' and No's for under the A_on_test for each record
        for (int i = 0; i < num_records; i++) {
            if (A_on_test[i] == 'Y' || A_on_test[i] == 'y')
                y_count++;
            else
                n_count++;
        }

        // to calculate entropy of the whole dataset
        entropy_dataset = ( p_ij(y_count, num_records) * log2(p_ij(y_count, num_records)) ) + ( p_ij(n_count, num_records) * log2(p_ij(n_count, num_records)) );
        entropy_dataset *= -1;

        System.out.println("Dataset entropy: " + entropy_dataset);

        int total = 0, total_less = 0, total_more = 0;
        float average = 0;
        float entropy_less = 0, entropy_more = 0, net_entropy = 0;
        float[] gain = new float[num_records - 1];
        int k = 0;

        for (int i = 0; i < num_records - 1; i++) {

            // calculating average of two record values for further calculations
            average = (float)( hours_studied[i] + hours_studied[i+1] ) / 2;
            k = 0;
            y_count = 0;
            n_count = 0;

            System.out.println("\nAverage of " + hours_studied[i] + " and " + hours_studied[i+1] + ": " + average);

            // calculating entropy <= average
            while((float)hours_studied[k] <= average) {
                if (A_on_test[k] == 'Y' || A_on_test[k] == 'y')
                    y_count++;
                else
                    n_count++;
                k++;
            }

            total_less = y_count + n_count;
            entropy_less = ( p_ij(y_count, total_less) * log2(p_ij(y_count, total_less)) ) + ( p_ij(n_count, total_less) * log2(p_ij(n_count, total_less)) );
            entropy_less *= -1;

            System.out.println("\nEntropy of data <= " + average + ": " + entropy_less);

            y_count = 0;
            n_count = 0;

            // calculating entropy > average
            while(k != num_records) {
                if ((float)hours_studied[k] > average) {
                    if (A_on_test[k] == 'Y' || A_on_test[k] == 'y')
                        y_count++;
                    else
                        n_count++;
                }
                k++;
            }

            total_more = y_count + n_count;
            entropy_more = ( p_ij(y_count, total_more) * log2(p_ij(y_count, total_more)) ) + ( p_ij(n_count, total_more) * log2(p_ij(n_count, total_more)) );
            entropy_more *= -1;

            System.out.println("\nEntropy of data > " + average + ": " + entropy_more);

            // calculating net entropy
            total = total_less + total_more;
            net_entropy = ( p_ij(total_less, total) * entropy_less ) + ( p_ij(total_more, total) * entropy_more );

            System.out.println("\nNet entropy: " + net_entropy);

            // calculating gain
            gain[i] = entropy_dataset - net_entropy;
            System.out.println("\nGain: " + gain[i]);
        }

        // finding the highest gain
        float max_gain = gain[0];
        for(int i = 0; i < num_records - 1; i++) {
            if(gain[i] > max_gain)
                max_gain = gain[i];
        }
        System.out.println("\nMaximum Gain value: " + max_gain + "\n");
    }

    public static void main(String[] args) {

        System.out.println("\n\t\t:: DATA SMOOTHING MEASURES ::\n");

        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("1: EQUAL WIDTH BINNING\n2: EQUAL DEPTH BINNING\n3. SUPERVISED BINNING: ENTROPY-BASED\n4. EXIT");
            System.out.print("\nEnter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    equal_width_binning();
                    break;

                case 2:
                    equal_depth_binning();
                    break;

                case 3:
                    supervised_binning_entropy_based();
                    break;

                case 4:
                    System.out.println("Exiting now.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}