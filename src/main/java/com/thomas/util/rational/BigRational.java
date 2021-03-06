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

import java.math.BigInteger;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public final class BigRational implements Comparable<BigRational> {

    public static final BigRational ZERO = new BigRational(0);
    public static final BigRational ONE = new BigRational(1);
    
    private final BigInteger num;
    private final BigInteger den;
    
    /**
     * @param num
     * @param den
     */
    public BigRational(BigInteger num, BigInteger den) {

        final BigInteger gcd = num.gcd(den);
        final BigInteger sgcd = den.signum() < 0 ? gcd.negate() :  gcd;
        
        this.num = num.divide(sgcd);
        this.den = den.divide(sgcd);
    }

    /**
     * @param num
     * @param den
     */
    public BigRational(long num, long den) {

        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    /**
     * @param num
     * @param den
     */
    public BigRational(long num) {

        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    public BigRational add(BigRational other) {
    
        final BigInteger gcd = this.den.gcd(other.den);
        
        return new BigRational(
                this.num.multiply(other.den).add(other.num.multiply(this.den)).divide(gcd),
                this.den.multiply(other.den).divide(gcd));
    }

    public BigRational subtract(BigRational other) {
        
        final BigInteger gcd = this.den.gcd(other.den);
        
        return new BigRational(
                this.num.multiply(other.den).subtract(other.num.multiply(this.den)).divide(gcd),
                this.den.multiply(other.den).divide(gcd));
    }

    public BigRational multiply(BigRational other) {
        
        final BigInteger gcd = this.num.gcd(other.den).multiply(other.num.gcd(this.den));
        
        return new BigRational(
                this.num.multiply(other.num).divide(gcd),
                this.den.multiply(other.den).divide(gcd));
    }

    public BigRational divide(BigRational other) {
        
        if (other.num.signum() == 0) throw new ArithmeticException("divide by zero");
        
        final BigInteger gcd = this.num.gcd(other.num).multiply(other.den.gcd(this.den));
        final BigInteger sgcd = other.num.signum() < 0 ? gcd.negate() : gcd;
        
        return new BigRational(
                this.num.multiply(other.den).divide(sgcd),
                this.den.multiply(other.num).divide(sgcd));
    }

    public BigRational negate() {
        
        return new BigRational(this.num.negate(), this.den);
    }
    
    public BigRational abs() {
        
        return new BigRational(this.num.abs(), this.den);
    }
    
    public BigRational invert() {
        
        if (this.num.signum() == 0) throw new ArithmeticException("divide by zero");
        
        return this.num.signum() < 0 ? new BigRational(this.den.negate(), this.num.negate()) : new BigRational(this.den, this.num);
    }
    
    public int signum() {
        
        return this.num.signum();
    }
    
    public BigRational min(BigRational other) {
    
        return this.compareTo(other) < 0 ? this : other;
    }
    
    public BigRational max(BigRational other) {
        
        return this.compareTo(other) > 0 ? this : other;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(BigRational other) {
        
        return this.num.multiply(other.den).compareTo(other.num.multiply(this.den));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        int result = 1;
        
        result = 31 * result + this.den.hashCode();
        result = 31 * result + this.num.hashCode();
        
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;
        if (!(o instanceof BigRational)) return false;
        
        final BigRational other = (BigRational)o;
        
        return this.num.equals(other.num) && this.den.equals(other.den);
    }
    
    
    
    @Override
    public String toString() {
    
        return this.num + (BigInteger.ONE.equals(this.den) ? "" : "/" + this.den);
    }

    public static void main(String[] args) {
    
        BigRational r1 = new BigRational(1, 6);
        BigRational r2 = new BigRational(4, -6);
        
        System.out.println(r1.add(r2));
        System.out.println(r1.subtract(r2));
        System.out.println(r1.multiply(r2));
        System.out.println(r1.divide(r2));
        System.out.println(r1.negate());
        System.out.println(r1.invert());
        System.out.println(r1.compareTo(r2));
        System.out.println(r2.abs());
        System.out.println(r1.min(r2));
        System.out.println(r1.max(r2));
    }

}
