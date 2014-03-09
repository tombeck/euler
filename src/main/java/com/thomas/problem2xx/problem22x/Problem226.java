/**
 * $Id$
 *
 * Copyright (c) 2014 Thomas Beckmann
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

import static com.thomas.util.NumberUtils.round;
import static java.lang.Math.sqrt;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 09.02.2014
 */
public class Problem226 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double solve() {

        double a = 0;
        
        for (double i = 0.5; ; i /= 2) {
            
            double prev = a;
            a = 0;
            
            for (double x = i/2; x < 0.5; x += i) {
                
                double d = blancmange(x) - square(x);
                
                if (d > 0) a += d;
            }
            a = round(a * i, 9);
            if (a == prev) break;
        }
        
        return round(a, 8);
    }

    double square(double x) {
    
        return 0.5 - sqrt(x * (0.5 - x));
    }
    
    double blancmange(double x) {
    
        double y = 0;
        
        for (long n2 = 1; ; n2 *= 2) {
            
            double s = s(x*n2);
            
            if (s == 0.0) break;
            y += s/n2;
        }
        
        return y;
    }
    
    double s(double x) {
    
        x = x - (long)x;
        
        if (x <= 0.5) return x;
        
        return 1 - x;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem226());
    }

}
