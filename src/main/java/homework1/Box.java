package homework1;

import homework1.fruit.Fruit;

import java.util.ArrayList;
import java.util.Collections;

public class Box<E extends Fruit> {
    private final ArrayList<E> box;

    public Box() {
        box = new ArrayList<>();
    }
    public Box(E... fruits) {
        this();
        Collections.addAll(box, fruits);
    }

    public float getWeight(){
        float sum = 0;
        for(E fruit : box){
            sum +=fruit.getWeight();
        }
        return sum;
    }

    public boolean compare(Box<?> another) {
        return this.getWeight() - another.getWeight() < 0.0001;
    }

    public void refillFruits(Box<E> anotherBox) {
        for (E fruit : box) {
            anotherBox.add(fruit);
        }
        box.clear();
    }

    public void add(E fruit) {
        box.add(fruit);
    }


}
