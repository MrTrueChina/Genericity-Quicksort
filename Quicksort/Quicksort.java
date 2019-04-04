package extra.tools;

import java.util.function.BiFunction;

/**
 * ����������
 * 
 * @author Mr.true.China
 *
 */
public class Quicksort {
    /**
     * ��һ��������ָ���������׼��������
     * 
     * @param originArray Ҫ���������
     * @param isLeft      �����׼���밴�����±�׼�༭����������Ԫ�أ������һ��Ԫ�رȵڶ���Ԫ�ظ���ǰ���򷵻�true�����򷵻�false
     * @return ����������
     * @throws NullPointerException �����������������׼Ϊnullʱ�׳����쳣
     */
    public static <E> E[] sort(final E[] array, final BiFunction<E, E, Boolean> isLeft) throws NullPointerException {
        if (array == null)
            throw new NullPointerException("Ҫ��������鲻��Ϊnull");
        if (isLeft == null)
            throw new NullPointerException("�����׼����Ϊnull");

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
