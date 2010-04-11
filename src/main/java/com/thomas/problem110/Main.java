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
package com.thomas.problem110;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 04.11.2009
 */
public class Main {

    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};
    
    /**
     * TODO Method documentation
     * 
     * 2^58 < 614889782588491410 (product of PRIMES)
     * 
     * @param args
     * @author Thomas
     * @throws InterruptedException 
     * @since 04.11.2009
     */
    public static void main(String[] args) throws InterruptedException {

        final int[] powers = new int[PRIMES.length];
        BigInteger best = BigInteger.valueOf(614889782588491410L);
        
        for (int s = 15; s < 59; ++s) {
            best = nextPower(powers, 0, s, s, best);
        }
//        System.out.println(Prime.getPrimeFactors(1260));
//        
//        System.out.println(countDistinctSolutions(2, 2, 1, 1));
    }

    private static BigInteger nextPower(int[] powers, int i, int rem, int max, BigInteger best) throws InterruptedException {
        
        if (i >= powers.length) {
            Thread.sleep(1);
            final long count = countDistinctSolutions(powers);
            if (count > 4000000) {
                final BigInteger n = getProduct(powers);
                if (n.compareTo(best) < 0) {
                    System.out.println(Arrays.toString(powers) + " = " + n);
                    return n;
                }
            }
        } else {
            for (int p = rem / (powers.length - i); p <= max && p <= rem; ++p) {
                powers[i] = p;
                best = nextPower(powers, i + 1, rem - p, p, best);
            }
        }
        
        return best;
    }
    
    private static BigInteger getProduct(int... powers) {
    
        BigInteger product = BigInteger.ONE;
        
        for (int i = 0; i < powers.length; ++i) {
            product = product.multiply(BigInteger.valueOf(PRIMES[i]).pow(powers[i]));
            
        }

        return product;
    }
    
    private static long countDistinctSolutions(int... powers) {
        
        long count = 1;
        
        for (int p : powers) {
            count *= 2 * p + 1;
        }
        
        return (count + 1) / 2;
    }
    
}
