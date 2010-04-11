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
     * @throws InterruptedException
     * @since 31.01.2010
     */
    @Override
    public Object solve() throws InterruptedException {

        List<Long> primes = new ArrayList<Long>();
        
        for (int p2 = 0; p2 <= 10; ++p2) {
            for (int p3 = 0; p3 <= 5; ++p3) {
                for (int p5 = 0; p5 <= 2; ++p5) {
                    for (int p7 = 0; p7 <= 1; ++p7) {
                        for (int p11 = 0; p11 <= 1; ++p11) {
                            for (int p13 = 0; p13 <= 1; ++p13) {
                                
                                final long prime = pow(2L, p2) * pow(3L, p3) * pow(5L, p5) * pow(7L, p7) * pow(11L, p11) * pow(13L, p13) + 1;
                                
                                if (isPrime(prime)) {
                                    primes.add(prime);
                                    if (prime ==  2L) for (int i = 1; i <= 10; ++i) primes.add(2L * pow(2L, i));
                                    else if (prime ==  3L) for (int i = 1; i <= 5; ++i) primes.add(3L * pow(3L, i));
                                    else if (prime ==  5L) for (int i = 1; i <= 2; ++i) primes.add(5L * pow(5L, i));
                                    else if (prime ==  7) primes.add(49L);
                                    else if (prime == 11) primes.add(121L);
                                    else if (prime == 13) primes.add(169L);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        Collections.sort(primes, new Comparator<Long>() {

            @Override
            public int compare(Long o1, Long o2) {

                return Long.signum(o2 - o1);
            }
            
        });
        
//        for (long[] a : primes) {
//            System.out.println(Arrays.toString(a));
//        }
        final List<Long> numbers = new ArrayList<Long>();
        
        findNumbers(primes, 0, 6227020800L, 1L, numbers, 1);
        Collections.sort(numbers);
        
        return numbers.get(150000 - 1);
    }

    private void findNumbers(List<Long> factors, int first, long rem, long number, List<Long> numbers, int iteration) throws InterruptedException {
        
        //System.out.println(Prime.getPrimeFactors(Numbers.totient(cur[0])));
        
        if (rem == 1L) {
            numbers.add(number);
            Thread.sleep(1);
        }
        
        int i = first;
        
//        while(i < factors.size() && factors.get(i)[1] + dist > MAX) { ++i; }
        int j = factors.size();
//        long max = getMax(dist);
//        while(j < factors.size() && factors.get(j)[1] >= max) { ++j; }
        
        for ( ; i < j; ++i) {
            
            if (iteration < 2) System.out.println(iteration + ": " + i + "/" + factors.size());

            final long factor = factors.get(i);
            
            if (rem % (factor - 1) == 0) {
                findNumbers(factors, i + 1, rem / (factor - 1), number * factor, numbers, iteration + 1);
            }
        }
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
