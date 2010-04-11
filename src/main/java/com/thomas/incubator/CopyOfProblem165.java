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
package com.thomas.incubator;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.BlumBlumShub;
import com.thomas.util.random.IntGenerator;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.12.2009
 */
class CopyOfProblem165 implements Problem {

    static final class SimplePoint implements Comparable<SimplePoint> {
        
        final int xnum;
        final int ynum;
        final int den;
        
        public SimplePoint(long xnum, long ynum, long den) {

            if (xnum < Integer.MIN_VALUE || xnum > Integer.MAX_VALUE) throw new IllegalArgumentException();
            if (ynum < Integer.MIN_VALUE || ynum > Integer.MAX_VALUE) throw new IllegalArgumentException();
            if (den < Integer.MIN_VALUE || den > Integer.MAX_VALUE) throw new IllegalArgumentException();
            
            this.xnum = (int)xnum;
            this.ynum = (int)ynum;
            this.den = (int)den;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof SimplePoint)) return false;

            final SimplePoint other = (SimplePoint)obj;
            
            return this.xnum * other.den == other.xnum * this.den
                && this.ynum * other.den == other.ynum * this.den;
        }

        @Override
        public int compareTo(SimplePoint other) {

            final long diff = (long)this.xnum * other.den - (long)other.xnum * this.den;

            return Long.signum(diff == 0 ? (long)this.ynum * other.den - (long)other.ynum * this.den : diff);
        }

    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws InterruptedException 
     * @since 28.12.2009
     */
    @Override
    public Object solve() throws InterruptedException {

        final IntGenerator gen = new BlumBlumShub(500);
        final long[][][] segs = new long[5000][2][2];
        
        for (int i = 0; i < 5000; ++i) {
            segs[i][0][0] = gen.next();
            segs[i][0][1] = gen.next();
            segs[i][1][0] = gen.next() - segs[i][0][0];
            segs[i][1][1] = gen.next() - segs[i][0][1];
        }

//        final long[][][] segs = {
//                {{0, 3}, {5 - 0, 5 - 3}},
//                {{1, 2}, {2 - 1, 5 - 2}},
//                {{2, 0}, {1 - 2, 2 - 0}},
//                {{2, 0}, {5 - 2, 3 - 0}},
//                {{3, 3}, {5 - 3, 1 - 3}},
//                {{0, 2}, {4 - 0, 2 - 2}},
//                {{0, 2}, {5 - 0, 4 - 2}},
//                {{4, 1}, {4 - 4, 3 - 1}}
//        };
//        final int[][][] segs = {
//                {{27, 44}, {12 - 27, 32 - 44}},
//                {{46, 53}, {17 - 46, 62 - 53}},
//                {{46, 70}, {22 - 46, 40 - 70}}
//        };
        final Set<long[]> doppelt = new TreeSet<long[]>(new Comparator<long[]>() {

            @Override
            public int compare(long[] o1, long[] o2) {

                assertRange(o1[0], o2[2]);
                assertRange(o2[0], o1[2]);
                assertRange(o1[1], o2[2]);
                assertRange(o2[1], o1[2]);

                final long diff = o1[0] * o2[2] - o2[0] * o1[2];

                return Long.signum(diff == 0 ? o1[1] * o2[2] - o2[1] * o1[2] : diff);
            }
            
        });
        long count = 0;
        for (int i = 0; i < segs.length; ++i) {

            final Set<long[]> intersections = new TreeSet<long[]>(new Comparator<long[]>() {

                @Override
                public int compare(long[] o1, long[] o2) {

                    assertRange(o1[0], o2[2]);
                    assertRange(o2[0], o1[2]);
                    assertRange(o1[1], o2[2]);
                    assertRange(o2[1], o1[2]);

                    final long diff = o1[0] * o2[2] - o2[0] * o1[2];

                    return Long.signum(diff == 0 ? o1[1] * o2[2] - o2[1] * o1[2] : diff);
                }
                
            });
            
            final long[] u = segs[i][0];
            final long[] x = segs[i][1];
            
            System.out.println(i + ": " + count);
            
            for (int j = i + 1; j < segs.length; ++j) {
                
                long[] v = segs[j][0];
                long[] y = segs[j][1];
                
                assertRange(x[0], y[1]);
                assertRange(x[1], y[0]);
                long c = x[0] * y[1] - x[1] * y[0];

                if (c == 0) break;
                
                long[] w = {v[0] - u[0], v[1] - u[1]};
                
                assertRange(w[0], y[1]);
                assertRange(w[1], y[0]);
                assertRange(w[0], x[1]);
                assertRange(w[1], x[0]);
                
                long a = w[0] * y[1] - w[1] * y[0];
                long b = w[0] * x[1] - w[1] * x[0];
                
//                double r = (double)a / c;
//                double s = (double)b / c;

//                if (0 <= r && r <= 1 && 0 <= s && s <= 1) ++count;
                if (c < 0) {
                    if (a < 0 && a > c && b < 0 && b > c) {
//                        ++count;
                        assertRange(u[0], c);
                        assertRange(a, x[0]);
                        assertRange(u[1], c);
                        assertRange(a, x[1]);
                        long[] intersection = {(u[0] * c + a * x[0]) , (u[1] * c + a * x[1]) , c};
                        if (!doppelt.contains(intersection)) {
                            if (!intersections.add(intersection)) {
                                doppelt.add(intersection);
                            }
                        }
                    }
                } else {
                    if (0 < a && a < c && 0 < b && b < c) {
//                        ++count;
                        assertRange(u[0], c);
                        assertRange(a, x[0]);
                        assertRange(u[1], c);
                        assertRange(a, x[1]);
                        long[] intersection = {(u[0] * c + a * x[0]) , (u[1] * c + a * x[1]) , c};
                        if (!doppelt.contains(intersection)) {
                            if (!intersections.add(intersection)) {
                                doppelt.add(intersection);
                            }
                        }
                    }
                }
            }
            count += intersections.size();
            Thread.sleep(1);
        }

        return count;
    }

    static void assertRange(long a, long b) {
    
        if (b != 0) {
            if (Math.abs(a) > Long.MAX_VALUE / Math.abs(b) || Math.abs(a) < Long.MIN_VALUE / Math.abs(b)) {
                throw new ArithmeticException(a + " * " + b);
            }
        }
    }
    
    /**
     * TODO Method documentation
     * 
     * 1419181
     * 2178108
     * 2178047
     * 2781503
     * 2781478
     * 
     * 2868868
     * 
     * @param args
     * @author Thomas
     * @since 28.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new CopyOfProblem165());
    }

}
