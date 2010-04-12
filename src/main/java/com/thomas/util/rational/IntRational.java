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
package com.thomas.util.rational;

import static com.thomas.Util.gcd;
import static java.lang.Math.abs;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 07.02.2010
 */
public final class IntRational extends Number implements Comparable<IntRational> {

    private static final long serialVersionUID = -3922885969551064286L;
    
    public static final IntRational ZERO = new IntRational(0, 1);
    public static final IntRational ONE = new IntRational(1, 1);
    
    private final int num;
    private final int den;
    
    public IntRational(IntRational other) {

        this.num = other.num;
        this.den = other.den;
    }
    
    public IntRational(int num, int den) {

        final int gcd = gcd(abs(num), abs(den));
        
        this.num = (num / gcd) * Integer.signum(den);
        this.den = den / gcd * Integer.signum(den);
    }
    
    public IntRational(int num) {

        this(num, 1);
    }
    
    public int numerator() {
        
        return this.num;
    }
    
    public int denominator() {
        
        return this.den;
    }
    
    public IntRational add(IntRational other) {
        
        final int gcd = gcd(this.den, other.den);
        final int tmp = this.den / gcd;
        
        return new IntRational((this.num * (other.den / gcd) + other.num * tmp), (tmp * other.den));
    }
    
    public IntRational subtract(IntRational other) {
        
        final int gcd = gcd(this.den, other.den);
        final int tmp = this.den / gcd;
        
        return new IntRational((this.num * (other.den / gcd) - other.num * tmp), (tmp * other.den));
    }
    
    public IntRational multiply(IntRational other) {
    
        final int gcd1 = gcd(abs(this.num), other.den);
        final int gcd2 = gcd(this.den, abs(other.num));
        
        return new IntRational(((this.num / gcd1) * (other.num / gcd2)), ((this.den / gcd2) * (other.den / gcd1)));
    }
    
    public IntRational divide(IntRational other) {
        
        final int gcd1 = gcd(abs(this.num), abs(other.num));
        final int gcd2 = gcd(this.den, other.den);

        return new IntRational(((this.num / gcd1) * (other.den / gcd2) * Long.signum(other.num)), ((this.den / gcd2) * (abs(other.num) / gcd1)));
    }
    
    @Override
    public String toString() {
    
        return this.num + "/" + this.den;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param other
     * @return
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @author Thomas
     * @since 20.03.2010
     */
    @Override
    public int compareTo(IntRational other) {
        
        return Long.signum((long)this.num * other.den - (long)other.num * this.den);
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @author Thomas
     * @since 20.03.2010
     */
    public int signum() {

        return Long.signum(this.num);
    }

    public IntRational min(IntRational other) {
    
        return this.num * other.den < other.num * this.den ? this : other;
    }
    
    public IntRational max(IntRational other) {
        
        return this.num * other.den > other.num * this.den ? this : other;
    }
    
    @Override
    public int hashCode() {

        int result = 1;
        
        result = 31 * result + (int)(this.den ^ (this.den >>> 32));
        result = 31 * result + (int)(this.num ^ (this.num >>> 32));
        
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof IntRational)) return false;

        final IntRational other = (IntRational)obj;
        
        return this.den == other.den && this.num == other.num;
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see java.lang.Number#doubleValue()
     * @author Thomas
     * @since 21.03.2010
     */
    @Override
    public double doubleValue() {

        return (double)this.num / this.den;
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see java.lang.Number#floatValue()
     * @author Thomas
     * @since 21.03.2010
     */
    @Override
    public float floatValue() {

        return (float)doubleValue();
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see java.lang.Number#intValue()
     * @author Thomas
     * @since 21.03.2010
     */
    @Override
    public int intValue() {

        return this.num / this.den;
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see java.lang.Number#longValue()
     * @author Thomas
     * @since 21.03.2010
     */
    @Override
    public long longValue() {

        return intValue();
    }

}
