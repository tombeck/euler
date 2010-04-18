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
package com.thomas.problem259;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.function.Function0;
import com.thomas.util.rational.LongRational;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 01.04.2010
 */
public class Problem259 implements Problem {

    static interface Function extends Function0<Collection<LongRational>> {}
    
    static final class Constant implements Function {
        
        private final int value;
        private final Set<LongRational> call;
        

        public Constant(int value) {

            this.value = value;
            this.call = Collections.singleton(new LongRational(this.value));
        }

        @Override
        public int hashCode() {

            return 31 + this.value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof Constant)) return false;
            
            final Constant other = (Constant)obj;
            
            return this.value == other.value;
        }

        @Override
        public Collection<LongRational> call() {

            return this.call;
        }
        
    }
    
    static final class Operator implements Function {
    
        private final Function left;
        private final Function right;
        
        public Operator(Function left, Function right) {

            this.left = left;
            this.right = right;
        }

        @Override
        public int hashCode() {

            int result = 1;
            
            result = 31 * result + this.left.hashCode();
            result = 31 * result + this.right.hashCode();
            
            return result;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof Operator)) return false;

            final Operator other = (Operator)obj;

            return this.left.equals(other.left) && this.right.equals(other.right);
        }
        
        transient Collection<LongRational> call;
        
        @Override
        public Collection<LongRational> call() {

            if (this.call == null) {
                this.call = new HashSet<LongRational>();
                for (LongRational first : this.left.call()) {
                    for (LongRational second : this.right.call()) {
                        this.call.add(first.add(second));
                        this.call.add(first.subtract(second));
                        this.call.add(first.multiply(second));
                        try {
                            this.call.add(first.divide(second));
                        } catch (ArithmeticException e) { /**/ }
                    }
                }
            }
            
            return this.call;
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 01.04.2010
     */
    @Override
    public Long solve() {

        final int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        final Map<Function, Function> cache = new HashMap<Function, Function>();
        final Set<Long> reacheable = new TreeSet<Long>();
        
        long sum = 0;
        final Set<Function> functions = split(digits, 0, digits.length, cache);
        cache.clear();
        
        for (Iterator<Function> i = functions.iterator(); i.hasNext(); i.remove()) {
            final Function f = i.next();
            for (LongRational r : f.call()) {
                if (r.denominator() == 1 && r.numerator() > 0) {
                    reacheable.add(r.numerator());
                }
            }
        }

        for (Long r : reacheable) sum += r;
        
        return sum;
    }

    private Set<Function> split(int[] digits, int first, int last, Map<Function, Function> cache) {
    
        Set<Function> ret = new HashSet<Function>();
    
        ret.add(constant(toInt(digits, first, last), cache));
        
        if (last - first > 1) {
            for (int i = first + 1; i < last; ++i) {
                for (Function left : split(digits, first, i, cache)) {
                    for (Function right : split(digits, i, last, cache)) {
                        ret.add(operator(left, right, cache));
                    }
                }
            }
        }
        
        return ret;
    }
    
    private Function constant(int i, Map<Function, Function> cache) {
        
        final Function f = new Constant(i);
        
        if (cache.containsKey(f)) return cache.get(f);
        
        cache.put(f, f);
        
        return f;
    }
    
    private Function operator(Function left, Function right, Map<Function, Function> cache) {
        
        final Function f = new Operator(left, right);
        
        if (cache.containsKey(f)) return cache.get(f);
        
        cache.put(f, f);
        
        return f;
    }
    
    private int toInt(int[] digits, int first, int last) {
        
        int ret = 0;
        
        while (first != last) ret = ret * 10 + digits[first++];
        
        return ret;
    }
    
    /**
     * TODO Method documentation
     *
     * @param args
     * @author Thomas
     * @since 01.04.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem259());
    }

}
