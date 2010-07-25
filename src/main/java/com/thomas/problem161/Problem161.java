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
package com.thomas.problem161;

import static java.lang.Math.pow;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * ***  * *  ***  ***  ***  ***    *  *    * *  ***
 * *    * *    *  * *  *      *    *  *    * *
 * ***  ***  ***  * *  *      *  ***  ***  * *  ***
 * 
 * @author Thomas
 * @since 16.02.2010
 */
public class Problem161 implements Problem {

    private static final int[][] TOP = {
        /*       0  a  b  c  d  e  f  g  h  i  j */
        /* 0 */ {1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1},
        /* 1 */ {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
        /* 2 */ {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    
    private static final int[][] LEFT = {
        /*       0  a  b  c  d  e  f  g  h  i  j */
        /* 0 */ {0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0},
        /* a */ {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
        /* b */ {1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0},
        /* c */ {1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0},
        /* d */ {1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0},
        /* e */ {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
        /* f */ {1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0},
        /* g */ {1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0},
        /* h */ {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
        /* i */ {1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0},
        /* j */ {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
    };
    
    private static final int[] MASK = {0, 0, 0, 0, 1, 2, 2, 0, 0, 2, 0};
    private static final int[] LAST = {1, 2, 3, 7, 8, 10};
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 16.02.2010
     */
    @Override
    public Long solve() {

        final int width = 9;
        final int hight = 12;
        final long[][] cache = new long[hight][(int)pow(3, width)];
        final int[][] grid = new int[hight + 1][width];

        return countRow(width, grid, hight - 1, cache, 0);
    }

    private long countCol(int width, int[][] grid, int row, int col, int left, long[][] cache, int hash) {
    
        if (col == width) return countRow(width, grid, row - 1, cache, hash);
        
        final int[] prev = grid[row + 1];
        final int[] next = grid[row];

        long count = 0;
        
        if (row == 0) {
            for (int i : LAST) {
                if ((LEFT[left][i] > 0 && (col < (width - 1) || LEFT[i][0] > 0)) && TOP[prev[col]][i] > 0) {
                    count += countCol(width, grid, row, col + 1, i, cache, hash * 3 + (next[col] = MASK[i]));
                }
            }
        } else {
            for (int i = 1; i <= 10; ++i) {
                if ((LEFT[left][i] > 0 && (col < (width - 1) || LEFT[i][0] > 0)) && (TOP[prev[col]][i] > 0)) {
                    count += countCol(width, grid, row, col + 1, i, cache, hash * 3 +(next[col] = MASK[i]));
                }
            }
        }

        return count;
    }
    
    private long countRow(int width, int[][] grid, int row, long[][] cache, int hash) {
        
        if (row < 0) return 1;
        
        if (cache[row][hash] == 0) {
            cache[row][hash] = countCol(width, grid, row, 0, 0, cache, 0);
        }
        
        return cache[row][hash];
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
