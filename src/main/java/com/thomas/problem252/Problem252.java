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

import static java.lang.Math.max;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.sort;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.geometry.AngleComparator;
import com.thomas.util.geometry.OrdinateComparator;
import com.thomas.util.geometry.Point;
import com.thomas.util.random.BlumBlumShub;
import com.thomas.util.random.IntGenerator;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem252 implements Problem {

    /**
     * {@link http://de.wikipedia.org/wiki/Graham_Scan}
     * 
     * 104924.0
     * 
     * {@inheritDoc}
     */
    @Override
    public Double solve() {

        final int max = 500;
        final Point[] points = new Point[max];
        final IntGenerator generator = new BlumBlumShub() { 

            { next(); } // consume initial value
            
            @Override
            public int next() { return super.next() % 2000 - 1000; }
            
        };

        for (int i = 0; i < max; ++i) points[i] = new Point(generator);
        sort(points, new OrdinateComparator());
        
        int area = 0;
        
        for (int i = 0; i < max - 2; ++i) {
            
            final int[][] cache = new int[max - (i + 1)][max - i];
            final Point[] rem = copyOfRange(points, i, max);

            sort(rem, 1, rem.length, new AngleComparator(rem[0]) {
                
                @Override
                public int compare(Point p1, Point p2) { return super.compare(p2, p1); }
                
            });
            for (int j = 1; j < rem.length - 1; ++j) {
                area = max(area, rem[0].x * rem[j].y - rem[j].x * rem[0].y + area(rem, 0, j, cache));
            }
        }
        
        return area / 2.0;
    }

    private int area(Point[] points, int i0, int i1, int[][] cache) {
        
        if (cache[i0][i1] == 0) {
            
            final Point p1 = points[i1];
            final AngleComparator comp = new AngleComparator(points[i0]);
            
            cache[i0][i1] = p1.x * points[0].y - points[0].x * p1.y;

            Point s1 = p1;
            for (int i2 = i1 + 1; i2 < points.length; ++i2) {
                if (comp.compare(s1, points[i2]) >= 0) {
                    cache[i0][i1] = max(cache[i0][i1], area(points, i1, i2, cache) + p1.x * points[i2].y - points[i2].x * p1.y);
                    comp.setOrigin(p1);
                    s1 = points[i2];
                }
            }
        }
        
        return cache[i0][i1];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem252());
    }

}
