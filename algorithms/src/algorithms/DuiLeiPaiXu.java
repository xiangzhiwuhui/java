package algorithms;


/**
 * 要对集合中的对象的某属性进行排序有两种方式。
 * a. 一种是要排序对象类实现comparable接口的compareTo方法；然后把对象放入list；然后调用Collections.sort(list);
 * b. 一种是不对要排序对象类做任何改动，创建Comparator接口的实现类C；然后 把对象放入list；然后调用Collections.sort(list, C);
 * 
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class DuiLeiPaiXu {

	public static void main(String[] args) {
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(new Person("1", "zhangSan", 5, 104));
		list.add(new Person("3", "liSi", 3, 104));
		list.add(new Person("4", "wangWu", 2, 104));
		list.add(new Person("6", "zhaoLiu", 2, 103));
		list.add(new Person("2", "chang", 2, 104));
		list.add(new Person("5", "duan", 5, 104));
		
		System.out.println("########按照id排序(从小到大)#############");
		Collections.sort(list, new idComparator());
		for(Person p : list) {
			System.out.printf("%-4s%-10s%-3d%-6d\n", p.id, p.name, p.age, p.weigh);
		}
		
		System.out.println("########按体重（weight）升序排序#############");
		Collections.sort(list, new WeightComparator());
		for(Person p : list) {
			System.out.printf("%-4s%-10s%-3d%-6d\n", p.id, p.name, p.age, p.weigh);
		}
		
		System.out.println("########先按年龄升序排序，如果年龄相同再按体重降序排序#############");
		Collections.sort(list, new AgeWeightComparator());
		for(Person p : list) {
			System.out.printf("%-4s%-10s%-3d%-6d\n", p.id, p.name, p.age, p.weigh);
		}
		
		System.out.println();
		
		System.out.println("#############通过类实现Comparable接口排序#################");
		System.out.println("########先按年龄升序排序，如果年龄相同再按体重降序排序#############");
		ArrayList<PersonImp> list2 = new ArrayList<PersonImp>();
		list2.add(new PersonImp("1", "zhangSan", 5, 104));
		list2.add(new PersonImp("3", "liSi", 3, 104));
		list2.add(new PersonImp("4", "wangWu", 2, 104));
		list2.add(new PersonImp("6", "zhaoLiu", 2, 103));
		list2.add(new PersonImp("2", "chang", 2, 104));
		list2.add(new PersonImp("5", "duan", 5, 104));
		
		Collections.sort(list2);
		for(PersonImp p : list2) {
			System.out.printf("%-4s%-10s%-3d%-6d\n", p.id, p.name, p.age, p.weigh);
		}
		
	}
}

// 按id从小到大进行排序
class idComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		
		return p1.id.compareTo(p2.id);
	}
	
}

//按体重（weight）从小到大进行排序
class WeightComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		
		return p1.weigh - p2.weigh;
	}
	
}

// 先按年龄升序排序，如果年龄相同再按体重降序排序
class AgeWeightComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		
		if (p1.age < p2.age) {
			return -1;
		}else if (p1.age > p2.age) {
			return 1;
		}else {
			if (p1.weigh < p2.weigh) {
				return 1;
			}else if (p1.weigh > p2.weigh) {
				return -1;
			}else {
				return 0;
			}
		}
	}
	
}

class Person {
	String id;
	String name;
	Integer age;
	Integer weigh;
	public Person(String id, String name, Integer age, Integer weight) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.weigh = weight;
	}
}

// 实现Comparable接口的类(先按年龄升序排序，如果年龄相等再按体重降序排序)
class PersonImp implements Comparable<PersonImp> {
	String id;
	String name;
	Integer age;
	Integer weigh;
	public PersonImp(String id, String name, Integer age, Integer weight) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.weigh = weight;
	}
	
	public int compareTo(PersonImp p) {
		if (age == p.age) {
			return -(weigh - p.weigh);
		}else {
			return age - p.age;
		}
		
	}
	
}

