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
package com.thomas.problem303;

import java.util.ArrayList;
import static java.util.Collections.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 01.12.2010
 */
public class Problem303 implements Problem {

    private static final Comparator<long[]> PATH_COMP = new Comparator<long[]>() {
        
        @Override
        public int compare(long[] o1, long[] o2) {
            
            return Long.signum(o1[1] - o2[1]);
        }
        
    };
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        long sum = 0;
        
        final Set<Long> carryIndex = new HashSet<Long>();
        List<long[]> prev = new ArrayList<long[]>();
        List<long[]> next = new ArrayList<long[]>();
        
        loop: for (long n = 1; n <= 10000; ++n) {
            
            carryIndex.clear();
            prev.clear();
            next.clear();
            
            for (int i = 1; i < 10; ++i) {
                long j = i * n;
                if (j % 10 <= 2) {

                    final long carry = j / 10;
                    
                    if (carry <= 2) {
                        sum += i;
                        continue loop;
                    }
                    if (carryIndex.add(carry)) {
                        next.add(new long[] {carry, i});
                    }
                }
            }
            for (long m = 10; ; m *= 10) {
                prev.clear();
                prev.addAll(next);
                sort(prev, PATH_COMP);
                next.clear();
                for (int i = 0; i < 10; ++i) {
                    for (long[] path : prev) {
                        long j = i * n + path[0];
                        if (j % 10 <= 2) {
                            
                            final long carry = j / 10;
                            final long p = i * m + path[1];

                            if (carry <= 2) {
                                sum += p;
                                continue loop;
                            }
                            if (carryIndex.add(carry)) {
                                next.add(new long[] {carry, p});
                            }
                        }
                    }
                }
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem303());
    }

}
