package jiyoung;

class Animal{
	String name;
	int age;
	Animal(String a,int b){
		name=a;
		age=b;
	}
	void printAnimal() {
		System.out.print("이름:"+name+", 나이:"+age);
	}
}

class Pet extends Animal{
	String place;
	Pet(String a,int b, String c){
		super(a,b);
		place=c;
	}
	void printPet() {
		super.printAnimal();
		System.out.print(", 장소:"+place);
	}
}
public class Quiz1 {
	public static void main(String[] args) {
		Pet p= new Pet("바둑이",10,"우리집");
		p.printPet();
	}
}