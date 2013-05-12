/**
 * $Id$
 * 
 * Copyright (c) 2013 Thomas Beckmann
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

package com.thomas.problem4xx.problem42x;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 11.05.2013
 */
public class Problem425 implements Problem {

    static int digitsToInt(int[] digits, int len) {
        
        int n = 0;
        
        for (int i = len - 1; i >= 0; --i) {
            n = n * 10 + digits[i];
        }
        
        return n;
    }
    
    static int intToDigits(int[] digits, int n) {
        
        int len = 0;

        for ( ; n > 0; n /= 10) {
            digits[len++] = n % 10;
        }
        
        return len;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final int max = 10000000;

        final boolean[] sieve = new boolean[max+1];
        final Set<Integer> primes = new HashSet<Integer>(max/2);
        long sum = 0;
        
        int i = 1;
        
        for (int q = 1; (q += (i++ << 1) + 1) <= max; ) {
            if (!sieve[i]) {
                primes.add(i);
                sum += i;
                for (int j = q; j <= max; j += i) sieve[j] = true;
            }
        }
        for ( ; i <= max; ++i) {
            if (!sieve[i]) {
                primes.add(i);
                sum += i;
            }
        }
        
        final Queue<int[]> queue = new PriorityQueue<int[]>(1, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) { return Integer.compare(o1[1], o2[1]); }
            
        });
        
        queue.add(new int[] {2, 2});
        primes.remove(2);
        sum -= 2;
        
        for (int[] current; (current = queue.poll()) != null; ) {
            for (int connectedPrime : connectedPrimes(current[0], primes)) {
                
                final int[] node = {connectedPrime, max(connectedPrime, current[1])};
                
                if (node[1] <= node[0]) sum -= connectedPrime;
                queue.add(node);
                primes.remove(connectedPrime);
            }
        }

        return sum;
    }

    Collection<Integer> connectedPrimes(int n, Set<Integer> primes) {
        
        final int[] digits = new int[8];
        final int len = intToDigits(digits, n) + 1;
        final Collection<Integer> connectedPrimes = new ArrayList<Integer>(9 * len);

        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < 9; ++j) {
                digits[i] = (digits[i] + 1) % 10;
                if (i+2 == len && digits[i] == 0) continue;
                
                final Integer connected = digitsToInt(digits, len);
                
                if (primes.contains(connected)) connectedPrimes.add(connected);
            }
            digits[i] = (digits[i] + 1) % 10;
        }

        return connectedPrimes;
    }
    
   /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem425());
    }

}
