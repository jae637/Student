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
		name = "���ѹ�";
		height= 200;
		setWeight(200);
	}
	
	public static void main(String argc[])
	{
		Student s = new Student();
		s.set();
		System.out.println("�л� �̸��� "+s.name+" �̰� ���̴�"+s.age +"�Դϴ�.");
	}
}
