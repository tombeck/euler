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
class Problem165 implements Problem {

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

        final Comparator<long[]> comp = new Comparator<long[]>() {

            @Override
            public int compare(long[] o1, long[] o2) {

                final long diff = o1[0] * o2[2] - o2[0] * o1[2];

                return Long.signum(diff == 0 ? o1[1] * o2[2] - o2[1] * o1[2] : diff);
            }
            
        };
        
        final Set<long[]> intersections = new TreeSet<long[]>(comp);

        for (int i = segs.length; i-- > 0; ) {
            for (int j = segs.length; j-- > i; ) {
                final long[] u = segs[i][0];
                final long[] x = segs[i][1];
                final long[] v = segs[j][0];
                final long[] y = segs[j][1];
                
                final long[] w = subtract(v, u);
                
                final long det_wy = det(w, y);
                final long det_wx = det(w, x);
                final long det_xy = det(x, y);
                
                if (det_xy > 0) {
                    if (det_wy > 0 && det_wy < det_xy && det_wx > 0 && det_wx < det_xy) {
                        long[] intersection = {(u[0] * det_xy + det_wy * x[0]) , (u[1] * det_xy + det_wy * x[1]) , det_xy};
                        intersections.add(intersection);
                    }
                } else if (det_xy < 0) {
                    if (det_wy < 0 && det_wy > det_xy && det_wx < 0 && det_wx > det_xy) {
                        long[] intersection = {(u[0] * det_xy + det_wy * x[0]) , (u[1] * det_xy + det_wy * x[1]) , det_xy};
                        intersections.add(intersection);
                    }
                }
            }
            System.out.println(i + ": " + intersections.size());
            Thread.sleep(1);
        }
        
        return intersections.size();
    }

    private long det(long[] u, long[] v) {
    
        return u[0] * v[1] - u[1] * v[0];
    }
    
    private long[] subtract(long[] u, long[] v) {
    
        return new long[] {u[0] - v[0], u[1] - v[1]};
    }
    
    /**
     * TODO Method documentation
     * 
     * 1419181
     * 2178108
     * 2178047
     * 2781503
     * 2781478
     * 2868959
     * 2868972
     * 2868921
     * 
     * 2868868
     * 
     * @param args
     * @author Thomas
     * @since 28.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem165());
    }

}
