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
package com.thomas.problem126;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem126 implements Problem {

    /**
     * (c-2)*(2(a+b)+4(n-1))+2*sum[1..n](2(a+b)+4(m-1))+2ab
     * (c-2)*(2a+2b+4n-4)+2*sum[1..n](2(a+b))+2*sum[1..n](4(m-1))+2ab
     * 2ab+2ac+2bc+4nc-4c-4a-4b-8n+8+4na+4nb+4n^2+4n-8n
     * 
     * 2ab+2ac+2bc+4nc-4c-4a-4b+8+4na+4nb+4n^2-12n
     *
     * 2(ab+ac+bc)-4(c+a+b)+8+4n(a+b+c)+4n^2-12n
     *
     * 2(ab+ac+bc)+4(n-1)(a+b+c)+4n(n-1)-8(n-1)
     *
     * 2(ab+ac+bc)+4(n-1)(a+b+c+n-2)
     * 
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        for (int n = 1; ; ++n) {
            if (c(n) == 1000) return 2 * n;
        }
    }

    private int c(int n) {

        int count = 0;
        
        for (int a = 1; 2 * a + 1 <= n; ++a) {
            for (int b = 1; b <= a && a * b + a + b <= n; ++b) {
                for (int c = 1, t; c <= b && (t = a * b + a * c + b * c) <= n; ++c) {
                    for (int l = 0, cover; (cover = t + 2 * l * (a + b + c + l - 1)) <= n; ++l) {
                        if (cover == n) ++count;
                    }
                }
            }
        }

        return count;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem126());
    }

}
