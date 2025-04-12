package lambda.ex3;

import java.util.ArrayList;
import java.util.List;
public class MapExample {
    public static List<String> map(List<String> list, StringFunction function) {
        List<String> stringList = new ArrayList<>();

        for (String string : list) {
            stringList.add(function.apply(string));
        }
        return stringList;
    }

    public static void main(String[] args) {
        List<String> stringList = List.of("hello", "java", "lambda");
        System.out.println("원본 리스트: " + stringList);

        List<String> stringList1 = map(stringList, (str) -> str.toUpperCase());
        System.out.println(stringList1);

        List<String> stringList2 = map(stringList, (str) -> "*".repeat(3) + str + "*".repeat(3));
        System.out.println(stringList2);

    }
}
