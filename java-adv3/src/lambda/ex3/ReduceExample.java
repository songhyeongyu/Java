package lambda.ex3;

import java.util.List;


public class ReduceExample {

    public static int reduce(List<Integer> list, int initial, MyReducer myReducer) {

        for (Integer integer : list) {
            initial = myReducer.reduce(initial, integer);
        }
        return initial;
    }

    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4);

        int reduce = reduce(integerList, 0, (a, b) -> a + b);
        System.out.println(reduce);

        int reduce1 = reduce(integerList, 1, (a, b) -> a * b);
        System.out.println(reduce1);
    }
}
