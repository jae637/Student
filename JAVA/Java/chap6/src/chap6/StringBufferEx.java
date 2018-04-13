package chap6;

public class StringBufferEx {
	public static void main(String[] args)
	{
		StringBuffer sb = new StringBuffer("This");
		System.out.println(sb.hashCode());
		sb.append("is pencil");
		System.out.println(sb);
		
		sb.insert(7, "my");
		System.out.println(sb);
		
		sb.replace(8, 10, "Your");
		System.out.println(sb);
		
		sb.setLength(5);
		
		System.out.println(sb);
		System.out.println(sb.hashCode());
	}
}
