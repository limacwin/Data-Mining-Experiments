package com.limacwin.Decision_Tree;
import java.util.*;

public class Decision_Tree {
    static float log2(double value) {
        if (value <= 0)
            return 0;
        else
            return (float)(Math.log(value)/Math.log(2.0f));
    }
    
    static double Calculate(int index, int n_records, ArrayList<ArrayList<String>> table, int n_attributes, ArrayList<String> attributes, double dataset_entropy) {
        Set<String> set = new LinkedHashSet<String>();
        for(int i = 0; i<table.size(); i++)
            set.add(table.get(i).get(index));

        String[] unique_elements = new String[set.size()];
        set.toArray(unique_elements);

        int[][] entropy_calculation_data = new int[set.size()][2];
        int[] count_unique_attributes = new int[unique_elements.length];
        
        for(int i = 0; i<table.size(); i++) {
            for(int j = 0; j<unique_elements.length; j++) {
                if(unique_elements[j].equals(table.get(i).get(index)) && "c0".equals(table.get(i).get(n_attributes-1))) {
                    entropy_calculation_data[j][0]++;
                    count_unique_attributes[j]++;
                }
                else if(unique_elements[j].equals(table.get(i).get(index)) && "c1".equals(table.get(i).get(n_attributes-1))) {
                    entropy_calculation_data[j][1]++;
                    count_unique_attributes[j]++;
                }
            }
        }

        System.out.println("\n" +attributes.get(index)+ "\n");
        
        for(int i = 0; i<unique_elements.length; i++) {
            for(int j = 0; j<2; j++)
                System.out.print("Count of c" +j+ " for " +unique_elements[i]+ ": " +entropy_calculation_data[i][j] + "\n");
            System.out.println();
        }
        
        //Calculate entropies
        for(int i = 0; i<unique_elements.length; i++)
            System.out.println("Total count of " +unique_elements[i]+ ":" +count_unique_attributes[i]);
        
        double entropy = 0;
        double net_entropy = 0;
        double gain = 0;
        System.out.println();
        for(int i = 0; i<unique_elements.length; i++) {
            entropy = -(((double)entropy_calculation_data[i][0]/count_unique_attributes[i]*1.0)*log2((double)entropy_calculation_data[i][0]/count_unique_attributes[i]*1.0) + 
                    ((double)entropy_calculation_data[i][1]/count_unique_attributes[i]*1.0)*log2((double)entropy_calculation_data[i][1]/count_unique_attributes[i]*1.0));
            System.out.print("Entropy of " +unique_elements[i]+ ": " +entropy+ "\n");
            
            net_entropy += ((double)count_unique_attributes[i]/table.size()*1.0)*entropy;
        }
        
        System.out.println("\nNet Entropy of " +attributes.get(index)+ ": " +net_entropy);
        
        gain = dataset_entropy - net_entropy;
        System.out.print("Gain of " +attributes.get(index)+ ": " +gain+ "\n");
        
        System.out.println("-------------------------------------------------------");
        return gain;
    }
    
    public static void main(String[] args) {
        int n_records;
        int n_attributes;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the number of records: ");
        n_records = input.nextInt();
        
        System.out.print("\nEnter the number of attributes: ");
        n_attributes = input.nextInt();
        
        ArrayList<String> attributes = new ArrayList<String>();
        
        System.out.println("\nEnter the names of the attributes: ");
        for(int i = 0; i<n_attributes; i++) {
            System.out.print("Enter name of attribute " +(i+1)+ ": ");
            attributes.add(input.next().toLowerCase());
        }
        
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();        
        int number_of_C0 = 0;
        int number_of_C1 = 0;
        
        System.out.println("\nEnter the dataset data(row wise): ");
        for(int i = 0; i<n_records; i++) {
            ArrayList<String> temp = new ArrayList<String>();
            for(int j = 0; j<n_attributes; j++) {
                String value;
                value = input.next();
                if("c0".equals(value.toLowerCase()))
                    number_of_C0++;
                else if("c1".equals(value.toLowerCase()))
                    number_of_C1++;
                temp.add(value.toLowerCase());
            }
            table.add(temp);
        }
        
        System.out.println("\nDataset: ");
        for(int i = 0; i<n_attributes; i++)
            System.out.print(attributes.get(i) + "   ");
        System.out.println();
        
        for(int i = 0; i<n_records; i++) {
            for(int j = 0; j<n_attributes; j++)
                System.out.print(table.get(i).get(j)+ "        ");
            System.out.println();
        }
        
        double dataset_entropy;
        double current_gain;
        double max_gain;
        int max_gain_index;
        boolean further_splitted = true;
        String previous_max_gain_attribute = "";
        int iteration = 1;
        
        while(further_splitted) {
            max_gain = 0;
            max_gain_index = 0;
            System.out.print("=======================================================");
            System.out.println("\nCount of c0: " +number_of_C0);
            System.out.println("Count of c1: " +number_of_C1);
            
            dataset_entropy = -(((double)number_of_C0/table.size()*1.0)*log2((double)number_of_C0/table.size())+((double)number_of_C1/table.size()*1.0)*log2((double)number_of_C1/table.size())); 
            System.out.println("Dataset Entropy: " +dataset_entropy);
            System.out.println("=======================================================");
            
            for(int i = 1; i<n_attributes - 1; i++) {
                if(previous_max_gain_attribute.equals(attributes.get(i)))
                    continue;
                current_gain = Calculate(i, n_records, table, n_attributes, attributes, dataset_entropy);
                if(current_gain > max_gain) {
                    max_gain = current_gain;
                    max_gain_index = i;
                }
            }

            System.out.println("\nMax Gain Attribute: " +attributes.get(max_gain_index));
            System.out.println("Max Gain: " +max_gain);

            if(iteration == 1)
                System.out.println("\n" +attributes.get(max_gain_index)+ " will be the root node of the decision tree");
            
            Set<String> newSet = new LinkedHashSet<String>();

            for(int i = 0; i<table.size(); i++)
                newSet.add(table.get(i).get(max_gain_index));

            String[] new_unique_elements = new String[newSet.size()];
            newSet.toArray(new_unique_elements);

            boolean[][] purity = new boolean[newSet.size()][2];

            for(int i = 0; i<new_unique_elements.length; i++) {
                for(int j = 0; j<table.size(); j++) {
                    if(new_unique_elements[i].equals(table.get(j).get(max_gain_index)) && "c0".equals(table.get(j).get(n_attributes-1)))
                       purity[i][0] = true;
                    else if(new_unique_elements[i].equals(table.get(j).get(max_gain_index)) && "c1".equals(table.get(j).get(n_attributes-1)))
                       purity[i][1] = true;
                }
            }
            
            String not_eliminate = "";
            
            for(int i = 0; i<new_unique_elements.length; i++) {
                if(purity[i][0] == true && purity[i][1] == true) {
                    further_splitted = true;
                    not_eliminate = new_unique_elements[i];
                    break;
                }
                else
                    further_splitted = false;
            }

            previous_max_gain_attribute = attributes.get(max_gain_index);

            for(int i = 0; i<table.size(); i++) {
                if(not_eliminate.equals(table.get(i).get(max_gain_index)))
                    continue;           
                else {
                    table.remove(i);
                    i--;
                }
            }

            if(!table.isEmpty()) {
                System.out.println("\nThe dataset to be considered to evaluate " +attributes.get(max_gain_index)+ " = " +not_eliminate+ " is:");
                System.out.println("\nSub Dataset:");
                for(int i = 0; i<n_attributes; i++)
                    System.out.print(attributes.get(i) + "   ");
                System.out.println();

                for(int i = 0; i<table.size(); i++) {
                    for(int j = 0; j<n_attributes; j++)
                        System.out.print(table.get(i).get(j)+ "        ");
                    System.out.println();
                }

                number_of_C0 = 0;
                number_of_C1 = 0;
                
                for(int i = 0; i<table.size(); i++) {
                    if("c0".equals(table.get(i).get(n_attributes-1)))
                        number_of_C0++;
                    else
                        number_of_C1++;
                }
            }
            iteration++;
        }
    } 
}