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
package com.thomas.problem222;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 24.12.2009
 */
public class Problem222 implements Problem {

    /**
     * shortest length is achieved with
     * {50, 48, 46, 44, 42, 40, 38, 36, 34, 32, 30, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49} 
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 24.12.2009
     */
    @Override
    public Object solve() {

        double length = dist(31, 30) + dist(30, 32);
        
        for (int i = 33; i < 50; i += 2) {
            length += dist(i, i - 2) + dist(i - 1, i + 1);
        }
        
        length += 50 + 49;
        
        return round(length * 1000);
    }

    private double dist(int r1, int r2) {
        
        return sqrt(200 * (r1 + r2 - 50));
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 24.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem222());
    }

}
