package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelBinarySearch extends RecursiveTask<Integer> {
    private final int[] nums;
    private final int index;
    private final int from;
    private final int to;


    public ParallelBinarySearch(int[] nums,int index, int from, int to) {
        this.nums = nums;
        this.index = index;
        this.from = from;
        this.to = to;
    }

    public ParallelBinarySearch() {
        nums = null;
        index = -1;
        from = -1;
        to = -1;

    }

    @Override
    protected Integer compute() {

        if (index > to || index < from) {
            return -1;
        }
        int mid = (from + to) / 2;
        ParallelBinarySearch currentSearch;
        if (mid == index) {
            return nums[mid];
        } else if (mid > index) {
            currentSearch = new ParallelBinarySearch(nums, index, from, mid - 1);
        } else {
            currentSearch = new ParallelBinarySearch(nums, index, mid + 1, to);
        }
        currentSearch.fork();
        return currentSearch.join();
    }

    public int search(int[] nums, int index) {
        if (nums.length <= 10 ) {
            return simpleSearch(nums, index);
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(
                new ParallelBinarySearch(nums, index, 0, nums.length - 1)
        );
    }

    private int simpleSearch(int[] nums, int index) {
        int rsl = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i == index) {
                rsl = nums[i];
                break;
            }
        }
        return rsl;
    }
}
