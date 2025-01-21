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

        ExecutorService executor1 = Executors.newFixedThreadPool(1);
        List<Future<String>> list = executor1.invokeAll(List.of(new CallableTaskA("Hello year 2A"), new CallableTaskA("Hello year 2B"), new CallableTaskA("Hello RCA")));
        for (Future<String> future : list) {
            System.out.println(future.get());
        }

        String task = executor1.invokeAny(List.of(new CallableTaskA("Hello year 2A"), new CallableTaskA("Hello year 2B"), new CallableTaskA("Hello RCA")));
        System.out.println("The first task to be executed is: " + task);
        executor1.shutdown();
    }
}
