/**
 * $Id$
 *
 * Copyright (c) 2015 Thomas Beckmann
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
package com.thomas.problem5xx.problem50x;

import static com.thomas.util.NumberUtils.modPow;
import static com.thomas.util.PrimeUtils.primes;
import static java.lang.Math.log;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 25.05.2015
 */
public class Problem500 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final PriorityQueue<long[]> queue = new PriorityQueue<long[]>(500500, new Comparator<long[]>() {

            @Override
            public int compare(long[] o1, long[] o2) {

                double p1 = o1[1] * log(o1[0]);
                double p2 = o2[1] * log(o2[0]);
                
                if (p1 < p2) return -1;
                if (p1 > p2) return  1;

                return 0;
            }
            
            
        });
                        
        for (int i: primes(7376508)) {
            queue.add(new long[] {i, 1});
        }
        
        long number = 1;
        
        for (int i = 0; i < 500500; ++i) {
            long[] prime = queue.remove();
            number = (number * modPow(prime[0], prime[1], 500500507L)) % 500500507;
            prime[1] *= 2;
            queue.add(prime);
        }
        
        return number;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem500());
    }

}
