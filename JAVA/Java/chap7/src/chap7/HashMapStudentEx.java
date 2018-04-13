package chap7;
import java.util.*;
import java.io.*;

class Student{
		int id;
		double score;
		String name;
		public Student(int id,double score, String name) {
			this.id= id;
			this.score= score;
			this.name= name;
		}
}
public class HashMapStudentEx {
	public static void main (String[] args){
		HashMap<String,Student> map = new HashMap<String,Student>();
		
		String name;
		int id;
		double score;
		Scanner scanner = new Scanner(System.in);
		
		for (;;) {
			System.out.print("�̸��� �Է��ϼ��� : ");
			String s = scanner.next();
			if (s.equals("quit"))
				break;
			else {
				name=s;
				System.out.print("�й��� �Է��ϼ��� : ");
				String num1 = scanner.next();
				System.out.print("������ �Է��ϼ��� : ");
				String num2 = scanner.next();
				id=Integer.parseInt(num1);
				score=Double.parseDouble(num2);
				if(id>0&&id<2100000000&&score>0&&score<4.5)
					map.put(name, new Student(id,score,name));
				else
					System.out.println("�й�, Ȥ�� ������ �߸� �Է���ϴ�. ó������ �ٽ� �Է��ϼ���.");
			}
		}
		
//		System.out.println("HashMap�� ��� ����:" + map.size());
		
		Set<String> names = map.keySet();
		
		Iterator<String> it = names.iterator();
		while(it.hasNext()) {
			String name2 = it.next();
			Student student =  map.get(name2);
			System.out.println("�̸�:"+student.name+"/"+"�й�:"+student.id+"/"+"����:"+student.score);
		}
		it = names.iterator();
		for(;;) {
			System.out.print("ã���� �̸��� �Է��ϼ��� : ");
			String s = scanner.next();
			if (s.equals("quit")) {
				System.out.print("�����մϴ�");
				break;
				}
			else {
				while(it.hasNext()) {
					String name2 = it.next();
					Student student=  map.get(name2);
					if(student.name.equals(s)) {
						System.out.println("�̸�:"+student.name+"/"+"�й�:"+student.id+"/"+"����:"+student.score);
						it = names.iterator();
						break;
						}
					else if(!it.hasNext()){
							System.out.println("ã���� �ϴ� �̸��� �����ϴ�.");
							it = names.iterator();
							break;
					}
					else {}
					
				}
			}
		}
	}
}