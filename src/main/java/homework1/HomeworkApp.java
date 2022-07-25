package homework1;

import homework1.fruit.Apple;
import homework1.fruit.Orange;

import java.util.ArrayList;
import java.util.Collections;

public class HomeworkApp {
    public static void main(String[] args) {


        //проверка box
        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < 6; i++) {
            appleBox.add(new Apple());
        }
        System.out.println(appleBox.getWeight());


        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 4; i++) {
            orangeBox.add(new Orange());
        }

        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox.compare(appleBox));

        Box<Apple> appleBox1 = new Box<>();
        for (int i = 0; i < 10; i++) {
            appleBox1.add(new Apple());
        }

        System.out.println(appleBox1.getWeight());
        appleBox1.refillFruits(appleBox);
        System.out.println(appleBox.getWeight());
        

    }

    public static <T> void rearrange(T[] array, int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;


    }
    public static <E> ArrayList<E> createArrayList(E[] array) {
        ArrayList<E> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        return arrayList;
    }
}
