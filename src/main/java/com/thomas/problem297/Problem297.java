/**
 * $Id$
 *
 * Copyright (c) 2010 Thomas Beckmann
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.thomas.problem297;

import static com.thomas.util.NumberUtils.toLongArray;
import static java.util.Arrays.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem297 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long max = 100000000000000000L;
        final Map<Long, Long> cache = new HashMap<Long, Long>();
        
        final List<Long> fib = new ArrayList<Long>();
        
        for (long[] f = {1, 1}; f[1] < max; f = new long[] {f[1], f[0] + f[1]}) fib.add(f[1]);

        return sum(cache, toLongArray(fib), max);
    }

    private long sum(Map<Long, Long> cache, long[] fib, long n) {
        
        if (n == 1) return n - 1;
        
        Long sum = cache.get(n);
        
        if (sum != null) return sum;
        
        final int i = index(fib, n);
        final long diff = n - fib[i];
        
        cache.put(n, sum = diff + sum(cache, fib, diff) + sum(cache, fib, fib[i]));
        
        return sum;
    }
    
    private int index(long[] fib, long n) {
        
        final int i = binarySearch(fib, n);
        
        return i < 0 ? -(i + 2) : i - 1;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem297());
    }

}
