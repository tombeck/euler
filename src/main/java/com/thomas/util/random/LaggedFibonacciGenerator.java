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
package com.thomas.util.random;

/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 26.03.2010
 */
public class LaggedFibonacciGenerator implements IntGenerator {

    private final int[] s = new int[55];
    
    private int k = 1;

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.random.IntGenerator#next()
     * @author Thomas
     * @since 26.03.2010
     */
    @Override
    public int next() {

        final int ret = this.k <= 55
            ? (int)(((100003L - 200003L * this.k + 300007L * this.k * this.k * this.k) % 1000000))
            : (int)((this.s[(this.k - 25) % 55] + this.s[(this.k - 56) % 55]) % 1000000); 

        return this.s[(this.k++ - 1) % 55] = ret;
    }

}
