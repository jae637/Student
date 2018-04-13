package jiyoung;

// ���� �θ� Ŭ����
class Employee {
	private String name,addr;
	private int salary, age;
	public Employee(String name,String addr, int age, int salary) {
		this.addr=addr;
		this.name=name;
		this.age=age;
		this.salary=salary;
		
	}	
	String getName() {
		return name;
	}
	String getAddr() {
		return addr;
	}
	int getSalary() {
		return salary;
	}
	int getAge() {
		return age;
	}
	void setName(String name) {
		this.name=name;
	}
	void setAddr(String addr) {
		this.addr=addr;
	}
	void setSalary(int salary) {
		this.salary=salary;
	}
	void setAge(int age) {
		this.age=age;
	}
	void printEmp() {
		System.out.println("�̸�:"+name);
		System.out.println("����:"+age);
		System.out.println("�ּ�:"+addr);
	}
}

//������
class Regular extends Employee {
	private String dept;
	//������
	public Regular(String name, String addr, int age, int salary,String dept) {
		super(name, addr, age, salary);
		this.setDept(dept);
	}
	
	public String getDept() {
		return dept;
	}
	
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	void printEmp() {
		System.out.println("������");
		System.out.println("�μ�:"+dept);
		super.printEmp();
		System.out.println(super.getSalary());
		System.out.println("");
	}
}

//�ӽ���
class Temporary extends Employee{
	private int hour;
	private int wage=7500;
	
	//������
	public Temporary(String name, String addr, int age, int salary) {
		super(name, addr, age, salary);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
		super.setSalary(hour*wage);
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}
	
	void printEmp() {
		super.printEmp();
		System.out.println("���� �ð�:"+hour);
		System.out.println("�޿�:"+wage);
		System.out.println(super.getSalary());
		System.out.println("");
	}
}

public class EmployeeMain {
	public static void main(String[] args) {
		Regular A = new Regular("������", "��õ��", 20, 3000000, "�濵�к�");
		Temporary B = new Temporary("������", "�����", 20, 0);
		Employee C = A;
		B.setHour(240);
		A.printEmp();
		B.printEmp();
		C.printEmp();
	}

}
