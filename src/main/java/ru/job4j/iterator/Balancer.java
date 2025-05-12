package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int column = 0;
        while (source.hasNext()) {
            nodes.get(column).add(source.next());
            column = (column + 1) % nodes.size();
        }
    }
}