package chap3;

class DObject {
	public DObject next;
	
	public DObject() {
		next = null;
	}
	public void draw() {
		System.out.println("DObject draw");
	}
}
