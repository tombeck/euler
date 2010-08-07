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
package com.thomas.problem284;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.03.2010
 */
public class Problem284 implements Problem {

    private static final char[] BASE14 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd'};

    /**
     * TODO Method documentation 5a411d7b
     * 
     * @return
     * @throws Exception
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.03.2010
     */
    @Override
    public String solve() {

        final int max = 10000;
        
        final int[] x = new int[max];
        final int[] x2 = new int[2 * max];
        final int[] y = new int[max];
        final int[] y2 = new int[2 * max];
        
        int sum = 1 + 8 + 7;
        
        x[0] = 8;
        y[0] = 7;
        for (int n = 1, sumx = 8, sumy = 7; n < max; ++n) {
            add(x, x2, n - 1);
            add(y, y2, n - 1);
            sumx += x[n] = (14 - x2[n]) % 14;
            sumy += y[n] = y2[n];
            if (x[n] > 0) sum += sumx;
            if (y[n] > 0) sum += sumy;
        }

        return toBase14(sum);
    }

    private void add(int[] x, int[] x2, int n) {
        
        final int d2 = x[n] << 1;
        
        int carry = 0;
        
        for (int i = 0; i < n; ++i) {
            x2[n + i] = (carry = d2 * x[i] + x2[n + i] + carry) % 14;
            carry /= 14;
        }
        
        int sqr = x[n] * x[n] + carry;
        x2[2 * n] = sqr % 14;
        x2[2 * n + 1] = sqr / 14;
    }
    
    private String toBase14(long n) {

        final StringBuilder builder = new StringBuilder();
        
        for (; n > 0; n /= 14) {
            builder.append(BASE14[(int)(n % 14)]);
        }
        
        return builder.reverse().toString();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 31.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem284());
    }

}
