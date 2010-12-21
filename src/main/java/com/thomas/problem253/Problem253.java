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
package com.thomas.problem253;

import static java.lang.Long.bitCount;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 20.12.2010
 */
public class Problem253 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double solve() {

        final double[] a = count(1L << 40, new HashMap<Long, double[]>());

        double num = 0;
        double den = 0;

        for (int i = 0; i < a.length; ++i) {
            num += i * a[i];
            den += a[i];
        }
        
        return (double)round((num / den + 1) * 1000000) / 1000000;
    }

    private double[] count(final long c, final Map<Long, double[]> cache) {

        double[] ret = cache.get(c);
        
        if (ret == null) {
            
            ret = new double[20];
            
            if (c == 3) ret[0] = 1;
            else {
                
                final int segments = bitCount(c) - 2;
                
                for (long i = 1, n = c; n > 1; ) {
                    
                    final long next = c | i;
                    
                    if (next != c) {
                        
                        final double[] b = count(normalize(next), cache);
                        
                        int m = 0;
                        
                        for ( ; m <= segments;  ++m) ret[segments] += b[m];
                        for ( ; m < ret.length; ++m) ret[m] += b[m];
                    }
                    n >>= 1;
                    i <<= 1;
                }
            }
            
            cache.put(c, ret);
        }
        
        return ret;
    }
    
    private long normalize(long n) {
        
        final List<Integer> l = new ArrayList<Integer>();
        
        int leading = 0;
        int count = 0;
        
        for (; n > 1 && (n & 1) == 0; n >>= 1) ++leading;
        for (long piece, prev = 0; n > 1; n >>= 1) {
            piece = (n & 1);
            if (piece == 0) ++count;
            else {
                if (prev == 0) l.add(count);
                count = 0;
            }
            prev = piece;
        }
        sort(l);
        
        long key = 1;
        
        if (count > 0) {
            if (leading > 0) {
                key <<= min(count, leading);
                for (int s : l) {
                    key <<= s + 1;
                    key |= 1;
                }
                key <<= max(count, leading);
            } else {
                key <<= count;
                for (int s : l) {
                    key <<= s + 1;
                    key |= 1;
                }
            }
        } else {
            key <<= leading;
            for (int s : l) {
                key <<= s + 1;
                key |= 1;
            }
        }

        return key;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem253());
    }

}
