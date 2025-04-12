package lambda.lambda4;

public class RunnableMain {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("h2llo");
            }
        };

        runnable.run();

        Runnable runnable2 = () -> System.out.println("heool");
        runnable2.run();

    }
}
