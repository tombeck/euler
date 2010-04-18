/**
 * $Id$
 *
 * Copyright (c) 2009 Thomas Beckmann 
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
package com.thomas.problem78;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 25.10.2009
 */
public class Problem78 implements Problem {

    private static int MAX = 100000;
    private static final int[] CACHE = new int[MAX + 1];

    private static int partition(long n) {
        
        if (n < 0) return 0;
        if (n == 0) return 1;
        
        if (CACHE[(int)n] != 0) return CACHE[(int)n];
        
        int p = 0;
        for (int k = 1, sign = -1; k <= n; ++k) {
            sign *= -1;
            long i = n - (k * (3 * (long)k - 1)) / 2;
            long j = n - (k * (3 * (long)k + 1)) / 2;
            p = (p + (partition(i) + partition(j)) * sign) % 1000000;
            if (p < 0) p = p + 1000000;
        }
        CACHE[(int)n] = p;
        
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() throws Exception {

        for (int n = 0; ; ++n) {
            if (partition(n) == 0) return n;
        }
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 25.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem78());
    }

}
