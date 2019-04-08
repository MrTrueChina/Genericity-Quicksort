package quicksort.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import quicksort.Quicksort;

/**
 * ���ڲ���Quicksort���JUnit������
 * 
 * @author Mr.true.China
 */
class Quicksort_Test {
    @Test
    public void sort_NoDuplicate() {
        Integer[] origin = new Integer[] { 1, 5, 6, 9, 8, 7, 4, 3, 2 };
        Integer[] expected = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        BiFunction<Integer, Integer, Boolean> left = (a, b) -> a < b;
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_Duplicate() {
        Integer[] origin = new Integer[] { 1, 5, 6, 9, 8, 7, 6, 4, 3, 2, 2 };
        Integer[] expected = new Integer[] { 1, 2, 2, 3, 4, 5, 6, 6, 7, 8, 9 };
        BiFunction<Integer, Integer, Boolean> left = (a, b) -> a < b;
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_AllDuplicate() {
        Integer[] origin = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        Integer[] expected = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        BiFunction<Integer, Integer, Boolean> left = (a, b) -> a < b;
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_Sorted() {
        Integer[] origin = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Integer[] expected = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        BiFunction<Integer, Integer, Boolean> left = (a, b) -> a < b;
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_Reverse() {
        Integer[] origin = new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        Integer[] expected = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        BiFunction<Integer, Integer, Boolean> left = (a, b) -> a < b;
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_Two() {
        Integer[] origin = new Integer[] { 2, 1 };
        Integer[] expected = new Integer[] { 1, 2 };
        BiFunction<Integer, Integer, Boolean> left = (a, b) -> a < b;
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_void() {
        Integer[] origin = new Integer[0];
        Integer[] expected = new Integer[0];
        BiFunction<Integer, Integer, Boolean> left = (a, b) -> a < b;
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_ArrayIsNull() {
        try {
            BiFunction<Integer, Integer, Boolean> isLeft = (a, b) -> a < b;
            Quicksort.sort(null, isLeft);
            fail();
        } catch (NullPointerException e) {
            if (e.getMessage() != "Ҫ��������鲻��Ϊnull")
                fail();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void sort_IsLeftIsNull() {
        try {
            Quicksort.sort(new Integer[0], null);
            fail();
        } catch (NullPointerException e) {
            if (e.getMessage() != "�����׼����Ϊnull")
                fail();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void sort_Object_MainID() {
        Person[] persons = new Person[] { new Person(1, 5, "һ��-1-5"), new Person(2, 3, "����-2-3"),
                new Person(3, 6, "����-3-6"), new Person(4, 9, "�ĺ�-4-9"), new Person(5, 2, "���-5-2"),
                new Person(6, 7, "����-6-7"), new Person(7, 1, "�ߺ�-7-1"), new Person(8, 4, "�˺�-8-4"),
                new Person(9, 8, "�ź�-9-8") };
        Person[] origin = new Person[] { persons[2], persons[5], persons[6], persons[4], persons[0], persons[7],
                persons[8], persons[3], persons[1] };
        Person[] expected = new Person[] { persons[0], persons[1], persons[2], persons[3], persons[4], persons[5],
                persons[6], persons[7], persons[8] };
        BiFunction<Person, Person, Boolean> left = (a, b) -> a.mainId() < b.mainId();
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_Object_SecondID() {
        Person[] persons = new Person[] { new Person(1, 5, "һ��-1-5"), new Person(2, 3, "����-2-3"),
                new Person(3, 6, "����-3-6"), new Person(4, 9, "�ĺ�-4-9"), new Person(5, 2, "���-5-2"),
                new Person(6, 7, "����-6-7"), new Person(7, 1, "�ߺ�-7-1"), new Person(8, 4, "�˺�-8-4"),
                new Person(9, 8, "�ź�-9-8") };
        Person[] origin = new Person[] { persons[2], persons[5], persons[6], persons[4], persons[0], persons[7],
                persons[8], persons[3], persons[1] };
        Person[] expected = new Person[] { persons[6], persons[4], persons[1], persons[7], persons[0], persons[2],
                persons[5], persons[8], persons[3] };
        BiFunction<Person, Person, Boolean> left = (a, b) -> a.secondId() < b.secondId();
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    @Test
    public void sort_Object_SuperAndChild() {
        Person[] persons = new Person[] { new Person(1, 5, "һ��-1-5"), new Student(2, 3, 87, "����-2-3"),
                new Person(3, 6, "����-3-6"), new Student(4, 9, 36, "�ĺ�-4-9"), new Person(5, 2, "���-5-2"),
                new Student(6, 7, 25, "����-6-7"), new Student(7, 1, 99, "�ߺ�-7-1"), new Person(8, 4, "�˺�-8-4"),
                new Person(9, 8, "�ź�-9-8") };
        Person[] origin = new Person[] { persons[2], persons[5], persons[6], persons[4], persons[0], persons[7],
                persons[8], persons[3], persons[1] };
        Person[] expected = new Person[] { persons[0], persons[1], persons[2], persons[3], persons[4], persons[5],
                persons[6], persons[7], persons[8] };
        BiFunction<Person, Person, Boolean> left = (a, b) -> a.mainId() < b.mainId();
        assertArrayEquals(expected, Quicksort.sort(origin, left));
    }

    /**
     * ���ڲ����Զ����������������Զ����࣬�����������ж�˳���int��һ���޹ص�String
     * 
     * @author Mr.true.China
     *
     */
    class Person {
        int _mainId;
        int _secondId;
        String _name;

        public int mainId() {
            return _mainId;
        }

        public int secondId() {
            return _secondId;
        }

        public String name() {
            return _name;
        }

        public Person(int mainId, int secondId, String name) {
            _mainId = mainId;
            _secondId = secondId;
            _name = name;
        }
    }

    /**
     * ���ڲ��Զ�̬��������࣬������һ��int���ڶ�̬���������int����Ϊ�����׼
     * 
     * @author Mr.true.China
     *
     */
    class Student extends Person {
        int _studentId;

        public int studentId() {
            return _studentId;
        }

        public Student(int mainId, int secondId, int studentId, String name) {
            super(mainId, secondId, name);
            _studentId = studentId;
        }
    }
}
