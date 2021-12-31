package com.limacwin.Apriori_Algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Apriori_Algorithm {
    private static Integer getSupportCount(Map < String, String > orig_set, String i) {
        int s = 0;
        for (String key: orig_set.keySet()) {
            String s1 = orig_set.get(key);
            String s2 = i;
            String[] a;
            if (s2.contains(",")) 
                a = s2.split(",");
            else {
                a = new String[1];
                a[0] = s2;
            }
            
            boolean flag = true;
            for (int k = 0; k < a.length; k++) {
                if (!s1.contains(a[k])) {
                    flag = false;
                    break;
                }
            }       
            if (flag)
                s++;
        }
        return s;
    }

    private static Vector < String > generateCombinations(Vector < String > unique_items) {
        Vector < String > candidate_set = new Vector < > ();
        for (int i = 0, start = 0, end = 0; i < unique_items.size() - 1; i++) {
            if (candidate_set.isEmpty()) {
                for (String x: unique_items) {
                    candidate_set.add(x);
                    end++;
                }
            } 
            else {
                int new_end = end;
                String ignore = "";
                for (int j = start; j < end; j++) {
                    String t1 = candidate_set.get(j);
                    ignore = ignore + " " + t1;
                    for (int k = 0; k < unique_items.size(); k++) {
                        if (!t1.contains(unique_items.get(k))) {
                            String t2 = t1 + "," + unique_items.get(k);
                            if (!ignoreRequired(candidate_set, t2)) {
                                candidate_set.add(t2);
                                new_end++;
                            }
                        }
                    }
                }
                start = end;
                end = new_end;
            }
        }
        return candidate_set;
    }

    private static String String_sort(String inputString) {
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    private static boolean ignoreRequired(Vector < String > v1, String s2) {
        boolean ignore = false;
        s2 = String_sort(s2.toLowerCase());
        for (String s1: v1) {
            s1 = String_sort(s1.toLowerCase());
            if (s1.equals(s2)) {
                ignore = true;
                break;
            } 
            else
                ignore = false;
        }
        return ignore;
    }

    public static void main(String[] args) {
        Map < String, String > orig_set = new HashMap < > ();
        Map < String, Integer > l = new HashMap < > ();
        Vector < String > itemset = new Vector < > ();
        Vector < Integer > l_count = new Vector < > ();
        Vector < String > unique_items = new Vector < > ();
        Vector < String > candidate_set;
        int min_support = 0, min_confidence = 0, i, n = 0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/com/limacwin/Apriori_Algorithm/input.txt"));
            //number of transactions
            n = Integer.parseInt(br.readLine());
            //transaction list
            for (i = 0; i < n; i++)
                orig_set.put(String.valueOf(i + 1), br.readLine());
            //min support
            min_support = Integer.parseInt(br.readLine());
            min_confidence = Integer.parseInt(br.readLine());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of transactions : " + n);
        System.out.println("The transactions");
        orig_set.forEach((k, v) ->System.out.println(" Transaction " + k + " : " + v));
        System.out.print("\nMinimun support: " + min_support);
        System.out.print("\tMinimun confidence: " + min_confidence + "%\n");
        
        for (String key: orig_set.keySet()) {
            String[] t1 = orig_set.get(key).split(",");
            for (String t: t1) {
                if (!unique_items.contains(t)) unique_items.add(t);
            }
        }
        
        Collections.sort(unique_items);
        System.out.println("\nUnique Items are " + unique_items);
        candidate_set = generateCombinations(unique_items);
        for (String values: candidate_set) {
            Integer s = getSupportCount(orig_set, values);
            if (s >= min_support) 
            {
                itemset.add(values);
                l_count.add(s);
                l.put(values, s);
            }
        }

        int index = itemset.size() - 1;
        for (i = index--; i < itemset.size(); i++) {
            String[] a = itemset.get(i).split(",");
            String[] b = itemset.get(index).split(",");
            if (a.length < b.length) {
                index = i + 1;
                break;
            }
        }
        
        System.out.println("L set generated is as follows");
        for (i = 0; i < itemset.size(); i++)
            System.out.println(itemset.get(i) + "\t\t" + l_count.get(i));
        
        while (index < l.size()) {
            String[] elements = itemset.get(index).split(",");
            Vector < String > evector = new Vector < > ();
            
            for (String in: elements) evector.add( in );
                evector = generateCombinations(evector);

            Vector < Double > per = new Vector < > ();
            System.out.println("\nSelected Item combination = " + itemset.get(index));
            System.out.print("\nItem Combinations are {");
            for (i = 0; i < evector.size(); i++)
                System.out.print(" {" + evector.get(i) + "} ");
            System.out.println(" }\n");

            for (i = 0; i < evector.size(); i++) {
                String key1 = itemset.get(index);
                String key2 = evector.get(i);
                Double ans = (double) l.get(key1) / l.get(key2);
                per.add(ans * 100);
                System.out.print("Confidence(" + key2 + "->" + key1 + ") = ");
                System.out.printf("%.4f\n", ans * 100);
            }

            for (i = 0; i < per.size(); i++)
                if (per.get(i) >= min_confidence)
                    System.out.println("Items with highest confidence : " + evector.get(i));
            index++;
        }
    }
}