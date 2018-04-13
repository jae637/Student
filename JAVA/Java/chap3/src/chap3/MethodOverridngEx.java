package chap3;

public class MethodOverridngEx {
	public static void main(String[] args) {
		DObject obj = new DObject();
		Line line = new Line();
		DObject p =new Line();
		DObject r=line;
		
		obj.draw();
		line.draw();
		p.draw();
		r.draw();
		
		DObject rect = new Rect();
		DObject circle = new Circle();
		rect.draw();
		circle.draw();
	}
}

