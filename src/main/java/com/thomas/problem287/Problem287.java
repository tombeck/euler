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
package com.thomas.problem287;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 08.01.2011
 */
public class Problem287 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int n = 24;
        final int s = 1 << (n - 1);

        return 1 + count(s, 0, 0, s) + count(s, s, 0, s) + count(s, 0, s, s) + count(s, s, s, s);
    }

    private long count(int s, int x, int y, long t) {
        
        if (s == 1) return 2;
        
        final boolean br, tl;

        if (((x - t) * (x - t) + (y - t) * (y - t) <= t * t) == (br = (x + s - 1 - t) * (x + s - 1 - t) + (y - t) * (y - t) <= t * t) &&
            br == (tl = (x - t) * (x - t) + (y + s - 1 - t) * (y + s - 1 - t) <= t * t) &&
            tl == ((x + s - 1 - t) * (x + s - 1 - t) + (y + s - 1 - t) * (y + s - 1 - t) <= t * t)) return 2;
        
        s >>= 1;
        
        return 1 + count(s, x, y, t) + count(s, x + s, y, t) + count(s, x, y + s, t) + count(s, x + s, y + s, t);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem287());
    }

}
