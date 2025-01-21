package day9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class CallableTaskA implements Callable<String> {

    String message;

    public CallableTaskA(String message) {
        this.message = message;
    }

    @Override
    public String call() throws Exception {
        return message;
    }
}

public class CallableTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> tasks = new ArrayList<>();
        tasks.add("Hello year 2A");
        tasks.add("Hello year 2B");
        tasks.add("Hello RCA");

        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (String task : tasks) {
            Future<String> msg = executor.submit(new CallableTaskA(task));
            String message = msg.get();
            System.out.println("Message is: " + message);
        }

        executor.shutdown();

        System.out.println("Hello main");
    }
}
