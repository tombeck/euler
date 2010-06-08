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
package com.thomas.problem211;

import static com.thomas.util.NumberUtils.isSquare;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem211 implements Problem {

    /**
     * if n is prime, sigma_2(n) is not a sqare
     * 
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int max = 64000000;
        final long[] sigma2 = new long[max + 1];
        
        long sum = 0;

        for (int n = 1; n <= max; n++) {
            for (int i = n; i <= max; i += n) {
                sigma2[i] += (long)n * n;
            }
            if (isSquare(sigma2[n])) sum += n;
        }

        return sum;

//        final int[] primes = toIntArray(primes(8000));// = sqrt(64000000)
//        
//        long sum = 0;
//        
//        for (int n = 1; n < 64000000; ++n) {
//            
//            int t = n;
//            long product = 1;
//            int sqr = 0;
//            
//            for (int p : primes) {
//
//                if ((sqr = p * p) > t) break;
//                if (t % p == 0) {
//                    long s = 1;
//                    do {
//                        s = s * sqr + 1;
//                    } while ((t /= p) % p == 0);
//                    product *= s;
//                }
//            }
//            if (t > 1) {
//                if (t == n) continue; // n is prime
//                product *= 1 + (long)t * t;
//            }
//            
//            if (isSquare(product)) {
//                //System.out.println(n + ": " + product);
//                sum += n;
//            }
//        }
//        
//        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem211());
    }

}
