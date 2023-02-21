package StreamReduce;

import java.util.*;
import java.util.stream.IntStream;

public class StreamReduce {
    public static void main(String[] args) {
        var list = List.of(2,10,5);
        var value = list.stream().reduce((a,b)->a+b);
        System.out.println(value.orElse(0));

        var value2 = list.stream().reduce(0, (a,b)->a+b); // 0(a) + 2(b) = 2, 2(a) + 10(b) = 12, 12(a) + 5(b) = 17
        System.out.println(value2);

        var value3 = list.stream().reduce(10, (a,b)->a+b); // 10(a) + 2(b) = 12, 12(a) + 10(b) = 22, 22(a) + 5(b) = 27
        System.out.println(value3);

        var value4 = list.stream().reduce(0, (a,b)->(a+10) + (b+10)); // (0(a) + 10) + (2(b) + 10)= 22, (22(a) + 10) + (10(b) + 10) = 52, (52(a) + 10) + (5(b) + 10) = 77
        System.out.println(value4);

        var value5 = list.stream().reduce((a,b)->(a+10) + (b+10)); // (2(a) + 10) + (10(b) + 10)= 32, (32(a) + 10) + (5(b) + 10) = 57
        System.out.println(value5);
        System.out.println(value5.orElse(0));

        OptionalInt reduced = IntStream.range(1,4).reduce((a,b)->a+b); // 1 + 2 + 3
        System.out.println(reduced.getAsInt());

        int reducedTwoParams = IntStream.range(1,4).reduce(10, (a,b)-> a+b); // 10 + 1 = 11 + 2 = 13 + 3 = 16
        System.out.println(reducedTwoParams);

        int reducedx = Arrays.asList(1,2,3).parallelStream().reduce(10, (a,b)->a+b, (a,b)->{
            System.out.println("Combiner was called..");
            return a+b; // 3 iterations: (10 + 1) + (10 + 2) + (10 + 3) = 36
        });
        System.out.println(reducedx);

        List<Integer> list1 = Arrays.asList(10,15,20,25,30);

        Optional<Integer> result = list1.stream().reduce((a, b)-> a + b);
        System.out.println(result.get());

        // using method reference
        Optional<Integer> result2 = list1.stream().reduce(Integer::sum);
        System.out.println(result2.get());

        Integer result3 = list1.stream().reduce(0, (a,b)->a+b);
        System.out.println(result3);

        List<String> list2 = Arrays.asList("Marco", "Daisy", "Michael", "Sanya", "Robin");
        Optional<String> result4 = list2.stream().reduce((a,b)->a.length() > b.length() ? a : b);
        System.out.println(result4.get());

        List<Employee> empList = new ArrayList<>();

        empList.add(new Employee(1, "Marco", "marco@gmail.com", 25000));
        empList.add(new Employee(2, "Daisy", "daisy@gmail.com", 30000));
        empList.add(new Employee(3, "Michael", "michael@gmail.com", 40000));
        empList.add(new Employee(4, "Sanya", "sanya@gmail.com", 28000));
        empList.add(new Employee(5, "Robin", "robin@gmail.com", 50000));

        Optional<Integer> maxSalary = empList.stream().filter(emp -> emp.getSalary() > 25000).map(emp -> emp.getSalary()).reduce((a, b) -> a > b ? a : b);
        System.out.println(maxSalary.get());
    }

    public static class Employee {
        private int id;
        private String name;
        private String email;
        private int salary;

        public Employee(int id, String name, String email, int salary) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.salary = salary;
        }

        public int getSalary() {
            return salary;
        }
    }
}
