import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

class IntArrayTools_Test {
    @Test
    void quickSort_Normal() {
        int[] origin = new int[] { 1, 9, 8, 5, 6, 3, 2, 4, 7, 0, -1, -8, -5, -6, -9, -3, -2, -4, -7 };
        int[] expected = new int[] { -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertArrayEquals(expected, IntArrayQuickSort.sort(origin));
    }

    @Test
    void quickSort_Sorted() {
        int[] origin = new int[] { -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] expected = new int[] { -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertArrayEquals(expected, IntArrayQuickSort.sort(origin));
    }

    @Test
    void quickSort_Reverse() {
        int[] origin = new int[] { 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5 };
        int[] expected = new int[] { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 };
        assertArrayEquals(expected, IntArrayQuickSort.sort(origin));
    }

    @Test
    void quickSort_Single() {
        int[] origin = new int[] { 1 };
        int[] expected = new int[] { 1 };
        assertArrayEquals(expected, IntArrayQuickSort.sort(origin));
    }

    @Test
    void quickSort_Repeat() {
        int[] origin = new int[] { 1, 5, 6, 8, 9, 8, 7, 3, 2, 5, 3, 7, 4 };
        int[] expected = new int[] { 1, 2, 3, 3, 4, 5, 5, 6, 7, 7, 8, 8, 9 };
        assertArrayEquals(expected, IntArrayQuickSort.sort(origin));
    }

    @Test
    void quickSort_Void() {
        int[] origin = new int[0];
        int[] expected = new int[0];
        assertArrayEquals(expected, IntArrayQuickSort.sort(origin));
    }

    @Test
    void quickSort_Null() {
        try {
            IntArrayQuickSort.sort(null);
            fail(); // 没接收到异常说明出bug了
        } catch (NullPointerException e) {
            if (e.getMessage() != "排序数组不能为空")
                fail();
        } catch (Exception e) {
            fail(); // 如果接收到其他异常则说明出bug了
        }
    }
}
