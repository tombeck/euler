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
package com.thomas.problem3xx.problem34x;

import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 23.12.2011
 */
public class Problem349 implements Problem {

    final static class Coordinate {
    
        final int x;
        final int y;

        public Coordinate(int x, int y) {

            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {

            int result = 1;
            
            result = 31 * result + this.x;
            result = 31 * result + this.y;
            
            return result;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof Coordinate)) return false;

            final Coordinate other = (Coordinate)obj;
            
            return this.x == other.x && this.y == other.y;
        }

    }
    
    static class Grid {
    
        private final Set<Coordinate> blackSquares = new HashSet<Coordinate>();
        
        public boolean flip(Coordinate coordinate) {
        
            final boolean black = this.blackSquares.contains(coordinate);
            
            if (black) {
                this.blackSquares.remove(coordinate);
            } else {
                this.blackSquares.add(coordinate);
            }
            
            return black;
        }

        public int countBlackSquares() {
            
            return this.blackSquares.size();
        }

    }
    
    static enum Orientation {
        
        UP {

            @Override
            public Orientation rotateLeft() { return LEFT; }
            
            @Override
            public Orientation rotateRight() { return RIGHT; }

            @Override
            public Coordinate move(Coordinate coordinate) {

                return new Coordinate(coordinate.x, coordinate.y - 1);
            }
            
        },
        DOWN {

            @Override
            public Orientation rotateLeft() { return RIGHT; }
            
            @Override
            public Orientation rotateRight() { return LEFT; }
            
            @Override
            public Coordinate move(Coordinate coordinate) {

                return new Coordinate(coordinate.x, coordinate.y + 1);
            }
            
        },
        LEFT {

            @Override
            public Orientation rotateLeft() { return DOWN; }
            
            @Override
            public Orientation rotateRight() { return UP; }
            
            @Override
            public Coordinate move(Coordinate coordinate) {

                return new Coordinate(coordinate.x + 1, coordinate.y);
            }
            
        },
        RIGHT {

            @Override
            public Orientation rotateLeft() { return UP; }
            
            @Override
            public Orientation rotateRight() { return DOWN; }
            
            @Override
            public Coordinate move(Coordinate coordinate) {

                return new Coordinate(coordinate.x - 1, coordinate.y);
            }
            
        };
        
        public abstract Orientation rotateLeft();
        
        public abstract Orientation rotateRight();
        
        public abstract Coordinate move(Coordinate coordinate);
        
    }
    
    static class Ant {
    
        private Coordinate coordinate = new Coordinate(0, 0);
        private Orientation orientation = Orientation.RIGHT;
        
        public void move(Grid grid) {
            
            this.orientation = grid.flip(this.coordinate) ? this.orientation.rotateLeft() : this.orientation.rotateRight();
            this.coordinate = this.orientation.move(this.coordinate);
        }
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final Grid grid = new Grid();
        final Ant ant = new Ant();
        
        for (int i = 0; i < 10024; ++i) {
            ant.move(grid);
        }

        return ((1000000000000000000L - 10024) / 104) * 12 + grid.countBlackSquares();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem349());
    }

}
