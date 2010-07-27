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
package com.thomas.problem209;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem209 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final Set<Integer> all = new TreeSet<Integer>();
        
        long count = 1;
        
        for (int i = 0; i < 64; ++i) {
            if (!all.contains(i)) {
                
                int c = 0;
                
                for (int j = i; all.add(j); j = other(j)) ++c;
                count *= fib(c);
            }
        }
        
        return count;
    }

    private long fib(int n) {
        
        long[] x = {1, 3};
        
        if (n < 3) return x[n - 1];

        for (int i = 2; i < n; ++i) x = new long[] {x[1], x[0] + x[1]};
        
        return x[1];
    }
    
    private int other(int n) {
        
        final int a = (n >> 5) & 1;
        final int b = (n >> 4) & 1;
        final int c = (n >> 3) & 1;
        
        return ((n & -33) << 1) | a ^ (b & c);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem209());
    }

}
