/**
 * $Id$
 * 
 * Copyright (c) 2013 Thomas Beckmann
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

package com.thomas.problem2xx.problem20x;

import static java.lang.Long.numberOfTrailingZeros;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 08.08.2013
 */
public class Problem202 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long n = 12017639147L;
        final long m = (n+3)/2;
                    
        long sum = 0;
        
        for (long x = 3 - m%3, y; (y = m - x) > x; x += 3) {
            if (gcd(x, y) == 1) ++sum;
        }
        
        return 2 * sum;
    }

    long gcd(long x, long y) {
     
        final long cf2 = numberOfTrailingZeros(x | y);
     
        x >>= numberOfTrailingZeros(x);
        for (;;) {
            y >>= numberOfTrailingZeros(y);
            if (x == y) break;
            if (x > y) {
                
                long tmp = x;
                
                x = y;
                y = tmp;
            }
            if (x == 1) break;
            y -= x;
        }
     
        return x << cf2;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem202());
    }

}
