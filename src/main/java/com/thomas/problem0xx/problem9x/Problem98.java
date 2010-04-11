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
package com.thomas.problem0xx.problem9x;

import static com.thomas.util.IOUtils.closeQuietly;
import static com.thomas.util.PrimeUtils.primes;
import static java.lang.Character.getNumericValue;
import static java.lang.Long.signum;
import static java.lang.Math.max;
import static java.util.Arrays.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 04.01.2010
 */
class Problem98 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @throws IOException
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 04.01.2010
     */
    @Override
    public Long solve() throws IOException {

        final List<Integer> primes = primes(102); // 26 primes
        final int A = getNumericValue('A');
        final Comparator<String> comp = new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {

                final int diff = o2.length() - o1.length();
                
                return diff == 0 ? signum(anagramHash(o1) - anagramHash(o2)) : diff;
            }
            
            long anagramHash(String s) {
                
                long hash = 1;
                
                for (char c : s.toCharArray()) {
                    hash *= primes.get(getNumericValue(c) - A);
                }
                
                return hash;
            }
            
        };
        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("words.txt")));

        try {
            
            final String[] names = reader.readLine().replaceAll("\"", "").split(",");
            final Map<List<Integer>, List<Long>> sqPatterns = squareNumberPatterns();

            long max = 0;
            
            sort(names, comp);
            for (int i = 1; i < names.length; ++i) {
                if (comp.compare(names[i - 1], names[i]) == 0) {
                    for (long p1 : sqPatterns.get(patternOf(names[i - 1]))) {
                        
                        final List<Long> l = sqPatterns.get(patternOf(names[i]));
                        final long p2 = reorder(p1, names[i - 1], names[i]);
                        
                        if (l.contains(p2)) max = max(max, max(p1, p2));
                    }
                }
            }

            return max;
            
        } finally {
            closeQuietly(reader);
        }
    }

    private long reorder(long p, String from, String to) {
    
        final Map<Character, Integer> map = new HashMap<Character, Integer>();
        final char[] sc = from.toCharArray();
        
        long ret = 0;
        
        for (int i = sc.length; i-- > 0; p /= 10) map.put(sc[i], (int)(p % 10));
        for (char c : to.toCharArray()) ret = ret * 10 + map.get(c);
        
        return ret;
    }
    
    private Map<List<Integer>, List<Long>> squareNumberPatterns() {

        final Map<List<Integer>, List<Long>> patterns = new HashMap<List<Integer>, List<Long>>();
        
        for (long i = 1, q = 0; (q = i * i) < 1000000000; ++i) {
            
            final List<Integer> pattern = patternOf(Long.toString(q));
            
            List<Long> squares = patterns.get(pattern);
            
            if (squares == null) patterns.put(pattern, squares = new ArrayList<Long>());
            squares.add(q);
        }

        return patterns;
    }

    private List<Integer> patternOf(String s) {
    
        final List<Integer> pattern = new ArrayList<Integer>(s.length());
        final List<Character> arr = new ArrayList<Character>(s.length());
        
        for (char c : s.toCharArray()) {
            pattern.add(arr.indexOf(c));
            arr.add(c);
        }
        
        return pattern;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 04.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem98());
    }

}
