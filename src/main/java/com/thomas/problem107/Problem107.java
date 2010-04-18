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
package com.thomas.problem107;

import static com.thomas.util.IOUtils.closeQuietly;
import static com.thomas.util.NumberUtils.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 21.02.2010
 */
public class Problem107 implements Problem {

    /**
     * Prim's algorithm (find minimal spanning tree)
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws IOException 
     * @since 21.02.2010
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("network.txt")));

        final int size = 40;
        final int[][] net = new int[size][];
        
        int saving = 0;

        try {
            int i = 0;
            for (String line; (line = reader.readLine()) != null; ++i) {
                net[i] = parseInt(line.replaceAll("-", "0").split(","));
                for (int j = i + 1; j < size; ++j) saving += net[i][j];
            }
        } finally {
            closeQuietly(reader);
        }

        final boolean[] visited = new boolean[size];
        final int[] min = new int[size];

        for (int i = 0; i < size; ) {
            saving -= min[i];
            visited[i] = true;
            for (int j = 0; j < size; ++j) {
                if ((min[j] == 0) || (net[i][j] > 0 && net[i][j] < min[j])) min[j] = net[i][j];
            }
            for (i = 0; i < size && (min[i] == 0 || visited[i]); ) ++i;
            for (int j = i + 1; j < size; ++j) {
                if (min[j] != 0 && !visited[j] && min[j] < min[i]) i = j;
            }
        }
        
        return saving;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 21.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem107());
    }

}
