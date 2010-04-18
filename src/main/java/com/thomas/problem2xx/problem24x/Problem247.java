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
package com.thomas.problem2xx.problem24x;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 12.03.2010
 */
public class Problem247 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 12.03.2010
     */
    @Override
    public Integer solve() {

        return 1 + count(smallest(1, 0, 0, 0), 1, 0);
    }

    private double length(double l, double b) {
        
        final double diff = l - b;
        
        return (sqrt(4 + diff * diff) - (l + b)) / 2;
    }
    
    private int count(double min, double l, double b) {
        
        int count = 0;
        
        for (double x = length(l, b); x > min; x = length(l += x, b)) {
            count += 1 + count(min, l, b + x);
        }
        
        return count;
    }
    
    private double smallest(double l, double b, int il, int ib) {
    
        final double x = length(l, b);
        
        if (il == 3 && ib == 3) return x;
        
        double min = 1;
        
        if (il < 3) min = min(min, smallest(l + x, b, il + 1, ib)); 
        if (ib < 3) min = min(min, smallest(l, b + x, il, ib + 1));
        
        return min;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 12.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem247());
    }

}
