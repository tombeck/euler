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
package com.thomas.problem212;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.LookAheadIterator;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.IntGenerator;
import com.thomas.util.random.LaggedFibonacciGenerator;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem212 implements Problem {

    static class Cuboid {
        
        final int frontX;
        final int frontY;
        final int frontZ;
        final int backX;
        final int backY;
        final int backZ;
        
        public Cuboid(IntGenerator generator) {
            
            this.frontX = generator.next() % 10000;
            this.frontY = generator.next() % 10000;
            this.frontZ = generator.next() % 10000;
            this.backX = this.frontX + 1 + generator.next() % 399;
            this.backY = this.frontY + 1 + generator.next() % 399;
            this.backZ = this.frontZ + 1 + generator.next() % 399;
        }

    }
    
    static final Comparator<Cuboid> FRONT_X_COMPARATOR = new Comparator<Cuboid>() {

        @Override
        public int compare(Cuboid c1, Cuboid c2) {

            int diff = 0;
            
            if (diff == 0) diff = c1.frontX - c2.frontX;
            if (diff == 0) diff = c1.backX - c2.backX;
            if (diff == 0) diff = c1.frontY - c2.frontY;
            if (diff == 0) diff = c1.backY - c2.backY;
            if (diff == 0) diff = c1.frontZ - c2.frontZ;
            if (diff == 0) diff = c1.backZ - c2.backZ;

            return diff;
        }
        
    };
    static final Comparator<Cuboid> BACK_X_COMPARATOR = new Comparator<Cuboid>() {

        @Override
        public int compare(Cuboid c1, Cuboid c2) {

            int diff = 0;
            
            if (diff == 0) diff = c1.backX - c2.backX;
            if (diff == 0) diff = c1.frontX - c2.frontX;
            if (diff == 0) diff = c1.backY - c2.backY;
            if (diff == 0) diff = c1.frontY - c2.frontY;
            if (diff == 0) diff = c1.backZ - c2.backZ;
            if (diff == 0) diff = c1.frontZ - c2.frontZ;

            return diff;
        }
        
    };
    static final Comparator<Cuboid> FRONT_Y_COMPARATOR = new Comparator<Cuboid>() {

        @Override
        public int compare(Cuboid c1, Cuboid c2) {

            int diff = 0;
            
            if (diff == 0) diff = c1.frontY - c2.frontY;
            if (diff == 0) diff = c1.backY - c2.backY;
            if (diff == 0) diff = c1.frontZ - c2.frontZ;
            if (diff == 0) diff = c1.backZ - c2.backZ;

            return diff;
        }
        
    };
    static final Comparator<Cuboid> BACK_Y_COMPARATOR = new Comparator<Cuboid>() {

        @Override
        public int compare(Cuboid c1, Cuboid c2) {

            int diff = 0;
            
            if (diff == 0) diff = c1.backY - c2.backY;
            if (diff == 0) diff = c1.frontY - c2.frontY;
            if (diff == 0) diff = c1.backZ - c2.backZ;
            if (diff == 0) diff = c1.frontZ - c2.frontZ;

            return diff;
        }
        
    };
    static final Comparator<Cuboid> FRONT_Z_COMPARATOR = new Comparator<Cuboid>() {

        @Override
        public int compare(Cuboid c1, Cuboid c2) {

            int diff = 0;
            
            if (diff == 0) diff = c1.frontZ - c2.frontZ;
            if (diff == 0) diff = c1.backZ - c2.backZ;

            return diff;
        }
        
    };
    static final Comparator<Cuboid> BACK_Z_COMPARATOR = new Comparator<Cuboid>() {

        @Override
        public int compare(Cuboid c1, Cuboid c2) {

            int diff = 0;
            
            if (diff == 0) diff = c1.backZ - c2.backZ;
            if (diff == 0) diff = c1.frontZ - c2.frontZ;

            return diff;
        }
        
    };
    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final int max = 50000;
        final IntGenerator generator = new LaggedFibonacciGenerator();
        final TreeSet<Cuboid> cuboids = new TreeSet<Cuboid>(FRONT_X_COMPARATOR);
        final TreeSet<Cuboid> back = new TreeSet<Cuboid>(BACK_X_COMPARATOR);
        final TreeSet<Cuboid> cuboidsY = new TreeSet<Cuboid>(FRONT_Y_COMPARATOR);
        final TreeSet<Cuboid> backY = new TreeSet<Cuboid>(BACK_Y_COMPARATOR);
        final TreeSet<Cuboid> cuboidsZ = new TreeSet<Cuboid>(FRONT_Z_COMPARATOR);
        final TreeSet<Cuboid> backZ = new TreeSet<Cuboid>(BACK_Z_COMPARATOR);
        
        for (int i = 0; i < max; ++i) cuboids.add(new Cuboid(generator));
        
        final LookAheadIterator<Cuboid> front = new LookAheadIterator<Cuboid>(cuboids.iterator());
        
        long volume = 0;
        
        for (int prev = 0; front.hasNext() || !back.isEmpty(); ) {

            final int next = back.isEmpty() ? front.peekNext().frontX : (front.hasNext() ? Math.min(front.peekNext().frontX, back.first().backX) : back.first().backX);
            
            cuboidsY.clear();
            backY.clear();
            
            cuboidsY.addAll(back);
            
            final LookAheadIterator<Cuboid> frontY = new LookAheadIterator<Cuboid>(cuboidsY.iterator());
            
            long volumeY = 0;
            
            for (int prevY = 0, nextY; frontY.hasNext() || !backY.isEmpty(); prevY = nextY) {
                cuboidsZ.clear();
                cuboidsZ.addAll(backY);
                
                final LookAheadIterator<Cuboid> frontZ = new LookAheadIterator<Cuboid>(cuboidsZ.iterator());
                
                long volumeZ = 0;
                
                backZ.clear();
                nextY = backY.isEmpty() ? frontY.peekNext().frontY : (frontY.hasNext() ? Math.min(frontY.peekNext().frontY, backY.first().backY) : backY.first().backY);
                for (int prevZ = 0, nextZ; frontZ.hasNext() || !backZ.isEmpty(); prevZ = nextZ) {
                    nextZ = backZ.isEmpty() ? frontZ.peekNext().frontZ : (frontZ.hasNext() ? Math.min(frontZ.peekNext().frontZ, backZ.first().backZ) : backZ.first().backZ);
                    if (!backZ.isEmpty()) volumeZ += nextZ - prevZ;
                    for (Iterator<Cuboid> i = backZ.iterator(); i.hasNext() && i.next().backZ == nextZ; i.remove());
                    for (; frontZ.hasNext() && frontZ.peekNext().frontZ == nextZ; backZ.add(frontZ.next()));
                }
                volumeY += volumeZ * (nextY - prevY);
                for (Iterator<Cuboid> i = backY.iterator(); i.hasNext() && i.next().backY == nextY; i.remove());
                for (; frontY.hasNext() && frontY.peekNext().frontY == nextY; backY.add(frontY.next()));
            }
            volume += volumeY * (next - prev);
            
            System.out.println(next);
            
            for (Iterator<Cuboid> i = back.iterator(); i.hasNext() && i.next().backX == next; i.remove());
            for (; front.hasNext() && front.peekNext().frontX == next; back.add(front.next()));

            prev = next;
        }
        
        return volume;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem212());
    }

}
