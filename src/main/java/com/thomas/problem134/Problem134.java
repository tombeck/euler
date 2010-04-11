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
package com.thomas.problem134;

import java.util.Iterator;
import java.util.List;

import com.thomas.util.Euler;
import static com.thomas.util.PrimeUtils.*;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 22.12.2009
 */
class Problem134 implements Problem {

    private static final int[][] TABLE = {
        {}, // 0
        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, // 1
        {}, // 2
        {0, 7, 4, 1, 8, 5, 2, 9, 6, 3}, // 3
        {}, // 4
        {}, // 5
        {}, // 6
        {0, 3, 6, 9, 2, 5, 8, 1, 4, 7}, // 7
        {}, // 8
        {0, 9, 8, 7, 6, 5, 4, 3, 2, 1}, // 9
    };
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 22.12.2009
     */
    @Override
    public Long solve() {

        final List<Integer> primes = primes(1000003+1);
        final Iterator<Integer> i = primes.iterator();
        
        int prev = 0; while ((prev = i.next()) != 5) { /**/ }

        long sum = 0;
        while (i.hasNext()) {
            final int next = i.next();
            long n = 0;
            for (long t = 1 ; prev > 0; t *= 10) {
                int from = (int)((n / t) % 10);
                int to = prev % 10;
                n += t * next * TABLE[next % 10][(from > to ? 10 - (from - to) : to - from)];
                prev /= 10;
            }
            sum += n;
            prev = next;
        }

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 22.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem134());
    }

}
