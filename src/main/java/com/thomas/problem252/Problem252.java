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
package com.thomas.problem252;

import java.util.Arrays;
import java.util.Comparator;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.BlumBlumShub;
import com.thomas.util.random.IntGenerator;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem252 implements Problem {

    class AngleComparator implements Comparator<int[]> {
        
        private final int[] p0;
        
        private AngleComparator(int[] p0) {

            this.p0 = p0;
        }

        @Override
        public int compare(int[] p1, int[] p2) {

            return compareAngle(this.p0, p2, p1);
        }
        
    }
    
    private static final Comparator<int[]> AXIS_COMPARATOR = new Comparator<int[]>() {

        @Override
        public int compare(int[] p1, int[] p2) {

            final int diff = p1[1] - p2[1];

            return diff == 0 ? p1[0] - p2[0] : diff;
        }
        
    };
    
    /**
     * {@link http://de.wikipedia.org/wiki/Graham_Scan}
     * 
     * 104924.0
     * 
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final int max = 500;
        final IntGenerator generator = new BlumBlumShub(2000);
        final int[][] t = new int[max][2];

        for (int i = 0; i < max; ++i) {
            t[i][0] = generator.next();
            t[i][1] = generator.next();
        }
        Arrays.sort(t, AXIS_COMPARATOR);
        
        long area = 0;
        
        for (int i = 0; i + 2 < max; ++i) {
            
            final int[][] rem = Arrays.copyOfRange(t, i, max);

            Arrays.sort(rem, 1, rem.length, new AngleComparator(rem[0]));
            final int[][] cache = new int[500][500];
            for (int j = 1; j + 1 < rem.length; ++j) {
                area = Math.max(area, rem[0][0] * rem[j][1] - rem[j][0] * rem[0][1] + area(rem, 0, j, cache));
            }
        }
        return area / 2.0;
    }

    private int area(int[][] rem, int i0, int i1, int[][] cache) {
        
        if (cache[i0][i1] == 0) {
            
            final int[] p1 = rem[i1];
            
            cache[i0][i1] = p1[0] * rem[0][1] - rem[0][0] * p1[1];
            
            int[] s0 = rem[i0];
            int[] s1 = p1;
            for (int i2 = i1 + 1; i2 < rem.length; ++i2) {
                
                final int[] p2 = rem[i2];
                final int c = (s1[0] - s0[0]) * (p2[1] - s0[1]) - (p2[0] - s0[0]) * (s1[1] - s0[1]);
                
                if (c >= 0) {
                    cache[i0][i1] = Math.max(cache[i0][i1], p1[0] * p2[1] - p2[0] * p1[1] + area(rem, i1, i2, cache));
                    s0 = p1;
                    s1 = p2;
                }
            }
        }
        
        return cache[i0][i1];
    }
    
    private int compareAngle(int[] p0, int[] p1, int[] p2) {
        
        return (p1[0] - p0[0]) * (p2[1] - p0[1]) - (p2[0] - p0[0]) * (p1[1] - p0[1]);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem252());
    }

}
