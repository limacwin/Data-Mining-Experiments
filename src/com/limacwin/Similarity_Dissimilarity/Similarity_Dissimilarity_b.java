package com.limacwin.Similarity_Dissimilarity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Similarity_Dissimilarity_b {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file1 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_First.txt"));
        Scanner file2 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Second.txt"));
        Scanner file3 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Third.txt"));
        Scanner file4 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Fourth.txt"));
        Scanner file5 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Fifth.txt"));

        ArrayList<String> words = new ArrayList<>();
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

        int document_size = words.size();

        int[] document_1 = new int[document_size];
        Arrays.fill(document_1, 0);
        int[] document_2 = new int[document_size];
        Arrays.fill(document_2, 0);
        int[] document_3 = new int[document_size];
        Arrays.fill(document_3, 0);
        int[] document_4 = new int[document_size];
        Arrays.fill(document_4, 0);
        int[] document_5 = new int[document_size];
        Arrays.fill(document_5, 0);

        file1 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_First.txt"));
        file2 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Second.txt"));
        file3 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Third.txt"));
        file4 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Fourth.txt"));
        file5 = new Scanner(new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Document_Fifth.txt"));

        while(file1.hasNext ()) {
            String token = file1.next();
            for (int i = 0; i < document_size; i++) {
                if (Objects.equals(token, words.get(i)))
                    document_1[i] += 1;
            }
        }

        while(file2.hasNext ()) {
            String token = file2.next();
            for (int i = 0; i < document_size; i++) {
                if (Objects.equals(token, words.get(i)))
                    document_2[i] += 1;
            }
        }

        while(file3.hasNext ()) {
            String token = file3.next();
            for (int i = 0; i < document_size; i++) {
                if (Objects.equals(token, words.get(i)))
                    document_3[i] += 1;
            }
        }

        while(file4.hasNext ()) {
            String token = file4.next();
            for (int i = 0; i < document_size; i++) {
                if (Objects.equals(token, words.get(i)))
                    document_4[i] += 1;
            }
        }

        while(file5.hasNext ()) {
            String token = file5.next();
            for (int i = 0; i < document_size; i++) {
                if (Objects.equals(token, words.get(i)))
                    document_5[i] += 1;
            }
        }

        int d1d2 = 0, d1d3 = 0, d1d4 = 0, d1d5 = 0,  d2d3 = 0, d2d4 = 0, d2d5 = 0, d3d4 = 0, d3d5 = 0, d4d5 = 0;

        for (int i = 0; i < document_size; i++) {
            d1d2 += document_1[i] * document_2[i];
            d1d3 += document_1[i] * document_3[i];
            d1d4 += document_1[i] * document_4[i];
            d1d5 += document_1[i] * document_5[i];
            d2d3 += document_2[i] * document_3[i];
            d2d4 += document_2[i] * document_4[i];
            d2d5 += document_2[i] * document_5[i];
            d3d4 += document_3[i] * document_4[i];
            d3d5 += document_3[i] * document_5[i];
            d4d5 += document_4[i] * document_5[i];
        }

        double modD1 = 0, modD2 = 0, modD3 = 0, modD4 = 0, modD5 = 0;
        for (int i = 0; i < document_size; i++) {
            modD1 += (document_1[i] * document_1[i]);
            modD2 += (document_2[i] * document_2[i]);
            modD3 += (document_3[i] * document_3[i]);
            modD4 += (document_4[i] * document_4[i]);
            modD5 += (document_5[i] * document_5[i]);
        }

        modD1 = Math.sqrt(modD1);
        modD2 = Math.sqrt(modD2);
        modD3 = Math.sqrt(modD3);
        modD4 = Math.sqrt(modD4);
        modD5 = Math.sqrt(modD5);

        double cosD1D2 = (double) (d1d2) / (modD1 * modD2);
        double cosD1D3 = (double) (d1d3) / (modD1 * modD3);
        double cosD1D4 = (double) (d1d4) / (modD1 * modD4);
        double cosD1D5 = (double) (d1d5) / (modD1 * modD5);
        double cosD2D3 = (double) (d2d3) / (modD2 * modD3);
        double cosD2D4 = (double) (d2d4) / (modD2 * modD4);
        double cosD2D5 = (double) (d2d5) / (modD2 * modD5);
        double cosD3D4 = (double) (d3d4) / (modD3 * modD4);
        double cosD3D5 = (double) (d3d5) / (modD3 * modD5);
        double cosD4D5 = (double) (d4d5) / (modD4 * modD5);

        System.out.println("\nCos(D1, D1) = " + 1);
        System.out.println("Cos(D1, D2) = " + cosD1D2);
        System.out.println("Cos(D1, D3) = " + cosD1D3);
        System.out.println("Cos(D1, D4) = " + cosD1D4);
        System.out.println("Cos(D1, D5) = " + cosD1D5);
        System.out.println("Cos(D2, D2) = " + 1);
        System.out.println("Cos(D2, D3) = " + cosD2D3);
        System.out.println("Cos(D2, D5) = " + cosD2D4);
        System.out.println("Cos(D2, D5) = " + cosD2D5);
        System.out.println("Cos(D3, D3) = " + 1);
        System.out.println("Cos(D3, D4) = " + cosD3D4);
        System.out.println("Cos(D3, D5) = " + cosD3D5);
        System.out.println("Cos(D4, D4) = " + 1);
        System.out.println("Cos(D4, D5) = " + cosD4D5);
        System.out.println("Cos(D5, D5) = " + 1);
    }
}
