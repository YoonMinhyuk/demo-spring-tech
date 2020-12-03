package me.demo.reactive;

import org.junit.jupiter.api.Test;

public class Temp {
    @Test
    void test() {
        int n = 90;
        System.out.println(fib(n));
        System.out.println(fib2(n));
    }

    private int fib(final int n) {
        if (n == 0) return 0;
        int[] memoization = new int[n + 1];
        memoization[0] = 0;
        memoization[1] = 1;
        for (int i = 2; i <= n; i++) {
            memoization[i] = memoization[i - 1] + memoization[i - 2];
        }
        return memoization[n];
    }

    private int fib2(final int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}