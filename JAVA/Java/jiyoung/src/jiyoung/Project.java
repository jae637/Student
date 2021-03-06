package jiyoung;

import java.util.Scanner;

class Book {
	String name;
	String writer;
	String company;
	int page;
	int cost;
	
	Book(String name, String writer, String company, int page, int cost){
		this.name=name;
		this.writer=writer;
		this.company=company;
		this.page=page;
		this.cost=cost;
	}
	Book(){
		name=null;
		writer=null;
		company=null;
		page=0;
		cost=0;
	}
	
	void PrintInfo() {
		System.out.println("제목:"+name);
		System.out.println("저자:"+writer);
		System.out.println("출판사:"+company);
		System.out.println("쪽수:"+page);
		System.out.println("가격:"+cost);
	}
}

class Novel extends Book{
	String genre;
	
	Novel(String name, String writer, String company, int page, int cost, String genre) {
		super(name, writer, company, page, cost);
		this.genre=genre;
	}
	public Novel() {
		super();
		genre=null;
	}
	void PrintInfo() {
		System.out.println("소설책 입니다");
		super.PrintInfo();
		System.out.println("장르:"+genre);
	}
}

class Poem extends Book{
	Poem(String name, String writer, String company, int page, int cost,String genre) {
		super(name, writer, company, page, cost);
		this.genre=genre;
	}
	Poem(){
		super();
		genre=null;
	}
	String genre;
	@Override
	void PrintInfo() {
		System.out.println("시 입니다");
		super.PrintInfo();
		System.out.println("장르:"+genre);
	}
}

class Magazine extends Book{
	String date;
	
	Magazine(String name, String writer, String company, int page, int cost,String date) {
		super(name, writer, company, page, cost);
		this.date=date;
	}
	Magazine(){
		super();
		date=null;
	}
	@Override
	void PrintInfo() {
		System.out.println("잡지 입니다");
		super.PrintInfo();
		System.out.println("호수:"+ date);
	}
}

public class Project {
	public static void main(String[] args)
	{	
		Scanner scanner = new Scanner(System.in);
		int select = 0;	//
		int garne;		//
		String name;	//
		String writer;	//
		String company;	//
		String a;		//
		int page;		//
		int cost;		//
		int flag=0;
		
		Novel N[] = new Novel[100];
		Poem P[] = new Poem[100];
		Magazine M[] = new Magazine[100];
		int Ncount=0;	// 소설의 갯수
		int Mcount=0;	// 잡지의 갯수
		int Pcount=0;	// 시의 갯수
		
		while(flag!=2) {
			flag=0;
			System.out.print("메뉴를 선택해 주세요 (1:책 검색/2:책 추가/3:책 삭제):");
			select= scanner.nextInt();
			switch(select) {
			case 1:
				System.out.print("책 제목을 입력해 주세요:");
				name=scanner.next();
				for(int i=0;i<Ncount;i++)
				{
					if(name.equals(N[i].name)) {
						N[i].PrintInfo();
						flag=1;
					}
				}
				for(int i=0;i<Mcount;i++)
				{
					if(name.equals(M[i].name)) {
						M[i].PrintInfo();
						flag=1;
					}
				}
				for(int i=0;i<Pcount;i++)
				{
					if(name.equals(P[i].name)) {
						P[i].PrintInfo();
						flag =1;
					}
				}
				if(flag==1)
					break;
				System.out.println("검색한 책이 존재하지 않습니다.");
				break;
			case 2:
				System.out.print("책 추가 할 목록을 선택해 주세요 (1:소설, 2:시, 3:잡지) :");
				garne=scanner.nextInt();
				if (0<garne&&garne<4){
					System.out.print("책 제목을 입력해 주세요:");
					name=scanner.next();
					System.out.print("책 글쓴이를 입력해 주세요:");
					writer=scanner.next();
					System.out.print("책 출판사를 입력해 주세요:");
					company=scanner.next();
					System.out.print("책 페이지를 입력해 주세요:");
					page=scanner.nextInt();
					System.out.print("책 가격를 입력해 주세요:");
					cost=scanner.nextInt();
					switch (garne)
					{
					case 1:
						System.out.print("소설의 장르를 입력해 주세요:");
						a=scanner.next();
						N[Ncount]= new Novel(name,writer,company,page,cost,a);
						Ncount++;
						break;
					case 2:
						System.out.print("시의 장르를 입력해 주세요:");
						a=scanner.next();
						P[Pcount]= new Poem(name,writer,company,page,cost,a);
						Pcount++;
						break;
					case 3:
						System.out.print("잡지의 호수를 입력해 주세요:");
						a=scanner.next();
						M[Mcount]= new Magazine(name,writer,company,page,cost,a);
						Mcount++;
						break;
					}
				}
				else
					break;
				break;
			case 3:
				System.out.print("삭제할 책 제목을 입력해 주세요:");
				name=scanner.next();
				for(int i=0;i<Ncount;i++)
				{
					if(name.equals(N[i].name)) {
						N[i]=new Novel();
						System.out.println("삭제 완료");
						flag=1;
					}
				}
				for(int i=0;i<Mcount;i++)
				{
					if(name.equals(M[i].name)) {
						M[i]=new Magazine();
						System.out.println("삭제 완료");
						flag=1;
					}
				}
				for(int i=0;i<Pcount;i++)
				{
					if(name.equals(P[i].name)) {
						P[i]=new Poem();
						System.out.println("삭제 완료");
						flag=1;
					}
				}
				if (flag==1)
					break;
				System.out.println("검색한 책이 존재하지 않습니다.");
				break;
			default:
				flag =2;
				break;
			}
		}
		System.out.println("시스템 종료");
	}
}
