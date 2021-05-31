package ru.job4j.cuncurrentmap;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void thenAddOneBase() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Assert.assertTrue(cache.add(base));
    }

    @Test(expected = OptimisticException.class)
    public void thenUpdateNoEqualVersions() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(1, 1);
        cache.add(base1);
        cache.update(base2);
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        cache.add(base1);
        cache.update(base1);
        Assert.assertEquals(1, cache.get(base1).getVersion());
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        cache.add(base1);
        cache.delete(base1);
        Assert.assertNull(cache.get(base1));
    }
}