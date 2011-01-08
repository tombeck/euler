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
package com.thomas.problem266;

import static com.thomas.util.NumberUtils.sqrt;
import static com.thomas.util.NumberUtils.toBigIntegerArray;
import static com.thomas.util.PrimeUtils.primes;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.copyOfRange;
import static java.util.Collections.sort;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 14.03.2010
 */
public class Problem266 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger solve() {

        final BigInteger[] primes = toBigIntegerArray(primes(190));

        final List<BigInteger> l = new ArrayList<BigInteger>();
        final List<BigInteger> h = new ArrayList<BigInteger>();
        
        l.add(ONE);
        h.add(ONE);
        
        for (BigInteger prime : copyOfRange(primes, 0, primes.length / 2)) {
            for (BigInteger p : new ArrayList<BigInteger>(l)) {
                l.add(prime.multiply(p));
            }
        }
        for (BigInteger prime : copyOfRange(primes, primes.length / 2, primes.length)) {
            for (BigInteger p : new ArrayList<BigInteger>(h)) {
                h.add(prime.multiply(p));
            }
        }
        
        sort(l);
        sort(h);
        
        final int lMax = l.size() - 1;
        final int hMax = h.size() - 1;
        final BigInteger sqrt = sqrt(l.get(lMax).multiply(h.get(hMax)));
        
        BigInteger best = ZERO;
        
        for (int i = 0, j = hMax; i <= lMax && j >= 0; ) {
            
            final BigInteger current = l.get(i).multiply(h.get(j));
            final int c = current.compareTo(sqrt);
            
            if (c > 0) {
                j -= 1;
            } else if (c < 0) {
                if (current.compareTo(best) > 0) best = current;
                i += 1;
            }
        }
        
        return best.mod(TEN.pow(16));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem266());
    }

}
