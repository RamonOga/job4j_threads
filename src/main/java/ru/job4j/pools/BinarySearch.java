package ru.job4j.pools;

import java.lang.reflect.Array;
import java.util.function.BiFunction;

public class BinarySearch {
    public int recursiveSearch(int[] nums, int index, int from, int to) {
        if (index > to || index < from) {
            return -1;
        }
        int mid = (from + to) / 2;
        if (mid == index) {
            return nums[mid];
        } else if (mid > index) {
            return recursiveSearch(nums, index, from, mid - 1);
        } else {
            return recursiveSearch(nums, index, mid + 1, to);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.recursiveSearch(nums, 8, 0, nums.length - 1));

    }
}
