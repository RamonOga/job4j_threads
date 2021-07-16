package ru.job4j.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RunAsync {
    /**
     *
     * Допустим вы очень занятой человек и часто берете работу на дом.
     * Но дома тоже есть свои дела. Например, сходить выбросить мусор.
     * Вам некогда этим заниматься, но у вас есть сын, который может это сделать.
     * Вы сами работаете, а ему поручаете это дело.
     * Это проявление асинхронности, т.к. вы сами работаете, а тем временем ваш сын выбрасывает мусор.
     * Сымитируем эту ситуацию.
     */
    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            System.out.println("Вы: Я работаю : " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    public static CompletableFuture<Void> goToTrash() {
        return CompletableFuture.runAsync(
                () -> {
                    System.out.println("Сын: Мам/Пам, я пошел выносить мусор : " + Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я вернулся! : "  + Thread.currentThread().getName());
                }
        );
    }

    /**
     *
     * Возьмем теперь другой пример. Вы поручили сыну купить молоко. Вот как это будет выглядеть.
     */
    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Сын: Мам/Пам, я пошел в магазин : " + Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я купил " + product + " : " + Thread.currentThread().getName());
                    return product;
                }
        );
    }

    public static void runAsyncExample() throws Exception {
        CompletableFuture<Void> gtt = goToTrash();
        iWork();
    }

    public static void supplyAsyncExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        iWork();
        System.out.println("Куплено: " + bm.get() + " : " + Thread.currentThread().getName());
    }



    /**
     *
     * Пример thenRun()
     * Этот метод ничего не возвращает, а позволяет выполнить задачу типа Runnable после выполнения асинхронной задачи.
     * Допишем первый пример, чтобы сын шел мыть руки
     */
    public static void thenRunExample() throws Exception {
        CompletableFuture<Void> gtt = goToTrash();
        gtt.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("Сын: я мою руки : " + Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            System.out.println("Сын: Я помыл руки : " + Thread.currentThread().getName());
        });
        iWork();
    }

    /**
     *
     * Пример thenAccept()
     *
     * Допустим вы не хотите запускать отдельную задачу, а хотите, чтобы просто было выполнено какое-то действие.
     * В отличие от thenRun(), этот метод имеет доступ к результату CompletableFuture.
     * Допишем второй пример, чтобы сын убрал молоко в холодильник.
     */
    public static void thenAcceptExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        bm.thenAccept((product) -> System.out.println("Сын: Я убрал " + product + " в холодильник : " + Thread.currentThread().getName()));
        iWork();
        System.out.println("Куплено: " + bm.get() + " : " + Thread.currentThread().getName());
    }
    /**
     * Пример thenApply()
     *
     * Этот метод принимает Function. Также имеет доступ к результату.
     * Как раз благодаря этому, мы можем произвести преобразование полученного результата.
     * Допишем второй пример. Например, вы хотите, чтобы после того, как сын принес молоко, налил вам его в кружку.
     * Однако результат преобразования станет доступным только при вызове get()
     */
    public static void thenApplyExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко")
                .thenApply((product) -> "Сын: я налил тебе в кружку " + product + ". Держи. : " + Thread.currentThread().getName());
        iWork();
        System.out.println(bm.get());
    }

    /**
     *
     * thenCompose()
     * Данный метод используется, если действия зависимы.
     * Т.е. сначала должно выполниться одно, а только потом другое.
     * Например, вам принципиально, чтобы сын сначала выбросил мусор, а только потом сходил за молоком.
     * В ситуации можно записать так
     */

    public static void thenComposeExample() throws Exception {
        CompletableFuture<Void> result = buyProduct("Молоко").thenCompose(a -> goToTrash());
        iWork();
    }

    /**
     *
     * thenCombine()
     * Данный метод используется, если действия могут быть выполнены независимо друг от друга.
     * Причем в качестве второго аргумента, нужно передавать BiFunction – функцию, которая преобразует результаты двух задач во что-то одно.
     * Например, первого сына вы посылаете выбросить мусор, а второго сходить за молоком. В этой ситуации можно поступить так
     */

    public static void thenCombineExample() throws Exception {
        CompletableFuture<String> result = buyProduct("Молоко")
                .thenCombine(buyProduct("Хлеб"), (r1, r2) -> "Куплены " + r1 + " и " + r2);
        iWork();
        System.out.println(result.get());
    }

    /**
     *
     * allOf()
     * Это метод возвращает ComputableFuture<Void>, при этом обеспечивает выполнение всех задач.
     * Например, вы зовете всех членов семью к столу. Надо дождаться пока все помоют руки
     */

    public static CompletableFuture<Void> washHands(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ", моет руки");
        });
    }

    public static void allOfExample() throws Exception {
        CompletableFuture<Void> all = CompletableFuture.allOf(
                washHands("Папа"), washHands("Мама"),
                washHands("Ваня"), washHands("Боря")
        );
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     *
     * anyOf()
     * Этот метод возвращает ComputableFuture<Object>. Результатом будет первая выполненная задача.
     * На том же примере мы можем, например, узнать, кто сейчас моет руки. Результаты запуск от запуска будут различаться.
     */

    public static CompletableFuture<String> whoWashHands(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + ", моет руки";
        });
    }

    public static void anyOfExample() throws Exception {
        CompletableFuture<Object> first = CompletableFuture.anyOf(
                whoWashHands("Папа"), whoWashHands("Мама"),
                whoWashHands("Ваня"), whoWashHands("Боря")
        );
        System.out.println("Кто сейчас моет руки?");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(first.get());
    }

    public static void main(String[] args) throws Exception {
        //runAsyncExample();
        //supplyAsyncExample();
        //thenRunExample();
        //thenAcceptExample();
        thenApplyExample();
        //thenComposeExample();
        //thenCombineExample();
        //allOfExample();
        //anyOfExample();
    }
}
