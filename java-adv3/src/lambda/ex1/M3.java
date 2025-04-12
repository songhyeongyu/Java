package lambda.ex1;


import lambda.Procedure;

import java.util.Arrays;

public class M3 {
    public static void measure(Procedure p) {
        long startNs = System.nanoTime();
        p.run(); // 바뀌는 로직 실행 (익명 클래스 or 람다로 전달)
        long endNs = System.nanoTime();
        System.out.println("실행 시간: " + (endNs - startNs) + "ns");
    }


    public static void main(String[] args) {
        measure(() -> {
        int N = 100;
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += i;
        }
        System.out.println("[1부터 " + N + "까지 합] 결과: " + sum);
        });

        measure(() -> {
            int[] arr = { 4, 3, 2, 1 };
            System.out.println("원본 배열: " + Arrays.toString(arr)); Arrays.sort(arr);
            System.out.println("[배열 정렬] 결과: " + Arrays.toString(arr));
        });

    }
}
