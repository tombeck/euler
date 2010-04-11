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

import static com.thomas.util.IOUtils.closeQuietly;
import static java.lang.Character.digit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.10.2009
 */
class Problem96 implements Problem {

    /**
     * TODO Method documentation 24702
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws IOException 
     * @since 28.02.2010
     */
    @Override
    public Integer solve() throws IOException {

        int sum = 0;
        
        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("sudoku.txt")));

        try {
            
            final int[][] grid = new int[9][9];
            
            int row = -1;
            for (String line; (line = reader.readLine()) != null; ) {
                if (row > -1) {
                    int col = 0;
                    for (char ch : line.toCharArray()) grid[row][col++] = digit(ch, 10);
                }
                if (++row == 9) {
                    row = -1;
                    final int[][] solution = new SuDokuSolver(grid).solve();
                    sum += solution[0][0] * 100 + solution[0][1] * 10 + solution[0][2];
                }
            }
        } finally {
            closeQuietly(reader);
        }
        
        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @throws IOException 
     * @since 31.10.2009
     */
    public static void main(String[] args) throws IOException {

        Euler.run(new Problem96());
    }

}
