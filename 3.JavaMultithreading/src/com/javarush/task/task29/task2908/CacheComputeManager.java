package com.javarush.task.task29.task2908;

import java.util.concurrent.*;

/* Argument and Value are generic types*/
public class CacheComputeManager<Argument, Value> implements Computable<Argument, Value> {
    private final ConcurrentHashMap<Argument, Future<Value>> cache = new ConcurrentHashMap<>();
    private final Computable<Argument, Value> computable;

    public CacheComputeManager(Computable<Argument, Value> computable) {
        this.computable = computable;
    }

    @Override
    public Value compute(final Argument arg) throws InterruptedException {
        Future<Value> future = cache.get(arg);

        if (future == null) {
            FutureTask<Value> futureTask = createFutureTaskForNewArgumentThatHasToComputeValue(arg);
            cache.putIfAbsent(arg, futureTask);
            future = futureTask;
            futureTask.run();
            System.out.print(arg + " will be cached  ");
        } else {
            System.out.print(arg + " taken from cache  ");
        }

        try {
            return future.get();
        } catch (CancellationException e) {
            cache.remove(arg, future);
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
        return null;
    }

    public FutureTask<Value> createFutureTaskForNewArgumentThatHasToComputeValue(final Argument arg) {

        return new FutureTask<>(new Callable<Value>() {
            @Override
            public Value call() throws Exception {
                return computable.compute(arg);
            }
        });
    }
}
