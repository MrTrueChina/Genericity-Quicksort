package extra.tools;

import java.util.function.BiFunction;

/**
 * 快速排序类
 * 
 * @author Mr.true.China
 *
 */
public class Quicksort {
    /**
     * 对一个数组以指定的排序标准进行排序
     * 
     * @param originArray 要排序的数组
     * @param isLeft      排序标准，请按照以下标准编辑：传入两个元素，如果第一个元素比第二个元素更靠前，则返回true，否则返回false
     * @return 排序后的数组
     * @throws NullPointerException 当传入的数组或排序标准为null时抛出此异常
     */
    public static <E> E[] sort(final E[] array, final BiFunction<E, E, Boolean> isLeft) throws NullPointerException {
        if (array == null)
            throw new NullPointerException("要排序的数组不能为null");
        if (isLeft == null)
            throw new NullPointerException("排序标准不能为null");

        sort(array, 0, array.length - 1, isLeft);
        return array;
    }

    static <E> void sort(final E[] originArray, final int startLeftIndex, final int startRightIndex,
            final BiFunction<E, E, Boolean> isLeft) {
        if (startLeftIndex >= startRightIndex)
            return;

        int leftIndex = startLeftIndex;
        int rightIndex = startRightIndex;
        E standard = originArray[leftIndex];
        while (leftIndex < rightIndex) {
            while (rightIndex > leftIndex) {
                if (isLeft.apply(originArray[rightIndex], standard)) {
                    originArray[leftIndex] = originArray[rightIndex];
                    break;
                }
                rightIndex--;
            }

            while (leftIndex < rightIndex) {
                if (isLeft.apply(standard, originArray[leftIndex])) {
                    originArray[rightIndex] = originArray[leftIndex];
                    break;
                }
                leftIndex++;
            }
        }
        originArray[leftIndex] = standard;

        sort(originArray, startLeftIndex, leftIndex - 1, isLeft);
        sort(originArray, leftIndex + 1, startRightIndex, isLeft);
    }
}
