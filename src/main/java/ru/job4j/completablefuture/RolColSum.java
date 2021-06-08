package ru.job4j.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] rslSums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rslSums[i] = new Sums(calculateRow(matrix, i),calculateCol(matrix, i));
        }
        return rslSums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = getTask(matrix, i).get();
        }
        return rsl;
    }

    public static CompletableFuture<Sums> getTask(int[][] mat, int index) {
        return CompletableFuture.supplyAsync(() -> new Sums(calculateRow(mat, index),calculateCol(mat, index)));

    }

    private static int calculateRow(int[][] mat, int row) {
        int rsl = 0;

        for (int i = 0; i < mat[row].length; i++) {
            rsl += mat[row][i];
        }
        return rsl;
    }

    private static int calculateCol(int[][] mat, int col) {
        int rsl = 0;
            for (int i = 0; i < mat[col].length; i++) {
                rsl += mat[i][col];
            }
        return rsl;
    }

}