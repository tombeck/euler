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
package com.thomas.incubator;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.03.2010
 */
class Problem136 implements Problem {

    /**
     * x^2 - y^2 - z^2 = n
     * n < 5 * 10^7
     * x > y > z > 0
     * 
     * x = y + r > y => r > 0
     * z = y - r > 0 => r < y   0 < r < y
     * 
     * x^2 - y^2 - z^2 = (y + r)^2 - y^2 - (y - r)^2 = 4ry - y^2 = (2r)^2 - (2r - y)^2 = n
     * 
     * =>  y^2 - 4ry + n = 0
     * =>  4y^2 - 16ry + 4n = 0
     * =>  4y^2 - 16ry + 4n + 16r^2 - 16r^2 = 0 
     * =>  (2y - 4r)^2 -16r^2 + 4n = 0
     * let a = 2y - 4r
     * => a^2 - 16r^2 + 4n = 0
     * => -a^2 + 16r^2 - 4n = 0
     * => 16r^2  - a^2 = 4n
     * => (4r + a)(4r - a) = 4n
     *   
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 16.03.2010
     */
    @Override
    public Object solve() {

        final int max = 500000; //50000000;
        
        Set<Integer> ns = new TreeSet<Integer>();
        
        for (int r = 1; 4*r - 1 < max; ++r) {
            for (int m = 2 * r - 1, n; m >= r && (n = 4 * r * r - m * m) < max; --m) {
                int y1 = 2 * r + m;
                
                ns.add(n);
//                System.out.println("n = " + n + ", r = " + r + ",y = " + y1);
            }
        }
        
        return ns.size();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 16.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem136());
    }

}
