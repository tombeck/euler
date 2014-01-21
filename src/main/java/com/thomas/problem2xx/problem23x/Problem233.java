/**
 * $Id$
 *
 * Copyright (c) 2014 Thomas Beckmann
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
package com.thomas.problem2xx.problem23x;

import static com.thomas.util.NumberUtils.pow;
import static com.thomas.util.PrimeUtils.primes;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 21.01.2014
 */
public class Problem233 implements Problem {

    /**
     * (2x - N)^2 + (2y - N)^2 = 2N^2
     *  
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long max = 100000000000L;
        final List<Integer> primes = primes(4733728);
        
        long sum = 0;

        for (int p1: primes) {
            if ((p1-1)%4 == 0) {
                final long N1 = pow((long)p1, 7);
                if (N1 > max) break;
                for (int p2: primes) {
                    if ((p2-1)%4 == 0 && p2 != p1) {
                        final long N2 = N1 * pow((long)p2, 3);
                        if (N2 > max) break;
                        sum += count(N2, primes, max);
                    }
                }
            }
        }
        for (int p1: primes) {
            if ((p1-1)%4 == 0) {
                final long N1 = pow((long)p1, 10);
                if (N1 > max) break;
                for (int p2: primes) {
                    if ((p2-1)%4 == 0 && p2 != p1) {
                        final long N2 = N1 * pow((long)p2, 2);
                        if (N2 > max) break;
                        sum += count(N2, primes, max);
                    }
                }
            }
        }
        for (int p1: primes) {
            if ((p1-1)%4 == 0) {
                final long N1 = pow((long)p1, 3);
                if (N1 > max) break;
                for (int p2: primes) {
                    if ((p2-1)%4 == 0 && p2 != p1) {
                        final long N2 = N1 * pow((long)p2, 2);
                        if (N2 > max) break;
                        for (int p3: primes) {
                            if ((p3-1)%4 == 0 && p3 != p1 && p3 != p2) {
                                final long N3 = N2 * pow((long)p3, 1);
                                if (N3 > max) break;
                                sum += count(N3, primes, max);
                            }
                        }
                    }
                }
            }
        }

        return sum;
    }

    long count(long N, List<Integer> primes, long max) {
        
        long count = N;
        
        for (int i = 0; i < primes.size(); ++i) {
            int p = primes.get(i);
            if (p == 2 || (p-3)%4 == 0) {
                final long N1 = N * p;
                if (N1 > max) break;
                count += count(N1, primes.subList(i, primes.size()), max);
            }
        }

        return count;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem233());
    }

}
