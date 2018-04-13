package jiyoung;

// 직원 부모 클래스
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
		System.out.println("이름:"+name);
		System.out.println("나이:"+age);
		System.out.println("주소:"+addr);
	}
}

//정규직
class Regular extends Employee {
	private String dept;
	//생성자
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
		System.out.println("정규직");
		System.out.println("부서:"+dept);
		super.printEmp();
		System.out.println(super.getSalary());
		System.out.println("");
	}
}

//임시직
class Temporary extends Employee{
	private int hour;
	private int wage=7500;
	
	//생성자
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
		System.out.println("일한 시간:"+hour);
		System.out.println("급여:"+wage);
		System.out.println(super.getSalary());
		System.out.println("");
	}
}

public class EmployeeMain {
	public static void main(String[] args) {
		Regular A = new Regular("정지영", "부천시", 20, 3000000, "경영학부");
		Temporary B = new Temporary("김지율", "기숙사", 20, 0);
		Employee C = A;
		B.setHour(240);
		A.printEmp();
		B.printEmp();
		C.printEmp();
	}

}
