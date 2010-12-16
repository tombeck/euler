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

import static java.lang.Math.floor;
import static java.lang.Math.log;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.NumberUtils;
import com.thomas.util.PrimeUtils;

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

//        final int max = 10000;
//        final List<Integer> primes = primes((int)sqrt(max));
//        final List<Integer> pair = new ArrayList<Integer>();
//        final List<Integer> practical = new ArrayList<Integer>();
//        
//        int c1 = 0;
//        int c2 = 0;
//        
//        for (int i = 5; i < max; i += 6) {
//            if (!isPrime(i, primes)) c1 = 0;
//            else if (++c1 >= 4) pair.add(i - 9);
//            if (!isPrime(i + 2, primes)) c2 = 0;
//            else if (++c2 >= 4) pair.add((i + 2) - 9);
//        }
//        c1 = 0;
//        c2 = 0;
//        for (int i = 2; i < max; i += 4) {
//            if (!NumberUtils.isPractical(i)) c1 = 0;
//            else if (++c1 >= 5) practical.add((i) - 8);
//            if (!NumberUtils.isPractical(i + 2)) c2 = 0;
//            else if (++c2 >= 5) practical.add((i + 2) - 8);
//        }
//        System.out.println(pair);(x + y + z
//        System.out.println(practical);
//        practical.retainAll(pair);
//        System.out.println(practical);
        
        final long max = 110000;
        final long[] primes = NumberUtils.toLongArray(PrimeUtils.primes((int)(max / 2)));
        
        final Comparator<long[]> comp = new Comparator<long[]>() {

            @Override
            public int compare(long[] o1, long[] o2) {

                final long diff = o2[1] - o1[1];
                
                return Long.signum(diff == 0 ? o1[0] - o2[0] : diff);
            }
            
        };
        final Set<long[]> practical = new TreeSet<long[]>(comp);
        
        for (int p = 1, b = (int)floor(log(max) / log(2)); p <= b; ++p) {
            
            final long product = NumberUtils.pow(2, p);
            final long sigma = 2 * product - 1;
            
            practical.add(new long[] {product, sigma});
//            practical(primes, 1, sigma, product, max, practical);
        }
        for (int i = 1; i < primes.length; ++i) {
            
            final Set<long[]> tmp = new TreeSet<long[]>(comp);
            
            for (long[] a : practical) {
                
                final long product = a[0];
                final long sigma = a[1];
                
                if (primes[i] > 1 + sigma) break;
                
                long power = 1;
                
                for (int p = 0, b = (int)(log(max / product) / log(primes[i])); p <= b; ++p) {
                    
                    final long next_product = product * power;
                    final long next_sigma = (sigma * ((power *= primes[i]) - 1)) / (primes[i] - 1);
                    
                    tmp.add(new long[] {next_product, next_sigma});
                }
            }
            
            practical.addAll(tmp);
        }
        System.out.println(practical.size());

        final Set<Long> tmp = new HashSet<Long>();
        
        for (long[] a : practical) tmp.add(a[0]);
        
        for (long n : tmp) {
            if (tmp.contains(n - 8) && tmp.contains(n - 4) && tmp.contains(n + 4) && tmp.contains(n + 8)) {
                if (PrimeUtils.isPrime(n - 9) && PrimeUtils.isPrime(n - 3) && PrimeUtils.isPrime(n + 3) && PrimeUtils.isPrime(n + 9)) {
                    System.out.println(n);
                }
            }
        }
        
        return null;
    }

    private void practical(long[] primes, int i, long sigma, long product, final double max, Set<Long> practical) {
        
        if (i == primes.length || primes[i] > 1 + sigma) return;
        
        long power = 1;
        
        for (int p = 0, b = (int)(log(max / product) / log(primes[i])); p <= b; ++p) {
            long next_product = product * power;
            long next_sigma = (sigma * ((power *= primes[i]) - 1)) / (primes[i] - 1);
            //System.out.println(next_product);
            practical.add(next_product);
            practical(primes, i + 1, next_sigma, next_product, max, practical);
        }
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
