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
package com.thomas.problem310;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 15.11.2010
 */
public class Problem310 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int max = 100000;
        final int[] cache = new int[max + 1];
        
        final Map<Integer, Long> values = new TreeMap<Integer, Long>();

        for (int x = 0; x <= max; ++x) {
            
            final Long c = values.get(cache[x] = nimValue(x, cache));
            
            if (c == null) values.put(cache[x], 1L);
            else values.put(cache[x], c + 1);
        }

        long count = 0;
        
        for (Entry<Integer, Long> x : values.entrySet()) {
            for (Entry<Integer, Long> y : values.entrySet()) {
                for (Entry<Integer, Long> z : values.entrySet()) {
                    if ((x.getKey() ^ y.getKey() ^ z.getKey()) == 0) {
                        if (x.getKey() <= y.getKey() && y.getKey() <= z.getKey()) {
                            if (x.getKey() == y.getKey()) {
                                if (y.getKey() == z.getKey()) {
                                    count += (x.getValue() * (1 + x.getValue()) * (2 + x.getValue())) / 6;
                                } else {
                                    count += ((y.getValue() * (y.getValue() + 1)) / 2) * z.getValue();
                                }
                            } else {
                                if (y.getKey() == z.getKey()) {
                                    count += ((y.getValue() * (y.getValue() + 1)) / 2) * x.getValue();
                                } else {
                                    count += x.getValue() * y.getValue() * z.getValue();
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return count;
    }

    private int nimValue(int x, int[] cache) {

        final TreeSet<Integer> set = new TreeSet<Integer>();
        
        for (int n = 1, n2 = 1; n2 <= x; n2 += 2 * n++ + 1) {
            set.add(cache[x - n2]); 
        }
        
        int i = 0;
        for (int n : set) {
            if (i != n) break;
            ++i;
        }

        return i;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem310());
    }

}
