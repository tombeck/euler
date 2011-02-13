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

import java.util.Arrays;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.matrix.IntMatrix;
import com.thomas.util.matrix.IntRowVector;

/**
 * @author Thomas Beckmann
 * @since 13.02.2011
 */
public class Problem324 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final int[][] matrix = new int[1 << 9][1 << 9];
        
        fill(new int[3][3], 0, matrix);
        
        IntMatrix m = new IntMatrix(matrix);
        
        int[] a = new int[1 << 9]; a[0] = 1;
        int[] res;
        
        for (int j = 0; j < 100; ++j) {
            int[] b = a.clone();
            res = new IntRowVector(a).multiply(m).mx[0];
            
            for (int i = 0; i < res.length; ++i) {
                if (res[i] + a[i] > 0) a[i] = 1;
            }
            if (Arrays.equals(a, b)) break;
        }
        
        int count = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > 0) ++count;
        }
        System.out.println(count);
        
        for (int i = 0; i < 10000; ++i) {
            System.out.println(i);
            m = m.modPow(10, 100000007);
        }

        return m.at(0, 0);
    }

    private int makeBottomProfile(int[][] board) {
        
        int profile = 0;
        
        for (int i = 0; i < 9; ++i) {
            
            int x = i % 3;
            int y = i / 3;
            
            if (board[x][y] == 0) profile |= 1 << i;
        }

        return profile;
    }
    
    private int makeTopProfile(int[][] board) {
        
        int profile = 0;
        
        for (int i = 0; i < 9; ++i) {
            
            int x = i % 3;
            int y = i / 3;
            
            if (board[x][y] == 1) profile |= 1 << i;
        }

        return profile;
    }

    private void fill(int[][] board, int i, int[][] matrix) {
        
        if (i == 9) {
            
            final int bottom = makeBottomProfile(board);
            final int top = makeTopProfile(board);
            
            matrix[bottom][top] += 1;

            return;
        }
        
        int x = i % 3;
        int y = i / 3;
        
        if ((y > 0 && board[x][y - 1] == 4) && (x > 0 && board[x - 1][y] == 3)) return;
        if (y > 0 && board[x][y - 1] == 4) {
            if (x > 0 && board[x - 1][y] == 3) return;
            board[x][y] = 2;
            fill(board, i + 1, matrix);
        } else if (x > 0 && board[x - 1][y] == 3) {
            board[x][y] = 5;
            fill(board, i + 1, matrix);
        } else {
            board[x][y] = 0;
            fill(board, i + 1, matrix);
            board[x][y] = 1;
            fill(board, i + 1, matrix);
            if (x < 2) {
                board[x][y] = 3;
                fill(board, i + 1, matrix);
            }
            if (y < 2) {
                board[x][y] = 4;
                fill(board, i + 1, matrix);
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem324());
    }

}
