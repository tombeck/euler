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
package com.thomas.problem248;

import static com.thomas.util.NumberUtils.pow;
import static com.thomas.util.PrimeUtils.isPrime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.01.2010
 */
class Problem248 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.01.2010
     */
    @Override
    public Object solve() {

        List<long[]> primes = new ArrayList<long[]>();
        
        for (int p2 = 0; p2 <= 10; ++p2) {
            for (int p3 = 0; p3 <= 5; ++p3) {
                for (int p5 = 0; p5 <= 2; ++p5) {
                    for (int p7 = 0; p7 <= 1; ++p7) {
                        for (int p11 = 0; p11 <= 1; ++p11) {
                            for (int p13 = 0; p13 <= 1; ++p13) {
                                
                                final long prime = pow(2L, p2) * pow(3L, p3) * pow(5L, p5) * pow(7L, p7) * pow(11L, p11) * pow(13L, p13) + 1;
                                
                                if (isPrime(prime)) {
                                    primes.add(new long[] {prime, encode(p13, p11, p7, p5, p3, p2)});
                                    if (prime ==  2L) {
                                        for (int i = 1; i <= 10; ++i) {
                                            primes.add(new long[] {2L * pow(2L, i), encode(p13, p11, p7, p5, p3, p2 + i)});
                                        }
                                    } else if (prime ==  3L) {
                                        for (int i = 1; i <= 5; ++i) {
                                            primes.add(new long[] {3L * pow(3L, i), encode(p13, p11, p7, p5, p3 + i, p2)});
                                        }
                                    } else if (prime ==  5L) {
                                        for (int i = 1; i <= 2; ++i) {
                                            primes.add(new long[] {5L * pow(5L, i), encode(p13, p11, p7, p5 + i, p3, p2)});
                                        }
                                    } else if (prime ==  7) {
                                        primes.add(new long[] {prime * prime, encode(p13, p11, p7 + 1, p5, p3, p2)});
                                    } else if (prime == 11) {
                                        primes.add(new long[] {prime * prime, encode(p13, p11 + 1, p7, p5, p3, p2)});
                                    } else if (prime == 13) {
                                        primes.add(new long[] {prime * prime, encode(p13 + 1, p11, p7, p5, p3, p2)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        Collections.sort(primes, new Comparator<long[]>() {

            @Override
            public int compare(long[] o1, long[] o2) {

                return Long.signum(o2[1] - o1[1]);
            }
            
        });
        
        final List<Long> numbers = new ArrayList<Long>();
        
        findNumbers(primes, 0, 1, 0, numbers, 1);
        Collections.sort(numbers);
        
        return numbers.get(150000 - 1);
    }

    private static long encode(long p13, long p11, long p7, long p5, long p3, long p2) {
    
        return (((((((((p13 << 8) + p11) << 8) + p7) << 8) + p5) << 8) + p3) << 8) + p2;
    }
    
    protected int compareFactors(long[] o1, long[] o2) {

        long diff = 0;
        
        if (diff == 0) diff = o1[6] - o2[6];
        if (diff == 0) diff = o1[5] - o2[5];
        if (diff == 0) diff = o1[4] - o2[4];
        if (diff == 0) diff = o1[3] - o2[3];
        if (diff == 0) diff = o1[2] - o2[2];
        if (diff == 0) diff = o1[1] - o2[1];

        return Long.signum(diff);
    }

    private static long MAX = encode(1, 1, 1, 2, 5, 10);
    
    private void findNumbers(List<long[]> factors, int first, long number, long dist, List<Long> numbers, int iteration) {
        
        if (MAX == dist && NumberUtils.totient(number) == 6227020800L) {
            numbers.add(number);
        }
        
        int i = first;
        
        while(i < factors.size() && factors.get(i)[1] + dist > MAX) { ++i; }
        int j = i;
        long max = getMax(dist);
        while(j < factors.size() && factors.get(j)[1] >= max) { ++j; }
        
        for ( ; i < j; ++i) {
            
            final long[] factor = factors.get(i);
            
            findNumbers(factors, i + 1, number * factor[0], dist + factor[1], numbers, iteration + 1);
        }
    }
    
    private long getMax(long n) {
    
        long max = 1;
        
        long m = MAX;
        
        while (n != m) {
            n >>= 8;
            m >>= 8;
            max <<= 8;
        }

        return max >> 8;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 31.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem248());
    }

}
