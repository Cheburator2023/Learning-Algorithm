import java.util.Arrays;

public class BaseJava4 {
    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
        Character[] nums2 = {'a', 'c', 'b', 'v', 'a'};
        String[] nums3 = {"ABC", "abc"};
        int[] nums4 = {1, 2, 2, 3, 4, 4, 5, 6, 7};
        int[] nums5 = {3, 1, 4, 1, 5, 9, 2, 6};
        int[] nums7 = {};
        int[] nums6 = {123, 234, -823, 456, -875, 789, -327, 234, 123, 345, -0, 567, 823};
//        System.out.println(binarySearch(nums1,4));
//        System.out.println(Arrays.toString(firstAndLast(nums4,7)));
//        System.out.println(findTarget(nums1, 0));
        quickSort(nums5);
        System.out.println(Arrays.toString(nums5));
    }

    // Бинарный поиск (классический)
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] == target) {
            return 0;
        }
        if (arr[arr.length - 1] == target) {
            return arr.length - 1;
        }
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Поиск первой/последней позиции элемента в отсортированном массиве
    public static int[] firstAndLast(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[]{-1, -1};
        }
        if (arr.length == 1) {
            return arr[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }
        int[] result = new int[2];
        result[0] = first(arr, target);
        result[1] = last(arr, target);

        return result;
    }

    private static int last(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private static int first(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // Поиск в отсортированном массиве со сдвигом
    public static int findTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1, result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) { // Левая половина отсортирована
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // Ищем в левой половине
                } else {
                    left = mid + 1;  // Ищем в правой половине
                }
            } else { // Правая половина отсортирована
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;  // Ищем в правой половине
                } else {
                    right = mid - 1; // Ищем в левой половине
                }
            }
        }
        return -1;
    }

    // Сортировка слиянием (Merge Sort)
    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;// Проверяем крайние случаи на пустой массив
        }
        int[] temp = new int[nums.length]; // создаем временный массив
        mergeSort(nums,temp, 0, nums.length - 1); // запускаем рекурсию
    }
    private static void mergeSort(int[] nums, int[] temp, int left, int right) {
        if (left < right) { // разделяем массив на подмассивы, до базового случая в один элемент в подмассиве
            int mid = (left + right) / 2; // находим середину в подмассиве

            mergeSort(nums, temp, left, mid); // рекурсивно сортируем левую половину
            mergeSort(nums, temp, mid + 1, right); // рекурсивно сортиртируем правую половину
            merge(nums,temp,left,mid,right); // рекурсивно объединяем подмассивы
        }
    }

    private static void merge(int[] nums, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) { // копируем массив во временный
            temp[i] = nums[i];
        }
        int i = left, j = mid + 1, k = left; // указатели для левой, правой половины и результирующего массива
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            nums[k] = temp[i];
            i++;
            k++;
        }
    }

    // Основной метод для сортировки
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    // Рекурсивный метод сортировки
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Разделяем массив и получаем индекс опорного элемента
            int pivotIndex = partition(arr, low, high);

            // Рекурсивно сортируем левую и правую части
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Метод для разделения массива
    private static int partition(int[] arr, int low, int high) {
        // Выбираем опорный элемент (последний элемент)
        int pivot = arr[high];

        // Индекс для элемента, который меньше pivot
        int i = low - 1;

        // Проходим по всем элементам от low до high-1
        for (int j = low; j < high; j++) {
            // Если текущий элемент меньше или равен pivot
            if (arr[j] <= pivot) {
                i++;
                // Меняем местами arr[i] и arr[j]
                swap(arr, i, j);
            }
        }

        // Помещаем pivot на правильную позицию
        swap(arr, i + 1, high);

        return i + 1; // Возвращаем индекс pivot
    }

    // Вспомогательный метод для обмена элементов
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
