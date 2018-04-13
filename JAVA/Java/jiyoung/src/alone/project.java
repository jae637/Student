package alone;

import java.util.Scanner;

class Book {
	String name;
	String writer;
	String company;
	int page;
	int cost;

	Book(String name, String writer, String company, int page, int cost) {
		this.name = name;
		this.writer = writer;
		this.company = company;
		this.page = page;
		this.cost = cost;
	}

	Book() {
		name = null;
		writer = null;
		company = null;
		page = 0;
		cost = 0;
	}

	void PrintInfo() {
		System.out.println("����:" + name);
		System.out.println("����:" + writer);
		System.out.println("���ǻ�:" + company);
		System.out.println("�ʼ�:" + page);
		System.out.println("����:" + cost);
	}
}

class Novel extends Book {
	String genre;

	Novel(String name, String writer, String company, int page, int cost, String genre) {
		super(name, writer, company, page, cost);
		this.genre = genre;
	}

	Novel() {
		super();
		genre = null;
	}

	void PrintInfo() {
		System.out.println("�Ҽ�å �Դϴ�");
		super.PrintInfo();
		System.out.println("�帣:" + genre);
	}
}

class Poem extends Book {
	Poem(String name, String writer, String company, int page, int cost, String genre) {
		super(name, writer, company, page, cost);
		this.genre = genre;
	}

	Poem() {
		super();
		genre = null;
	}

	String genre;

	void PrintInfo() {
		System.out.println("�� �Դϴ�");
		super.PrintInfo();
		System.out.println("�帣:" + genre);
	}
}

class Magazine extends Book {
	String date;

	Magazine(String name, String writer, String company, int page, int cost, String date) {
		super(name, writer, company, page, cost);
		this.date = date;
	}

	Magazine() {
		super();
		date = null;
	}

	void PrintInfo() {
		System.out.println("���� �Դϴ�");
		super.PrintInfo();
		System.out.println("ȣ��:" + date);
	}
}

public class project {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int select = 0;
		int gerne;
		String name;
		String writer;
		String company;
		String a;
		int page;
		int cost;
		Novel N[] = new Novel[100];
		Poem P[] = new Poem[100];
		Magazine M[] = new Magazine[100];
		int Ncount = 0;
		int Mcount = 0;
		int Pcount = 0;
		int x = 0;

		while (x != 2) {
			x = 0;
			System.out.print("�޴��� ������ �ּ��� (1:å �˻�/2:å �߰�/3:å ����):");
			select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.print("å ������ �Է��� �ּ���:");
				name = scanner.next();
				for (int i = 0; i < Ncount; i++) {
					if (name.equals(N[i].name)) {
						N[i].PrintInfo();
						x = 1;
					}
				}
				for (int i = 0; i < Mcount; i++) {
					if (name.equals(M[i].name)) {
						M[i].PrintInfo();
						x = 1;
					}
				}
				for (int i = 0; i < Pcount; i++) {
					if (name.equals(P[i].name)) {
						P[i].PrintInfo();
						x = 1;
					}
				}
				if (x == 1)
					break;
				System.out.println("�˻��� å�� �������� �ʽ��ϴ�.");
				break;
			case 2:
				System.out.print("å �߰� �� ����� ������ �ּ��� (1:�Ҽ�, 2:��, 3:����) :");
				gerne = scanner.nextInt();
				if (0 < gerne && gerne < 4) {
					System.out.print("å ������ �Է��� �ּ���:");
					name = scanner.next();
					System.out.print("å �۾��̸� �Է��� �ּ���:");
					writer = scanner.next();
					System.out.print("å ���ǻ縦 �Է��� �ּ���:");
					company = scanner.next();
					System.out.print("å �������� �Է��� �ּ���:");
					page = scanner.nextInt();
					System.out.print("å ���ݸ� �Է��� �ּ���:");
					cost = scanner.nextInt();
					switch (gerne) {
					case 1:
						System.out.print("�Ҽ��� �帣�� �Է��� �ּ���:");
						a = scanner.next();
						N[Ncount] = new Novel(name, writer, company, page, cost, a);
						Ncount++;
						break;
					case 2:
						System.out.print("���� �帣�� �Է��� �ּ���:");
						a = scanner.next();
						P[Pcount] = new Poem(name, writer, company, page, cost, a);
						Pcount++;
						break;
					case 3:
						System.out.print("������ ȣ���� �Է��� �ּ���:");
						a = scanner.next();
						M[Mcount] = new Magazine(name, writer, company, page, cost, a);
						Mcount++;
						break;
					}
				} else
					break;
				break;
			case 3:
				System.out.print("������ å ������ �Է��� �ּ���:");
				name = scanner.next();
				for (int i = 0; i < Ncount; i++) {
					if (name.equals(N[i].name)) {
						N[i] = new Novel();
						System.out.println("���� �Ϸ�");
						x = 1;
					}
				}
				for (int i = 0; i < Mcount; i++) {
					if (name.equals(M[i].name)) {
						M[i] = new Magazine();
						System.out.println("���� �Ϸ�");
						x = 1;
					}
				}
				for (int i = 0; i < Pcount; i++) {
					if (name.equals(P[i].name)) {
						P[i] = new Poem();
						System.out.println("���� �Ϸ�");
						x = 1;
					}
				}
				if (x == 1)
					break;
				System.out.println("�˻��� å�� �������� �ʽ��ϴ�.");
				break;
			default:
				x = 2;
				break;
			}
		}
		System.out.println("�ý��� ����");
	}
}