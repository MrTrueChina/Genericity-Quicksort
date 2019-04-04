public class IntArrayQuickSort {
    /**
     * 对int数组进行从小到大排序
     * 
     * @param ints 需要排序的数组
     * @return 排序后的数组
     * @throws NullPointerException 当传入null时抛出此异常
     */
    public static int[] sort(int[] ints) throws NullPointerException {
        if (ints != null) {
            sort(ints, 0, ints.length - 1);
            return ints;
        } else {
            throw new NullPointerException("排序数组不能为空");
        }
    }

    /*
     * 快速排序的原理是将一个值作为标准，其他所有值按照左小右大的方式放到这个值的两边，之后在两边再次排序，直到排序结束。
     * 这里的最大问题是排序的是一个数组，它并不能像List一样简单的调整位置，所以快排使用先保存一个数，之后左右依次利用这个空位做覆盖，
     * 最后再把这个数放进最后的空位，完成换位。 详细可以分为以下几点：
     * 第一步：选择一个值作为基准，这个值负责作为中间的那个值，为了方便后续调换位置，这个值一般选最左边或最右边的。这里我选最左边。
     * 第二步：因为基准值是左索引的值，所以右索引向左移动并找一个比基准值低的值，覆盖到左索引位置。
     * 
     * 
     * 详细步骤： 初始数组：3,5,2,4,1
     * 
     * 创建左右两个索引，分别在排序范围两端，因为是整组排序，所以在数组两端，创建后是这样的： 数组：[3,5,2,4,1] 左索引：0 右索引：4
     * 
     * 选取左右索引之一指向的数字做标准，这里选择左索引，指向的数字是3 选取后，数组：[3,5,2,4,1] 基准值（在数组外用字段保存）：3 左索引：0
     * 右索引：4 因为3已经保存，数组可以看为这样：[可覆盖,5,2,4,1]
     * 
     * 此时右索引开始向左移动并寻找一个比基准值小的值，找到了下标4的数字1，而此时左索引所在位置是可覆盖的，把这个值覆盖过去。（
     * 这就是为什么基准值要在左右索引找一个，因为方便覆盖） 覆盖后的数据： 数组：[1,5,2,4,1] 基准值：3 左索引：0 右索引：4
     * 因为本来在下标4的数字1被覆盖到了下标0的位置，此时下标4变为可覆盖：[1,5,2,4,可覆盖]
     * 
     * 之后左索引开始向右移动并找一个比基准值大的值，找到了下标1的数字5，此时右索引所在位置是可覆盖的，把这个值覆盖过去。 覆盖后的数据：
     * 数组：[1,5,2,4,5] 基准值：3 左索引：1 右索引：4 相当于： 数组：[1,可覆盖,2,4,5] 基准值：3 左索引：1 右索引：4
     * 
     * 右索引再次开始向左移动并寻找比基准值小的值，找到下标2的数字2，左索引所在位置可覆盖，覆盖过去。 覆盖后的数据： 数组：[1,2,2,4,5] 基准值：3
     * 左索引：1 右索引：2 相当于： 数组：[1,2,可覆盖,4,5] 基准值：3 左索引：1 右索引：2
     * 
     * 做所以再次向右移动并寻找比基准值大的值。 左索引还没有找到这个值，但已经和右索引在下标2的位置相遇了。
     * 此时左索引把他经过的比基准大的值都覆盖到了右索引经过的地方，右索引把它经过的所有比基准小的值都覆盖到了左索引经过的地方，两个索引相遇的位置是可覆盖的。
     * 这就说明基准值可以放在这个位置，把基准值放下去。 数据： 数组：[1,2,3,4,5] 基准值：3 左索引：2 右索引：2
     * 
     * 
     * 一轮排序完成后可以保证基准值位置正确、基准值左边的值都小于基准值、右边的值都大于基准值，但不能保证两边的排序是正确的，因此需要在两边递归。
     * 因为基准值的位置已经可以保证正确，而基准值的位置正是两个索引指向的位置，所以递归时应该排除这个数字的位置。 右侧递归：左索引开始位置 =
     * 当前轮排序完成后的索引位置+1 = 2+1 = 3，右索引开始位置 = 当前轮排序范围的最右端 = 4。 左侧底柜：左索引开始位置 =
     * 当前轮排序范围的最左端 = 0，右索引开始位置 = 当前轮排序完成后的索引位置-1 = 2-1 = 1。
     */
    static void sort(final int[] ints, final int startLeftIndex, final int startRightIndex) {
        if (startLeftIndex >= startRightIndex) // 右索引比左索引大的时候说明有至少两个可排序的元素，否则说明剩余元素不超过1个，已经没有排序的需要了，排序也就到了完成的时候了
            return;

        int leftIndex = startLeftIndex; // 为了不改变参数，用一个新的int来做左下标
        int rightIndex = startRightIndex; // 由下标同理
        int standard = ints[leftIndex]; // 选取最左边的数字作为基准，这个数字会被覆盖掉，需要先暂存下来，不能用0，因为不保证是整个数组排序，有可能是递归的部分排序

        while (leftIndex < rightIndex) /* 循环到左右索引相遇，一轮排序便完成了 */ {
            while (leftIndex < rightIndex) {
                if (ints[rightIndex] < standard) {
                    ints[leftIndex] = ints[rightIndex];
                    break; // 覆盖过去后一定要中断循环，否则下标会走过头，这样右侧就可能会留下没有被覆盖的比基准值小的元素
                }
                rightIndex--; // 每次循环向左移动一个下标
            }

            while (rightIndex > leftIndex) {
                if (ints[leftIndex] > standard) {
                    ints[rightIndex] = ints[leftIndex];
                    break;
                }
                leftIndex++;
            }
        }
        ints[leftIndex] = standard;

        sort(ints, startLeftIndex, leftIndex - 1);
        sort(ints, leftIndex + 1, startRightIndex);
    }
}
