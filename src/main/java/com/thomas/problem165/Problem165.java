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
package com.thomas.problem165;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.BlumBlumShub;
import com.thomas.util.random.IntGenerator;
import com.thomas.util.rational.IntRational;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.12.2009
 */
class Problem165 implements Problem {

    static class Point implements Comparable<Point> {
        
        final IntRational x;
        final IntRational y;

        public Point(IntRational x, IntRational y) {

            this.x = x;
            this.y = y;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(Point other) {

            final int diff = this.x.compareTo(other.x);
            
            return diff == 0 ? this.y.compareTo(other.y) : diff;
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 28.12.2009
     */
    @Override
    public Integer solve() {

        final IntGenerator gen = new BlumBlumShub(500);
        final int[][][] segs = new int[5000][2][2];
        
        for (int i = 0; i < 5000; ++i) {
            segs[i][0][0] = gen.next();
            segs[i][0][1] = gen.next();
            segs[i][1][0] = gen.next() - segs[i][0][0];
            segs[i][1][1] = gen.next() - segs[i][0][1];
        }

        final Set<Point> intersections = new TreeSet<Point>();

        for (int i = 0; i < segs.length; ++i) {

            for (int j = 0; j < i; ++j) {
                
                final int[] x = segs[i][1];
                final int[] y = segs[j][1];
                
                final int det_xy = x[0] * y[1] - x[1] * y[0];

                if (det_xy != 0) {
                    
                    final int[] u = segs[i][0];
                    final int[] v = segs[j][0];
                    
                    final int[] w = new int[] {v[0] - u[0], v[1] - u[1]};
                    
                    final int det_wy = w[0] * y[1] - w[1] * y[0];
                    final int det_wx = w[0] * x[1] - w[1] * x[0];
                    
                    if (det_xy > 0 ? (det_wy > 0 && det_wy < det_xy && det_wx > 0 && det_wx < det_xy) : (det_wy < 0 && det_wy > det_xy && det_wx < 0 && det_wx > det_xy)) {
                        intersections.add(new Point(new IntRational(u[0] * det_xy + det_wy * x[0], det_xy), new IntRational(u[1] * det_xy + det_wy * x[1], det_xy)));
                    }
                }
            }
        }
        
        return intersections.size();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 28.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem165());
    }

}
