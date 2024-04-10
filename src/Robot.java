import java.awt.*;
import java.awt.event.KeyEvent;

public class Robot {
    private static volatile boolean stopThread = false; // Biến điều kiện dừng

    public static void main(String[] args) {
        try {
            // Tạo một đối tượng Robot để điều khiển bàn phím
            java.awt.Robot robot = new java.awt.Robot();

            // Đợi 3 giây trước khi bắt đầu
            Thread.sleep(6000);

            // Khởi tạo và chạy một luồng để gửi tin nhắn
            Thread sendingThread = new Thread(() -> {
                try {
                    sendMessages(robot); // Gửi tin nhắn
                } catch (AWTException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            sendingThread.start(); // Bắt đầu luồng

            // Đợi 10 giây, sau đó dừng luồng
            Thread.sleep(10000);
            stopThread = true; // Đặt biến dừng
            sendingThread.interrupt(); // Ngắt luồng
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    // Phương thức để gửi các tin nhắn
    private static void sendMessages(java.awt.Robot robot) throws AWTException, InterruptedException {
        while (!stopThread) { // Lặp vô hạn cho đến khi biến dừng được đặt thành true
            // Gửi các tin nhắn
            sendMessage(robot, "nhieu lam");
            Thread.sleep(500); // Chờ 0.5 giây trước khi gửi tin nhắn tiếp theo
        }
    }

    // Phương thức để gửi một tin nhắn sử dụng Robot
    private static void sendMessage(java.awt.Robot robot, String message) {
        // Ghi văn bản vào cửa sổ hiện tại
        for (char c : message.toCharArray()) {
            typeCharacter(robot, c);
        }
        // Gửi phím Enter
        pressEnter(robot);
    }

    // Phương thức để ghi một ký tự bằng Robot
    private static void typeCharacter(java.awt.Robot robot, char character) {
        // Chuyển đổi ký tự sang mã ASCII
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(character);
        // Ấn phím tương ứng
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    // Phương thức để gửi phím Enter bằng Robot
    private static void pressEnter(java.awt.Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
