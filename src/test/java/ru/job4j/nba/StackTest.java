package ru.job4j.nba;

import static org.junit.Assert.*;

import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    @Test
    public void when3PushThen3Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assert.assertEquals((Integer) 3, stack.poll());
        Assert.assertEquals((Integer) 2, stack.poll());
        Assert.assertEquals((Integer) 1, stack.poll());
    }

    @Test
    public void when1PushThen1Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        Assert.assertEquals((Integer) 1, stack.poll());

    }

    @Test
    public void when2PushThen2Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        Assert.assertEquals((Integer) 2, stack.poll());
        Assert.assertEquals((Integer) 1, stack.poll());
    }
}