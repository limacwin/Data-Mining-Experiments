package com.limacwin.Similarity_Dissimilarity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Similarity_Dissimilarity_b {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file1 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_First.txt"));
        Scanner file2 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Second.txt"));
        Scanner file3 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Third.txt"));
        Scanner file4 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Fourth.txt"));
        Scanner file5 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Fifth.txt"));

        ArrayList<String> words = new ArrayList<String>();
        while(file1.hasNext ()) {
            String token = file1.next();
            words.add(token);
        }
        while(file2.hasNext ()) {
            String token = file2.next();
            words.add(token);
        }
        while(file3.hasNext ()) {
            String token = file3.next();
            words.add(token);
        }
        while(file4.hasNext ()) {
            String token = file4.next();
            words.add(token);
        }

        while(file5.hasNext ()) {
            String token = file5.next();
            words.add(token);
        }

        System.out.println();
        for (String word : words) {
            System.out.print(word + " ");
        }

        for(int i = 0; i < words.size(); i++) {
            if(words.get(i) != null) {

                for(int j = i + 1; j < words.size(); j++) {

                    if(Objects.equals(words.get(i), words.get(j)))
                        words.remove(j);
                }
            }
        }

        System.out.println();
        for (String word : words) {
            System.out.print(word + " ");
        }


    }
}
