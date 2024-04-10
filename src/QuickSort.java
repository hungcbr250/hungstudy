import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        System.out.println("Unsorted Array: " + Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Phân chia mảng và nhận chỉ số pivot
            int pivotIndex = partition(arr, low, high);

            // Đệ quy sắp xếp các phần từ trước và sau pivot
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // Chọn pivot là phần tử cuối cùng của mảng
        int pivot = arr[high];

        // Chỉ số của phần tử nhỏ hơn pivot
        int i = low - 1;

        // Duyệt qua mảng và đưa các phần tử nhỏ hơn pivot về đầu mảng
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Hoán đổi arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Hoán đổi pivot với phần tử sau chỉ số i
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Trả về chỉ số pivot
        return i + 1;
    }
}
