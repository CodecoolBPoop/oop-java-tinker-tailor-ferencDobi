package com.codecool.tinker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class Tinker<T> extends ArrayList<T> {

    @Override
    public T get(int index) {
        return super.get(index % size());
    }

    @Override
    public T set(int index, T element) {
        return super.set(index % size(), element);
    }

    public static void timer(int a, int b, IntBinaryOperator method) {
        long start = System.currentTimeMillis();
        method.applyAsInt(a, b);
        long end = System.currentTimeMillis();
        System.out.println("Completed in: " + (end - start) + "ms.");
    }

    public static int tinkerSolver(int numOfChildren, int rhymeLength) {
        List<Integer> children = new Tinker<>();
        List<Integer> eliminated = new Tinker<>();
        for (int i = 1; i <= numOfChildren; i++) {
            children.add(i);
        }
        // index traverses the actual list, pointer traverses a virtual list of children still in the game
        for (int index = rhymeLength - 1, pointer = index; pointer < rhymeLength * numOfChildren; index++) {
            int child = children.get(index); // query child
            if (child == 0) continue; // if child has been eliminated, skip to next iteration
            if (++pointer % rhymeLength == 0) { // advance the pointer and check if the child should be eliminated
                eliminated.add(child);
                children.set(index, 0); // eliminate the child
            }
        }
        System.out.println(eliminated.toString());
        return 0;
    }

    public static int tinkerSolverEfficient(int numOfChildren, int rhymeLength) {
        List<Integer> inGame = new Tinker<>();
        List<Integer> eliminated = new Tinker<>();
        for (int i = 1; i <= numOfChildren; i++) {
            inGame.add(i);
        }
        int i = 0;
        while (!inGame.isEmpty()) {
            i = (i + rhymeLength - 1) % inGame.size();
            eliminated.add(inGame.get(i));
            inGame.remove(i);
        }
        System.out.println(eliminated.toString());
        return 0;
    }


    public static void main(String[] args) {
        timer(5, 3, Tinker::tinkerSolver);
        timer(6, 8, Tinker::tinkerSolver);
        timer(5, 1, Tinker::tinkerSolver);
        timer(5853, 5430, Tinker::tinkerSolver);
        timer(1047, 698, Tinker::tinkerSolver);
        System.out.println();
        timer(5, 3, Tinker::tinkerSolverEfficient);
        timer(6, 8, Tinker::tinkerSolverEfficient);
        timer(5, 1, Tinker::tinkerSolverEfficient);
        timer(5853, 5430, Tinker::tinkerSolverEfficient);
        timer(1047, 698, Tinker::tinkerSolverEfficient);
    }
}
