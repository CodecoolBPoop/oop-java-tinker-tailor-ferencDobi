package com.codecool.tinker;

import java.util.ArrayList;
import java.util.List;

public class Tinker<T> extends ArrayList<T> {

    @Override
    public T get(int index) {
        return super.get(index % size());
    }

    @Override
    public T set(int index, T element) {
        return super.set(index % size(), element);
    }

    public static void tinkerSolver(int numOfChildren, int rhymeLength) {
        List<Integer> children = new Tinker<>();
        for (int i = 1; i <= numOfChildren; i++) {
            children.add(i);
        }
        for (int index = rhymeLength - 1, pointer = index; pointer < rhymeLength * numOfChildren; index++) {
            int child = children.get(index);
            if (child == 0) continue;
            if (++pointer % rhymeLength == 0) {
                System.out.print(child + " ");
                children.set(index, 0);
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Tinker.tinkerSolver(5, 3);
        Tinker.tinkerSolver(6, 8);
        Tinker.tinkerSolver(5, 1);
        Tinker.tinkerSolver(8, 4);
    }
}
