# 快速排序的实现和解释以及泛型支持
[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

## 如果你只是想找一个快速的按指定标准排序的方法：
JAVA中自带了 Arrays.sort 方法和 Collections.sort 方法，这两个方法可以按照指定标准对数组和集合进行排序，并且使用的是稳定的归并排序。

<br/>

## 文件夹内容：
| 文件夹 | 内容 |
| ------------- |:-------------| 
| src/quicksort | 快速排序的所有代码 |
| src/quicksort/IntArrayQuickSort.java | int数组的快速排序的代码 |
| src/quicksort/Quicksort.java.java | 支持泛型的快速排序的代码 |
| src/quicksort/test/IntArrayQuickSort_Test.java | IntArrayQuickSort的JUnit测试代码 |
| src/quicksort/test/IntArrayQuickSort_Test.java | Quicksort的JUnit测试代码 |

<br/>

## 原理解释：

快排比常见的简单的排序算法拥有更多的临时变量、更多的行进方向、更多的递归，这使得快排用语言表达非常困难，这也是为什么解释快排的文章这么多但同样让人看不懂。<br/>
这次我要用图文的方式来解释快排：

<br/>
<br/>

我们假设有一个int数组
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>3</td>
				<td>7</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>1</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
		</table>
		<br/>
		首先确认排序范围并在两端创建两个索引，这是第一轮，范围是整个数组
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>3</td>
				<td>7</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>1</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
			</tr>
		</table>
		现在的数据有：数组（int[]）、左索引（int）、右索引（int） 之后选一个基准值，选左右索引之一的位置的数字会方便后续操作，这里选择左索引的数字
		<br/>
		<br/>
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>3</td>
				<td>7</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>1</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
			</tr>
		</table>
		现在的数据有：数组（int[]）、左索引（int，0）、右索引（int，8）、基准值（int，3） 因为左索引指向的3已经被保存在了基准值里，因此数组可看成这样：
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>3（可覆盖）</td>
				<td>7</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>1</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
			</tr>
		</table>
		<br/>
		<br/>
		之后让基准值对面的索引向基准值这边的索引移动，并寻找一个比基准值小的值，也就是右索引向左走，找一个小于3的值
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>3（可覆盖）</td>
				<td>7</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>1</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		找到了1，之后把这个值覆盖到左索引的位置，覆盖后的数组是这样的
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>↙</td>
				<td>←</td>
				<td>←</td>
				<td>←</td>
				<td>←</td>
				<td>↖</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>7</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>1（可覆盖）</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		之后左索引向右走并找一个比基准值小的值，找到了7
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>7</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>1（可覆盖）</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		把7覆盖到右索引的位置
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td></td>
				<td>↗</td>
				<td>→</td>
				<td>→</td>
				<td>→</td>
				<td>↘</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>7（可覆盖）</td>
				<td>5</td>
				<td>2</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		之后又是右索引向左找一个小于基准的值并覆盖到左下标的位置
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td></td>
				<td>↙</td>
				<td>←</td>
				<td>↖</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>5</td>
				<td>2（可覆盖）</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td>↑<br/>左索引</td>
				<td></td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		之后又轮到了左索引
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td></td>
				<td></td>
				<td>↗</td>
				<td>↘</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>5（可覆盖）</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		之后轮到了右索引，但右索引还没找到小于基准的值就先和左索引相遇了
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>5（可覆盖）</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td>↑<br/>左索引，右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		此时这一轮的换位就结束了，把之前存下来的基准值覆盖到这个位置
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td>↑<br/>左索引，右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		这一轮的基准值就到达了正确的位置
		<br/>
		<br/>
		<br/> 一轮排序完成后向两边递归。 先看左边
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1（可覆盖）</td>
				<td>2</td>
				<td>3</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引</td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		基准值：1
		<br/>
		<br/> 右索引没找到，刚走一步就遇到了左索引，一轮结束，基准值覆盖进去
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引，右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<br/> 再次向左递归
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1（可覆盖）</td>
				<td>2</td>
				<td>3</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>↑<br/>左索引，右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		基准值：1
		<br/>
		<br/> 这轮只有一个数字，没有换位的意义，递归结束。
		<br/>
		<br/> 对应的，右边的递归也是只有一个数字
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td>↑<br/>左索引，右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<br/> 那么一轮的左侧递归全部完成，再去一轮的右侧递归看看
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>5（可覆盖）</td>
				<td>6</td>
				<td>7</td>
				<td>4</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
			</tr>
		</table>
		基准值：5
		<br/>
		<br/> 换位过程：
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td>↙</td>
				<td>←</td>
				<td>←</td>
				<td>↖</td>
				<td></td>
				<td></td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>6</td>
				<td>7</td>
				<td>4（可覆盖）</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↗</td>
				<td>→</td>
				<td>↘</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>6（可覆盖）</td>
				<td>7</td>
				<td>6</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td></td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
				<td>7</td>
				<td>6</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引，右索引</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<br/> 向左递归只有一个4直接结束，之后是向右递归过程
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
				<td>7（可覆盖）</td>
				<td>6</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td></td>
				<td></td>
				<td>↑<br/>右索引</td>
			</tr>
		</table>
		基准值：7
		<br/>
		<br/>
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↙</td>
				<td>↖</td>
				<td></td>
				<td></td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
				<td>6</td>
				<td>7（可覆盖）</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td>↑<br/>右索引</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>9</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引，右索引</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<br/>
		<br/> 向左又是只有6一个数字，再看向右递归过程：
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>9（可覆盖）</td>
				<td>8</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td>↑<br/>右索引</td>
			</tr>
		</table>
		基准值：9
		<br/>
		<br/> 向左又是只有6一个数字，再看向右递归过程：
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↙</td>
				<td>↖</td>
			</tr>
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>8</td>
				<td>8（可覆盖）</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引</td>
				<td>↑<br/>右索引</td>
			</tr>
		</table>
		这一步很重要，右索引直接站在比基准值小的位置，索引他直接覆盖了值而没有向左走，所以才没有在第一步就和左索引相遇
		<br/>
		<br/>
		<table border="1" cellpadding="5" cellspacing="0">
			<tr align="center">
				<td>1</td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
				<td>6</td>
				<td>7</td>
				<td>8</td>
				<td>9</td>
			</tr>
			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>↑<br/>左索引，右索引</td>
			</tr>
		</table>
		<br/>
		之后向左递归只有一个数字，向右递归一个数字也没有，所有递归结束，排序结束。
        
<br/>
<br/>
        
看完过程有没有理解快排的原理？<br/>
我自己都没怎么看明白，我建议你到 Quicksore 文件夹里看一看 IntArrayQuicksore ，这样应该会比较好理解。
