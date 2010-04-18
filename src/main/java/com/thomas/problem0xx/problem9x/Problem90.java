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
package com.thomas.problem0xx.problem9x;

import java.util.ArrayList;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 20.12.2009
 */
public class Problem90 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 20.12.2009
     */
    @Override
    public Integer solve() {

        final List<Integer> cubes = new ArrayList<Integer>();
        
        int sum = 0;
        
        for (int d1 = 1; d1 < 0x020; d1 <<= 1) {
            for (int d2 = d1 << 1; d2 < 0x040; d2 <<= 1) {
                for (int d3 = d2 << 1; d3 < 0x080; d3 <<= 1) {
                    for (int d4 = d3 << 1; d4 < 0x100; d4 <<= 1) {
                        for (int d5 = d4 << 1; d5 < 0x200; d5 <<= 1) {
                            for (int d6 = d5 << 1; d6 < 0x400; d6 <<= 1) {
                                
                                final int first = d1 | d2 | d3 | d4 | d5 | d6;
                                
                                if (valid(first)) {
                                    for (int second : cubes) {
                                        if (valid(first, second)) ++sum;
                                    }
                                    cubes.add(first);
                                }
                            }
                        }
                    }
                }
            }
        }

        return sum;
    }

    private boolean valid(int cube) {
        
        return     (0x003 & cube) != 0
                && (0x011 & cube) != 0
                && (0x241 & cube) != 0
                && (0x242 & cube) != 0
                && (0x024 & cube) != 0
                && (0x248 & cube) != 0
                && (0x250 & cube) != 0
                && (0x102 & cube) != 0;
    }

    private boolean valid(int first, int second) {
        
        return     (((0x001 & first ) != 0 && (0x002 & second) != 0)
                 || ((0x001 & second) != 0 && (0x002 & first ) != 0))
                && (((0x001 & first ) != 0 && (0x010 & second) != 0)
                 || ((0x001 & second) != 0 && (0x010 & first ) != 0))
                && (((0x001 & first ) != 0 && (0x240 & second) != 0)
                 || ((0x001 & second) != 0 && (0x240 & first ) != 0))
                && (((0x002 & first ) != 0 && (0x240 & second) != 0)
                 || ((0x002 & second) != 0 && (0x240 & first ) != 0))
                && (((0x004 & first ) != 0 && (0x020 & second) != 0)
                 || ((0x004 & second) != 0 && (0x020 & first ) != 0))
                && (((0x008 & first ) != 0 && (0x240 & second) != 0)
                 || ((0x008 & second) != 0 && (0x240 & first ) != 0))
                && (((0x010 & first ) != 0 && (0x240 & second) != 0)
                 || ((0x010 & second) != 0 && (0x240 & first ) != 0))
                && (((0x100 & first ) != 0 && (0x002 & second) != 0)
                 || ((0x100 & second) != 0 && (0x002 & first ) != 0));
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 20.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem90());
    }

}
