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
package com.thomas.problem0xx.problem5x;

import static com.thomas.util.Digit.DIGITS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.PrimeSieve;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.11.2009
 */
public class Problem51 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 25.01.2010
     */
    @Override
    public Integer solve() {

        final PrimeSieve sieve = new PrimeSieve(1000000);
        
        for (int prime : sieve) {
            for (List<Integer> pattern : findPatterns(prime)) {
                
                final char[] c = Integer.toString(prime).toCharArray();
                int total = 0;
                final List<Integer> list = new ArrayList<Integer>();
                for (char digit : DIGITS) {
                    for (int i : pattern) {
                        c[(c.length - 1) - i] = digit;
                    }
                    ++total;
                    int n = Integer.parseInt(String.valueOf(c));
                    if (sieve.isPrime(n)) list.add(n);
                    if (total - list.size() > 2) break;
                }
                if (list.size() >= 8) {
                    if (String.valueOf(list.get(0)).length() == String.valueOf(prime).length()) {
                        return list.get(0);
                    }
                }
            }
        }
        
        return null;
    }
    
    private Set<List<Integer>> findPatterns(int prime) {
    
        final List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < 3; ++i) {
            ret.add(new ArrayList<Integer>());
        }
        for (int i = 1; (prime /= 10) > 0; ++i) {
            int n = prime % 10;
            if (n < 3) ret.get(n).add(i);
        }
        
        final Set<List<Integer>> patterns = new HashSet<List<Integer>>();
        
        for (int i = 0; i < 3; ++i) {
            patterns.addAll(makeSubPatterns(ret.get(i)));
        }
        
        return patterns;
    }
    
    private Set<List<Integer>> makeSubPatterns(List<Integer> pattern) {
        
        final Set<List<Integer>> patterns = new HashSet<List<Integer>>();
        
        switch(pattern.size()) {
        case 3: patterns.add(pattern);
        case 4:
        case 5:
        default:
            for (Integer i : pattern) {
                List<Integer> copy = new ArrayList<Integer>(pattern);
                copy.remove(i);
                patterns.addAll(makeSubPatterns(copy));
            }
        }
        
        return patterns;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem51());
    }

}
