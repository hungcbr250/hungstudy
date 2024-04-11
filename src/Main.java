import java.util.Random;

public class Main {
    int count = 0;

    public void deQuy() {
        if (count < 2) {
            System.out.println("Hello");
            count++;
            deQuy();
        }
    }

    public static void main(String[] args) {
//     Main main=new Main();
//     main.deQuy();
        String arr[] = {"ab125 new", "ab160 old"};             // 2 3
        System.out.println("tu van cho toi nen mua xe nao");
        Random random = new Random();
        int xe = random.nextInt(arr.length);
        System.out.println("Ban nen mua xe " + arr[xe]);
    }
}