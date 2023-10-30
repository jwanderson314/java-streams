package com.xpanxion.solution;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

import com.sun.source.tree.Tree;
import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Person;
import com.xpanxion.java.assignments.model.PersonCat;
import com.xpanxion.java.assignments.model.Product;

import javax.xml.crypto.Data;

public class Worker {
    public void ex1 () {
        List<Product> arr1 = DataAccess.getProducts();
        List<Department> arr2 = DataAccess.getDepartments();

        for(int i = 0; i < arr1.size(); i++){
            for(int c = 0; c < arr2.size(); c++){
                if(arr1.get(i).getDepartmentId() == arr2.get(c).getId())
                    arr1.get(i).setDepartmentName(arr2.get(c).getName());
            }
        }
        System.out.println(arr1);

    }

    public void ex2(){

        List<Product> arr1 = DataAccess.getProducts();
        arr1.stream().peek(product -> product.setDepartmentName("N/A")).collect(Collectors.toList());
        System.out.println(arr1);


    }
    public void ex3(){
        System.out.println(DataAccess.getProducts().stream().filter(product -> product.getDepartmentId() == 1).filter(product -> product.getPrice() >= 10).collect(Collectors.toList()));

    }

    public void ex4(){
        double total = 0.0;
        for(int i = 0; i < DataAccess.getProducts().size(); i++){
            if(DataAccess.getProducts().get(i).getDepartmentId() == 2)
                total += DataAccess.getProducts().get(i).getPrice();
        }
        System.out.println("Total is $" + total);

    }
    public void ex5(){
        System.out.println(DataAccess.getPeople().stream().filter(id -> id.getId() <= 3).collect(Collectors.toList()));
    }

    public void ex6(){
        System.out.println(DataAccess.getCats().stream().sorted().collect(Collectors.toList()));
    }

    public void ex7(){

        ArrayList<String> arr = new ArrayList<String>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String[] x = DataAccess.getWords().split(" ");
        for(String word : x){
            var freq = map.putIfAbsent(word, 1); //did not find word
            if(freq != null){
                //found word
               map.put(word, freq + 1);
            }

        }
        sortbyKey(map);



    }
    public static void sortbyKey(HashMap<String, Integer> map ){
        TreeMap<String, Integer> sorted = new TreeMap<>(map);
        for(Map.Entry<String, Integer> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

    }

    public void ex8(){
        List<Person> arr1 = DataAccess.getPeople();
        arr1.stream().peek(product -> product.setLastName(null)).collect(Collectors.toList());
        arr1.stream().peek(product -> product.setAge(0)).collect(Collectors.toList());
        arr1.stream().peek(product -> product.setSsn(null)).collect(Collectors.toList());
        System.out.println(arr1);
    }

    public void ex9(){
        List<Product> arr1 = DataAccess.getProducts();
        double total = 0.0;
        for(Product x : arr1){
            if(x.getDepartmentId()==1)
                total += 2.0 + x.getPrice();
        }
        System.out.println("Total cost $" + total);
    }

    public void ex10(){
        System.out.println(DataAccess.getPeople().stream().map(
                person -> new PersonCat(
                        person.getId(),
                        person.getFirstName(),
                        DataAccess.getCats().stream().filter(cats -> cats.getId()==person.getId()).collect(Collectors.toList())
                )).collect(Collectors.toList()));

    }
}
