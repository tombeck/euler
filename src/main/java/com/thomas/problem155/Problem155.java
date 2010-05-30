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
package com.thomas.problem155;

import static java.util.Collections.singleton;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 29.12.2009
 */
public class Problem155 implements Problem {

    /**
     * TODO Method documentation
     *
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 29.12.2009
     */
    @Override
    public Integer solve() {

        final Map<Integer, Set<int[]>> cache = new HashMap<Integer, Set<int[]>>();
        
        cache.put(1, singleton(new int[] {1, 1}));
        
        for (int n = 2; n <= 18; ++n) {
            
            final Set<int[]> capacitors = new TreeSet<int[]>(new Comparator<int[]>() {

                @Override
                public int compare(int[] self, int[] other) {

                    return self[0] * other[1] - other[0] * self[1];
                }
                
            });
            
            for (int i = 1; 2 * i <= n; ++i) {

                final Set<int[]> right = cache.get(n - i);
                
                for (int[] l : cache.get(i)) {
                    for (int[] r : right) {
                        capacitors.add(new int[] {l[0] * r[1] + r[0] * l[1], l[1] * r[1]});
                        capacitors.add(new int[] {l[0] * r[0], l[1] * r[0] + r[1] * l[0]});
                    }
                }
            }

            capacitors.addAll(cache.get(n - 1));
            
            cache.put(n, capacitors);
        }
        
        return cache.get(18).size();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 29.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem155());
    }

}
