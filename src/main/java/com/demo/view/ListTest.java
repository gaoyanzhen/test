package com.demo.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2021-11-18
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 4, 9);
        List<List<Integer>> subSets = new ListTest().subSets(list);
        subSets.forEach(System.out::println);
    }

    public List<List<Integer>> subSets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> reset = list.subList(1, list.size());
        List<List<Integer>> sub1 = subSets(reset);
        List<List<Integer>> sub2 = insertAll(first, sub1);
        return concat(sub1, sub2);
    }

    public List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
        List<List<Integer>> all = new ArrayList<>(a);
        all.addAll(b);
        return all;
    }

    public List<List<Integer>> insertAll(Integer a, List<List<Integer>> list) {
        List<List<Integer>> result = new ArrayList<>();
        list.forEach(item -> {
            List<Integer> temp = new ArrayList<>();
            temp.add(a);
            temp.addAll(item);
            result.add(temp);
        });
        return result;
    }
}
