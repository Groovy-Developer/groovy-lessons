package org.example;

public class Cat {
    private int length;
    private int width;

    public Cat() {
    }

    public Cat(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

//    public void setLength(int length) {
//        this.length = length;
//    }

    public int getWidth() {
        return width;
    }

//    public void setWidth(int width) {
//        this.width = width;
//    }

    public int calculateSquare() {
        return length * width;
    }

    private void setLengthPrivate(int value) {
        length = 2 * value;
    }
}
