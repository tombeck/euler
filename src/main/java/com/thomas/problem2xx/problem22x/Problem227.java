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
package com.thomas.problem2xx.problem22x;

import static java.lang.String.format;

import java.util.Locale;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.03.2010
 */
public class Problem227 implements Problem {

    /**
     * TODO Method documentation
     * 
     * -1 : 1/6
     *  0 : 4/6
     *  1 : 1/6
     * 
     * -2 = -1 -1 : 1/6 * 1/6 =  1/36
     * -1 = -1  0 : 1/6 * 4/6 =  4/36
     *  0 = -1  1 : 1/6 * 1/6 =  1/36
     * -1 =  0 -1 : 4/6 * 1/6 =  4/36
     *  0 =  0  0 : 4/6 * 4/6 = 16/36
     *  1 =  0  1 : 4/6 * 1/6 =  4/36
     *  0 =  1 -1 : 1/6 * 1/6 =  1/36
     *  1 =  1  0 : 1/6 * 4/6 =  4/36
     *  2 =  1  1 : 1/6 * 1/6 =  1/36
     * 
     * -2 :  1/36
     * -1 :  8/36
     *  0 : 18/36
     *  1 :  8/36
     *  2 :  1/36
     *  
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 28.03.2010
     */
    @Override
    public String solve() {

        double[] pp = new double[100];

        pp[50] = 1;

        double sum = 0;
        
        for (int n = 1; ; ++n) {

            final double[] pn = new double[100];
            
            for (int i = 1; i < 100; ++i) {
                pn[(i + 98) % 100] += pp[i] * ( 1.0 / 36);
                pn[ i -  1       ] += pp[i] * ( 8.0 / 36);
                pn[ i            ] += pp[i] * (18.0 / 36);
                pn[(i +  1) % 100] += pp[i] * ( 8.0 / 36);
                pn[(i +  2) % 100] += pp[i] * ( 1.0 / 36);
            }
            
            final double tmp = sum;
            
            if (pn[0] > 0 && (sum += pn[0] * n) == tmp) return format(Locale.ENGLISH, "%.10g", sum);

            pp = pn;
        }

    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 28.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem227());
    }

}
