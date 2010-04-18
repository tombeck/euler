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
package com.thomas.problem1xx.problem10x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.11.2009
 */
public class Problem100 implements Problem {

    /**
     * total t
     * blue  b
     * 
     *    b/t * (b-1)/(t-1) = 1/2
     * => 2(b^2 - b) = t^2 - t
     * => 2(b^2 - b + 1/4 - 1/4) = t^2 - t + 1/4 - 1/4
     * => 2((b - 1/2)^2 - 1/4) = (t - 1/2)^2 - 1/4  (2. binomische Gleichung)
     * => 2(b - 1/2)^2 = (t - 1/2)^2 + 1/4
     * => 4 * (2(b - 1/2)^2) = 4 * ((t - 1/2)^2 + 1/4)
     * => 2(2b - 1)^2 = (2t - 1)^2 + 1
     * => (2t - 1)^2 - 2(2b - 1)^2 = -1
     * => x^2 - 2y^2 = -1   mit x = 2t - 1, y = 2b - 1 bzw. t = (x+1)/2, b = (y+1)/2 (1)
     * 
     * x^2 - 2y^2 = -1 ist die negative Pellsche Gleichung x^2 - Dy^2 = -1 mit D = 2
     * die minimale Lösung ist x = 1, y = 1 und alle weiteren Lösungen erhät man mit
     * 
     * x(n+1)   | x(0) Dy(0) |   | x(n) |
     *        = |            | * |      |
     * y(n+1)   | y(0)  x(0) |   | y(n) |
     * 
     * x(n+1)   | 1  2 |   | x(n) |
     *        = |      | * |      |
     * y(n+1)   | 1  1 |   | y(n) |
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 22.03.2010
     */
    @Override
    public Long solve() {

        long[] x = {2 * (85 + 35) - 1, 2 * 85 - 1};
        
        while(x[0] < 2000000000000L) {
            x = new long[] {3 * x[0] + 4 * x[1], 2 * x[0] + 3 * x[1]};
        }
        
        return (x[1] + 1) / 2;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 22.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem100());
    }

}
