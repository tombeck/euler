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

import static com.thomas.util.PrimeUtils.isPrime;
import static com.thomas.util.PrimeUtils.primes;
import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 04.02.2010
 */
class Problem263 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 04.02.2010
     */
    @Override
    public Object solve() {

        final int max = 10000;
        final List<Integer> primes = primes((int)sqrt(max));
        final List<Integer> pair = new ArrayList<Integer>();
        final List<Integer> practical = new ArrayList<Integer>();
        
        int c1 = 0;
        int c2 = 0;
        
        for (int i = 5; i < max; i += 6) {
            if (!isPrime(i, primes)) c1 = 0;
            else if (++c1 >= 4) pair.add(i - 9);
            if (!isPrime(i + 2, primes)) c2 = 0;
            else if (++c2 >= 4) pair.add((i + 2) - 9);
        }
        c1 = 0;
        c2 = 0;
        for (int i = 2; i < max; i += 4) {
            if (!NumberUtils.isPractical(i)) c1 = 0;
            else if (++c1 >= 5) practical.add((i) - 8);
            if (!NumberUtils.isPractical(i + 2)) c2 = 0;
            else if (++c2 >= 5) practical.add((i + 2) - 8);
        }
        System.out.println(pair);
        System.out.println(practical);
        practical.retainAll(pair);
        System.out.println(practical);
        return null;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 04.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem263());
    }

}
