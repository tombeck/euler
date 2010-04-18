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
package com.thomas.problem62;

import static com.thomas.util.Digit.PRIME;
import static java.lang.Math.ceil;
import static java.lang.Math.exp;
import static java.lang.Math.log;
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

    private static final Comparator<Long> COMP = new Comparator<Long>() {

        @Override
        public int compare(Long o1, Long o2) {

            return Long.signum(getHash(o1) - getHash(o2));
        }
        
        private long getHash(long n) {
            
            long hash = 1;

            for(; n != 0; n /= 10) hash *= PRIME[(int)(n % 10)];

            return hash;
        }
        
    };
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() throws Exception {

        final List<Long> cubes = new ArrayList<Long>();

        for (int len = 1, low = 1; ; ++len) {
            int high = crt(len);
            cubes.clear();
            for (long n = low; n < high; ++n) {
                cubes.add(n * n * n);
            }
            sort(cubes, COMP);
            
            long first = 0;
            int count = 0;
            for (Long cube : cubes) {
                if (COMP.compare(cube, first) == 0) {
                    if (++count == 5) return first;
                } else {
                    count = 1;
                    first = cube;
                }
            }
            low = high;
        }
    }

    private int crt(int len) {
        
        int ret = (int)ceil(exp((len * log(10)) / 3));
        
        return (len % 3 == 0) ? ret - 1 : ret;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem62());
    }

}
