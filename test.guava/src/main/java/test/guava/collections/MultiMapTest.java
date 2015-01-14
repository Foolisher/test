package test.guava.collections;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wanggen on 14-7-16.
 * @Desc:
 */
public class MultiMapTest {

    public static void main(String[] args) {


        List students = Lists.newArrayList();
        students.add(new Student("A", 10));
        students.add(new Student("B", 20));
        students.add(new Student("C", 30));
        students.add(new Student("D", 50));
        students.add(new Student("E", 60));
        students.add(new Student("F", 70));
        students.add(new Student("G", 80));
        students.add(new Student("H", 90));
        students.add(new Student("I", 100));
        ImmutableListMultimap<String, test.guava.collections.Student> index = Multimaps.index(students, new Function<Student, String>() {
            @Override
            public String apply(Student input) {
                if(Range.closedOpen(0, 59).contains(input.getScore()))
                    return "0-59";
                if(Range.closedOpen(60, 79).contains(input.getScore()))
                    return "60-79";
                if(Range.closedOpen(80, 100).contains(input.getScore()))
                    return "80-100";
                return "";
            }
        });
        for (Map.Entry<String, test.guava.collections.Student> stringStudentEntry : index.entries()) {
            System.out.println(stringStudentEntry.getKey()+" = "+stringStudentEntry.getValue());
        }

    }

    @Data
    @AllArgsConstructor
    static class Student {
        private String name;
        private Integer score;
    }
}

