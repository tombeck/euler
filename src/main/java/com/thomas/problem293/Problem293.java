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
package com.thomas.problem293;

import static com.thomas.util.PrimeUtils.nextPrime;
import static com.thomas.util.PrimeUtils.primes;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem293 implements Problem {

    static List<Integer> PRIMES = primes(31622); // = sqrt(1000000000)
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int f : findFortunate(new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29}, 0, 1, new TreeSet<Integer>())) {
            sum += f;
        }
        
        return sum;
    }

    private Set<Integer> findFortunate(int[] primes, int i, long product, Set<Integer> fortunate) {
        
        product *= primes[i];
        
        while (product < 1000000000) {
            fortunate.add((int)(nextPrime(product + 2, PRIMES) - product));
            findFortunate(primes, i + 1, product, fortunate);
            product *= primes[i];
        }
        
        return fortunate;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem293());
    }

}
