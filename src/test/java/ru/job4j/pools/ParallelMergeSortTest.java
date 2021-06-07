package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelMergeSortTest {
    @Test
    public void whenArrayLessThan10AndIndexFound() {
        ParallelBinarySearch pbs = new ParallelBinarySearch();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int expected = 1;
        Assert.assertEquals(expected, pbs.search(nums, 0));
    }

    @Test
    public void whenArrayMoreThan10AndIndexFound() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        ParallelBinarySearch pbs = new ParallelBinarySearch(nums, 19, 0, nums.length - 1);
        int expected = 20;
        Assert.assertEquals(expected, pbs.search(nums, 19));
    }

    @Test
    public void whenArrayMoreThan10AndIndexNoFound() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        ParallelBinarySearch pbs = new ParallelBinarySearch(nums, 19, 0, nums.length - 1);
        int expected = -1;
        Assert.assertEquals(expected, pbs.search(nums, 33));

    }
    @Test
    public void whenArrayLessThan10AndIndexNoFound() {
        ParallelBinarySearch pbs = new ParallelBinarySearch();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int expected = -1;
        Assert.assertEquals(expected, pbs.search(nums, -22));
    }

}