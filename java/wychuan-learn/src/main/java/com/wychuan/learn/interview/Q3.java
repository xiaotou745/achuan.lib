package com.wychuan.learn.interview;

public class Q3 {
	public static void main(String[] args) {

        Super sooper = new Super();
        Sub sub = new Sub();
        System.out.println(sooper.getLenght().toString() + "," + sub.getLenght().toString());
    }
}

class Super {
    public Integer getLenght() {
        return new Integer(4);
    }
}
class Sub extends Super {
    public Integer getLenght() {
        return new Integer(5);
    }
}
