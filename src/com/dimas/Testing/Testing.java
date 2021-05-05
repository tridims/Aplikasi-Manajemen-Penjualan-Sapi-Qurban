package com.dimas.Testing;

import java.util.ArrayList;
import java.util.List;

public class Testing {
    public static void main(String[] args) {
        // Testing HashMap
//        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
//        hashMap.put(1, "Hello");
//        hashMap.put(2, "World");
//        hashMap.put(3, "How");
//        hashMap.put(4, "Are");
//        hashMap.put(5, "You");
//
//        System.out.println(hashMap);
//
//        for (String str:hashMap.values()){
//            System.out.println(str);
//        }

        // Testing static field bisa di inisialisasi di dalam class lain yang belum di inisialisasi = bisa
        // Testing static field bisa di pointerkan oleh non static field dan masih bisa = bisa
        TestObject testObject = new TestObject();
        testObject.printList();
        TestObject2 testObject2 = new TestObject2();
        testObject2.printList2();

    }
}

class TestObject{
    static List<String> stringList = new ArrayList<>();

    String name2 = "Tri Mustakim";
    TestObject(){
        stringList.add(name2);
    }

    void printList(){
        for (String str:stringList){
            System.out.println(str);
        }
    }
}

class TestObject2{
    List<String> stringList = TestObject.stringList;

    String name1 = "Dimas";
    TestObject2(){
        stringList.add(name1);
    }

    void printList2(){
        for (String str:stringList){
            System.out.println(str);
        }
    }
}
