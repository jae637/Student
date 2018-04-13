package chap3;

class Person{
	int age;
	public String name;
	protected int height;
	private int weight;
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
}

public class Student extends Person {
	void set() {
		age =28;
		name = "정한민";
		height= 200;
		setWeight(200);
	}
	
	public static void main(String argc[])
	{
		Student s = new Student();
		s.set();
		System.out.println("학생 이름은 "+s.name+" 이고 나이는"+s.age +"입니다.");
	}
}
