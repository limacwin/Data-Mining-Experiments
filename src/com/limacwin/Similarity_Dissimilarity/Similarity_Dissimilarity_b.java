package com.limacwin.Similarity_Dissimilarity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Similarity_Dissimilarity_b {
    public static void main(String[] args) throws FileNotFoundException {

        File directory_path = new File("C:\\Users\\Speficifity\\Documents\\Semester VII\\DM\\Practicals\\Data-Mining-Experiments\\src\\com\\limacwin\\Similarity_Dissimilarity\\Documents");

        File[] document_files = directory_path.listFiles();
        assert document_files != null;
        int n_documents = document_files.length;
        System.out.println("\nTotal documents found in the directory: " + n_documents);

        ArrayList<String> words = new ArrayList<>();

        int file_index = 1;
        for (File file : document_files) {
            Scanner file_current = new Scanner(file);
            System.out.print("\nDocument " + file_index);
            while(file_current.hasNext ()) {
                String token = file_current.next();
                System.out.print(" " + token);
                words.add(token);
            }
            file_index++;
        }

        for(int i = 0; i < words.size(); i++) {
            if(words.get(i) != null) {
                for(int j = i + 1; j < words.size(); j++) {

                    if(Objects.equals(words.get(i), words.get(j)))
                        words.remove(j);
                }
            }
        }

        int document_size = words.size();

        int[][] document_weights = new int[n_documents][document_size];

        int index = 0;
        for (File file : document_files) {
            Scanner file_current = new Scanner(file);
            while(file_current.hasNext ()) {
                String token = file_current.next();
                for (int j = 0; j < document_size; j++) {
                    if (Objects.equals(token, words.get(j)))
                        document_weights[index][j] += 1;
                }
            }
            index++;
        }

        Object[] words_print = words.toArray();
        for (int i = 0; i < words_print.length; i++) {
            if (words_print[i].toString().length() < 10) {
                words_print[i] = String.format("%-10s", words_print[i]);
            }
        }

        System.out.println("\n\n\t\t:: Displaying the Document Term Matrix in a Vertical Fashion ::\n");
        System.out.println("\t\t\t\tD1\tD2\tD3\tD4\tD5");
        for (int i = 0; i < document_size; i++) {
            System.out.print("\t" + words_print[i]);
            for (int j = 0; j < n_documents; j++) {
                System.out.print("\t" + document_weights[j][i]);
            }
            System.out.println();
        }

        int[][] dXdY = new int[n_documents][n_documents];

        for (int i = 0; i < n_documents; i++) {
            for (int j = 0; j < n_documents; j++) {
                for (int k = 0; k < document_size; k++) {
                    dXdY[i][j] += document_weights[i][k] * document_weights[j][k];
                }
            }
        }

        double[] modDoc = new double[n_documents];
        for (int i = 0; i < n_documents; i++) {
            for (int j = 0; j < document_size; j++) {
                modDoc[i] += document_weights[i][j] * document_weights[i][j];
            }
        }

        for (int i = 0; i < n_documents; i++) {
            modDoc[i] = Math.sqrt(modDoc[i]);
        }

        double[][] cosdXdY = new double[n_documents][n_documents];

        for (int i = 0; i < n_documents; i++) {
            for (int j = 0; j < n_documents; j++) {
                if (i == j)
                    cosdXdY[i][j] = 1;
                else
                    cosdXdY[i][j] = dXdY[i][j]/(modDoc[i] * modDoc[j]);
            }
        }

        System.out.println("\n\t\t:: Displaying the Calculated Cosine Similarity Values ::\n");
        for (int i = 0; i < n_documents; i++) {
            for (int j = 0; j < n_documents; j++) {
                System.out.printf("Cos D%dD%d: %.4f\t", i+1, j+1, cosdXdY[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n\t\t:: Displaying the Formed Proximity Matrix ::\n");
        for (int i = 0; i < n_documents; i++) {
            if (i == 0) {
                for (int d = 0; d < n_documents; d++)
                    System.out.print("\t  D" + (d + 1));
                System.out.println();
            }
            System.out.print("D" + (i+1));
            for (int j = 0; j < n_documents; j++)
                System.out.printf("\t%.4f", cosdXdY[i][j]);
            System.out.println();
        }

        double most_similar = cosdXdY[0][1];
        for (int i = 0; i < n_documents; i++) {
            for (int j = 0; j < n_documents; j++) {
                if (i != j && cosdXdY[i][j] > most_similar)
                    most_similar = cosdXdY[i][j];
            }
        }

        System.out.println("\n\t\t:: Strongest Similarities between documents ::\n");
        for (int i = 0; i < n_documents; i++) {
            for (int j = 0; j < n_documents; j++) {
                if (i != j && cosdXdY[i][j] == most_similar) {
                    System.out.printf("D%d and D%d: %.4f\n", i+1, j+1, most_similar);
                    cosdXdY[j][i] = -1;
                }
            }
        }

    }
}
