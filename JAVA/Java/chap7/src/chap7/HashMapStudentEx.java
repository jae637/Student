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
			System.out.print("이름을 입력하세요 : ");
			String s = scanner.next();
			if (s.equals("quit"))
				break;
			else {
				name=s;
				System.out.print("학번을 입력하세요 : ");
				String num1 = scanner.next();
				System.out.print("학점을 입력하세요 : ");
				String num2 = scanner.next();
				id=Integer.parseInt(num1);
				score=Double.parseDouble(num2);
				if(id>0&&id<2100000000&&score>0&&score<4.5)
					map.put(name, new Student(id,score,name));
				else
					System.out.println("학번, 혹은 학점이 잘못 입력됬습니다. 처음부터 다시 입력하세요.");
			}
		}
		
//		System.out.println("HashMap의 요소 개수:" + map.size());
		
		Set<String> names = map.keySet();
		
		Iterator<String> it = names.iterator();
		while(it.hasNext()) {
			String name2 = it.next();
			Student student =  map.get(name2);
			System.out.println("이름:"+student.name+"/"+"학번:"+student.id+"/"+"학점:"+student.score);
		}
		it = names.iterator();
		for(;;) {
			System.out.print("찾으실 이름을 입력하세요 : ");
			String s = scanner.next();
			if (s.equals("quit")) {
				System.out.print("종료합니다");
				break;
				}
			else {
				while(it.hasNext()) {
					String name2 = it.next();
					Student student=  map.get(name2);
					if(student.name.equals(s)) {
						System.out.println("이름:"+student.name+"/"+"학번:"+student.id+"/"+"학점:"+student.score);
						it = names.iterator();
						break;
						}
					else if(!it.hasNext()){
							System.out.println("찾고자 하는 이름이 없습니다.");
							it = names.iterator();
							break;
					}
					else {}
					
				}
			}
		}
	}
}
