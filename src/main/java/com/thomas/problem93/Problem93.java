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
package com.thomas.problem93;

import static com.thomas.util.Permutation.nextPermutation;
import static java.lang.Math.floor;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 19.12.2009
 */
public class Problem93 implements Problem {

    private static interface Operator  { double call(double x, double y); }
    
    private static final Operator[] OPS = {
        new Operator() { @Override public double call(double x, double y) { return x + y; } },
        new Operator() { @Override public double call(double x, double y) { return x - y; } },
        new Operator() { @Override public double call(double x, double y) { return x * y; } },
        new Operator() { @Override public double call(double x, double y) { return x / y; } }
    };
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 02.04.2010
     */
    @Override
    public Integer solve() {

        int set = 0;
        
        for (int a = 0, max = 0; a < 7; ++a) {
            for (int b = a + 1; b < 8; ++b) {
                for (int c = b + 1; c < 9; ++c) {
                    for (int d = c + 1; d < 10; ++d) {
                        
                        final int size = size(a, b, c, d);
                        
                        if (size > max) {
                            max = size;
                            set = ((a * 10 + b) * 10 + c) * 10 + d;
                        }
                    }
                }
            }
        }
        
        return set;
    }
    
    private final int size(int... a) {
        
        final Set<Integer> results = new TreeSet<Integer>();
        
        do {
            for (Operator op0 : OPS) {
                for (Operator op1 : OPS) {
                    for (Operator op2 : OPS) {
                        try {
                            double result = (op0.call(op1.call(op2.call(a[0], a[1]), a[2]), a[3]));
                            if (result > 0 && result == floor(result)) results.add((int)result);
                        } catch (ArithmeticException e) { /**/ }
                        try {
                            double result = (op0.call(op1.call(a[0], op2.call(a[1], a[2])), a[3]));
                            if (result > 0 && result == floor(result)) results.add((int)result);
                        } catch (ArithmeticException e) { /**/ }
                        try {
                            double result = (op0.call(a[0], op1.call(op2.call(a[1], a[2]), a[3])));
                            if (result > 0 && result == floor(result)) results.add((int)result);
                        } catch (ArithmeticException e) { /**/ }
                        try {
                            double result = (op0.call(a[0], op1.call(a[1], op2.call(a[2], a[3]))));
                            if (result > 0 && result == floor(result)) results.add((int)result);
                        } catch (ArithmeticException e) { /**/ }
                        try {
                            double result = (op0.call(op1.call(a[0], a[1]), op2.call(a[2], a[3])));
                            if (result > 0 && result == floor(result)) results.add((int)result);
                        } catch (ArithmeticException e) { /**/ }
                    }
                }
            }
        } while (nextPermutation(a));
        
        int max = 0;
        
        for (int i : results) if (i != ++max) break; 
        
        return max;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 19.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem93());
    }

}
