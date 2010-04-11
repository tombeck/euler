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
package com.thomas.problem96;

import static java.lang.Integer.bitCount;
import static java.lang.Integer.numberOfTrailingZeros;

import java.util.Arrays;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.10.2009
 */
public class SuDokuSolver {

    private final int[][] grid;
    
    public SuDokuSolver(int[][] layout) {
        
        this.grid = new int[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.grid[i][j] = layout[i][j] == 0 ? 0x3fe : (1 << layout[i][j]);
            }
        }
    }

    public SuDokuSolver(SuDokuSolver source) {
        
        this.grid = new int[9][];
        for (int i = 0; i < 9; ++i) {
            this.grid[i] = Arrays.copyOf(source.grid[i], 9);
        }
    }
    
    public int[][] solve(){

        simplify();

        boolean finished = true;
        boolean valid = true;
        boolean solveable = true;
        int row_sel = 0;
        int col_sel = 0;
        int min_size = 10;
        
        for (int i = 0; i < 9; ++i) {
            int row = 0;
            int col = 0;
            int box = 0;
            for (int j = 0; j < 9; ++j) {
                final int size = bitCount(this.grid[i][j]);
                finished &= (size == 1);
                solveable &= (size != 0);
                row |= this.grid[i][j];
                col |= this.grid[j][i];
                box |= this.grid[3 * (i % 3) + j % 3][3 * (i / 3) + j / 3];
                if (size > 1 && size < min_size) {
                    row_sel = i;
                    col_sel = j;
                    min_size = size;
                }
            }
            valid &= (row == 0x3fe && col == 0x3fe && box == 0x3fe);
        }
        if (finished) return valid ? makeResult() : null;
        if (!solveable) return null;

        for (int c = this.grid[row_sel][col_sel], number = 1; (c >>= 1) > 0; ++number) {
            if ((c & 1) != 0) {
                
                final SuDokuSolver solver = new SuDokuSolver(this);
                
                solver.grid[row_sel][col_sel] &= (1 << number);
                
                final int[][] ret = solver.solve();
                
                if (ret != null) return ret;
            }
        }

        return null;
    }

    private void simplify() {
        
        boolean hasChanged;
        
        do {
            hasChanged = false;
            for (int i = 0; i < 9; ++i) {
                int n = 0;
                for (int j = 0; j < 9; ++j) {
                    int cell = this.grid[i][j]; 
                    if (bitCount(cell) == 1) n |= cell;
                }
                for (int j = 0; j < 9; ++j) {
                    int cell = this.grid[i][j]; 
                    if (bitCount(cell) > 1) {
                        this.grid[i][j] &= ~n;
                        hasChanged |= (cell != this.grid[i][j]);
                    }
                }
            }
            for (int i = 0; i < 9; ++i) {
                int n = 0;
                for (int j = 0; j < 9; ++j) {
                    int cell = this.grid[j][i]; 
                    if (bitCount(cell) == 1) n |= cell;
                }
                for (int j = 0; j < 9; ++j) {
                    int cell = this.grid[j][i]; 
                    if (bitCount(cell) > 1) {
                        this.grid[j][i] &= ~n;
                        hasChanged |= (cell != this.grid[j][i]);
                    }
                }
            }
            for (int i = 0; i < 9; ++i) {
                int n = 0;
                for (int j = 0; j < 9; ++j) {
                    int cell = this.grid[3 * (i % 3) + j % 3][3 * (i / 3) + j / 3]; 
                    if (bitCount(cell) == 1) n |= cell;
                }
                for (int j = 0; j < 9; ++j) {
                    int cell = this.grid[3 * (i % 3) + j % 3][3 * (i / 3) + j / 3]; 
                    if (bitCount(cell) > 1) {
                        this.grid[3 * (i % 3) + j % 3][3 * (i / 3) + j / 3] &= ~n;
                        hasChanged |= (cell != this.grid[3 * (i % 3) + j % 3][3 * (i / 3) + j / 3]);
                    }
                }
            }
        } while (hasChanged);
    }
    
    private int[][] makeResult() {
    
        final int[][] result = new int[9][9];
        
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                result[row][col] = numberOfTrailingZeros(this.grid[row][col]);
            }
        }
        
        return result;
    }
    
}
