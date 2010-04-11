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
package com.thomas.incubator;

import static java.lang.Integer.highestOneBit;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.DOWN;

import java.math.BigDecimal;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 07.04.2010
 */
class Problem192 implements Problem {

    private static final BigDecimal TWO = valueOf(2);

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 07.04.2010
     */
    @Override
    public Object solve() {

        long max = 1000000000000L;
        double x = Math.sqrt(13);
        long[] h = {0, 1};
        long[] k = {1, 0};
        
        for (int n = 0; ; ++n) {
            
            final long r = (long)x;
            
            long h_ = 0;
            long k_ = 0;
            for (long i = (long)Math.ceil(r / 2.0); i <= r; ++i) {
                h_ = h[1] * i + h[0];
                k_ = k[1] * i + k[0];
            }
            h = new long[] {h[1], h_};
            k = new long[] {k[1], k_};
            
            x = 1 / (x - r);
        }        
    }

    private BigDecimal sqrt(int n, int scale) {
        
        final BigDecimal s = valueOf(n);
        
        BigDecimal sqrt = valueOf(highestOneBit(n) >> 1); // initial guess
        
        for (BigDecimal i; !(i = s.divide(sqrt, scale, DOWN).add(sqrt).divide(TWO, scale, DOWN)).equals(sqrt); sqrt = i) { /**/ }

        return sqrt;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 07.04.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem192());
    }

}
