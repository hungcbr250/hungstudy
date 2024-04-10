public class Main {
    int count = 0;
    public void deQuy(){
        if (count <2) {
            System.out.println("Hello");
            count++;
            deQuy();
        }
    }

    public static void main(String[] args) {
     Main main=new Main();
     main.deQuy();
    }
}