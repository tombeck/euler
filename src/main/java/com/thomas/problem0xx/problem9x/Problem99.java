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

import static com.thomas.util.IOUtils.closeQuietly;
import static com.thomas.util.NumberUtils.parseInt;
import static java.lang.Math.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 08.11.2009
 */
public class Problem99 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws IOException 
     * @since 28.02.2010
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("base_exp.txt")));

        try {

            int ln = 1;
            int ln_max = 0;
            int[] n_max = {1, 0};
            
            for (String line; (line = reader.readLine()) != null; ++ln) {
                
                final int[] n = parseInt(line.split(","));
                
                if (n[1] * log(n[0]) > n_max[1] * log(n_max[0])) {
                    n_max = n;
                    ln_max = ln;
                }
            }
            
            return ln_max;

        } finally {
            closeQuietly(reader);
        }
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @throws IOException 
     * @since 08.11.2009
     */
    public static void main(String[] args) throws IOException {

        Euler.run(new Problem99());
    }

}
