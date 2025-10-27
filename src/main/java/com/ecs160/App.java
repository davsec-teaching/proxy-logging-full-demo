package com.ecs160;
import com.ecs160.model.Student;
import com.ecs160.ProxyCreator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        ProxyCreator proxyCreator = new ProxyCreator();
        Student s = new Student ("John Doe", 20, "S12345");
        Student student = (Student) proxyCreator.createProxy(s);
        System.out.println("Student Name: " + student.getName());
        System.out.println("Student Age: " + student.getAge());
        System.out.println("Student can vote: " + student.isEligibleToVote());
    }
}
