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

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.02.2010
 */
public class Problem161 implements Problem {

    private static final int[][] FOLLOWERS = {
            /*         0 A1 A2 B1 B2 C1 C2 D1 D2 E1 E2 E3 F1*/
            /* 0  */ { 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
            /* A1 */ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            /* A2 */ { 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1},
            /* B1 */ { 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            /* B2 */ { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            /* C1 */ { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1},
            /* C2 */ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            /* D1 */ { 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            /* D2 */ { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            /* E1 */ { 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            /* E2 */ { 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
            /* E3 */ { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            /* F1 */ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    
    private static final int[] SIZE = {
        /*    0 A1 A2 B1 B2 C1 C2 D1 D2 E1 E2 E3 F1*/
        /**/  0, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 3
    };
    
    private static final int[] TOP = {
        /*    0 A1 A2 B1 B2 C1 C2 D1 D2 E1 E2 E3 F1*/
        /**/  1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1
    };
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 16.02.2010
     */
    @Override
    public Object solve() {

        System.out.println(count(9, false));
        
        return null;
    }

    private int count(int size, boolean zero) {
    
        if (size == 0) return zero ? 1 : 0;
        
        int count = 0;
        
        for (int i = 0; i <= 2; ++i) {
            count += count(size - 1, zero || i == 0);
        }
        
        return count;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 16.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem161());
    }

}
