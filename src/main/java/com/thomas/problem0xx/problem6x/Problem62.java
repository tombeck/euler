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
package com.thomas.problem0xx.problem6x;

import static com.thomas.util.Permutation.hash;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem62 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final Comparator<long[]> hashCompare = new Comparator<long[]>() {
            
            @Override
            public int compare(long[] o1, long[] o2) {
                
                return Long.signum(o1[1] - o2[1]);
            }
            
        };
        
        final List<long[]> cubes = new ArrayList<long[]>();
        
        for (long n = 10, max = 10000; ; max *= 10) {
            
            for (long n3 ; (n3 = n * n * n) < max; ++n) {
                cubes.add(new long[] {n3, hash(n3)});
            }
            sort(cubes, hashCompare);
            
            long[] first = {0, 0};
            int count = 0;
            
            for (long[] cube : cubes) {
                if (cube[1] != first[1]) {
                    count = 1;
                    first = cube;
                } else if (++count == 5) {
                    
                    return first[0];
                }
            }
            cubes.clear();
        }
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {

        Euler.run(new Problem62());
    }

}
