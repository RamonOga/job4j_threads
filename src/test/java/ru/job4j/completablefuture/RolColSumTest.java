package ru.job4j.completablefuture;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class RolColSumTest {
    private int[][] array;
    private RolColSum rolColSum;

    @Before
    public void init() {
        array = new int[][]{{1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}};
        rolColSum = new RolColSum();
    }

    @Test
    public void syncTestWithIndex0() throws ExecutionException, InterruptedException {
        RolColSum.Sums rsl = rolColSum.asyncSum(array)[0];
        int rowExpected = 6;
        int columnExpected = 12;
        Assert.assertEquals(rowExpected, rsl.getRowSum());
        Assert.assertEquals(columnExpected, rsl.getColSum());
    }

    @Test
    public void syncTestWithIndex1() throws ExecutionException, InterruptedException {
        RolColSum.Sums rsl = rolColSum.asyncSum(array)[1];
        int rowExpected = 15;
        int columnExpected = 15;
        Assert.assertEquals(rowExpected, rsl.getRowSum());
        Assert.assertEquals(columnExpected, rsl.getColSum());
    }

    @Test
    public void syncTestWithIndex2() throws ExecutionException, InterruptedException {
        RolColSum.Sums rsl = rolColSum.asyncSum(array)[2];
        int rowExpected = 24;
        int columnExpected = 18;
        Assert.assertEquals(rowExpected, rsl.getRowSum());
        Assert.assertEquals(columnExpected, rsl.getColSum());
    }

    @Test
    public void NoSyncTestWithIndex0() {
        RolColSum.Sums rsl = rolColSum.sum(array)[0];
        int rowExpected = 6;
        int columnExpected = 12;
        Assert.assertEquals(rowExpected, rsl.getRowSum());
        Assert.assertEquals(columnExpected, rsl.getColSum());
    }

    @Test
    public void NoSyncTestWithIndex1() {
        RolColSum.Sums rsl = rolColSum.sum(array)[1];
        int rowExpected = 15;
        int columnExpected = 15;
        Assert.assertEquals(rowExpected, rsl.getRowSum());
        Assert.assertEquals(columnExpected, rsl.getColSum());
    }

    @Test
    public void NoSyncTestWithIndex2() {
        RolColSum.Sums rsl = rolColSum.sum(array)[2];
        int rowExpected = 24;
        int columnExpected = 18;
        Assert.assertEquals(rowExpected, rsl.getRowSum());
        Assert.assertEquals(columnExpected, rsl.getColSum());
    }

}