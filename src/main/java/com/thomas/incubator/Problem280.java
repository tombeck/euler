/**
 * $Id$
 *
 * Copyright (c) 2011 Thomas Beckmann
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.NumberUtils;
import com.thomas.util.rational.BigRational;

/**
 * @author Thomas Beckmann
 * @since 15.01.2011
 */
public class Problem280 implements Problem {

    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final BigDecimal THREE = BigDecimal.valueOf(3);
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    
    private static final BigDecimal[][] P = {
            {TWO, THREE, THREE, THREE, TWO},
            {THREE, FOUR, FOUR, FOUR, THREE},
            {THREE, FOUR, FOUR, FOUR, THREE},
            {THREE, FOUR, FOUR, FOUR, THREE},
            {TWO, THREE, THREE, THREE, TWO},
    };
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Object solve() {

        BigDecimal exp = BigDecimal.ZERO;
        BigDecimal[][][][] prev = makeArray();
        
        prev[(1 << 5) - 1][0][2][2] = BigDecimal.ONE.setScale(100);
        
        for (int n = 0; n < 4000; ++n) {
            
            final BigDecimal[][][][] next = makeArray();
            
            for (int p = 0; p < (1 << 5); ++p) {
                for (int d = 0; d < (1 << 5); ++d) {
                    
                    final int count_d = Integer.bitCount(d);
                    final int f = Integer.bitCount(p) + count_d;
                    
                    if (count_d == 5 || f < 4 || 5 < f) continue;
                    
                    for (int r = 0; r < 5; ++r) {
                        for (int c = 0; c < 5; ++c) {
                            
                            final BigDecimal prob = prev[p][d][r][c].divide(P[r][c], RoundingMode.HALF_UP);
                            
//                            if (!prev[p][d][r][c].equals(BigDecimal.ZERO)) {
//                                System.out.println(prob);
//                            }
                            if (r > 0) next[p(p, r - 1, c, f)][d(d, r - 1, c, f)][r - 1][c] = next[p(p, r - 1, c, f)][d(d, r - 1, c, f)][r - 1][c].add(prob); 
                            if (c < 4) next[p(p, r, c + 1, f)][d(d, r, c + 1, f)][r][c + 1] = next[p(p, r, c + 1, f)][d(d, r, c + 1, f)][r][c + 1].add(prob); 
                            if (r < 4) next[p(p, r + 1, c, f)][d(d, r + 1, c, f)][r + 1][c] = next[p(p, r + 1, c, f)][d(d, r + 1, c, f)][r + 1][c].add(prob);
                            if (c > 0) next[p(p, r, c - 1, f)][d(d, r, c - 1, f)][r][c - 1] = next[p(p, r, c - 1, f)][d(d, r, c - 1, f)][r][c - 1].add(prob); 
                        }
                    }
                }
            }
            prev = next;
            exp = exp.add(BigDecimal.valueOf(n).multiply(prev[0][(1 << 5) - 1][0][0].add(prev[0][(1 << 5) - 1][0][1]).add(prev[0][(1 << 5) - 1][0][2]).add(prev[0][(1 << 5) - 1][0][3]).add(prev[0][(1 << 5) - 1][0][4])));
            //print(prev[0][(1 << 5) - 1]);
            System.out.println(exp);
        }
        
        return exp;
    }

    private BigDecimal[][][][] makeArray() {
        
        final BigDecimal[][][][] a = new BigDecimal[1 << 5][1 << 5][5][5];;
        
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                for (int k = 0; k < a[i][j].length; ++k) {
                    Arrays.fill(a[i][j][k], BigDecimal.ZERO);
                }
            }
        }
        
        return a;
    }
    
    private int p(int p, int r, int c, int f) {
        
        boolean carry = f == 4;
        
        if (carry) return p;
        
        if (!isPickSquare(p, r, c)) return p;
        
        int ret = p & ~(1 << c);
        
        return ret;
    }
    
    private int d(int d, int r, int c, int f) {
        
        boolean carry = f == 4;
        
        if (!carry) return d;
        
        if (!isDropSquare(d, r, c)) return d;
        
        int ret = d | (1 << c);
        
        if (ret == 31 && r != 0) {
            System.out.println("oops");
        }
        
        return ret;
    }
    
    private boolean isPickSquare(int p, int r, int c) {
        
        return r == 4 && (p & (1 << c)) != 0;
    }
    
    private boolean isDropSquare(int d, int r, int c) {
        
        return r == 0 && (d & (1 << c)) == 0;
    }
    
    private void print(double[][] grid) {
        
        System.out.println();
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                System.out.printf(" %.5f", grid[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem280());
    }

}
