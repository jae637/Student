import java.util.ArrayList;

public class Newbie {
	final private static int noodle = 5000;
	final private static int pork = 4000;
	final private static int cuprice = 3000;
	final private static int ramun = 2000;
	
	public static void main(String args[]) {
		//����̰� ������ �ִ� ��
		int charge=50000;
		//����� �� ǥ�� ����
		int count=1;
		//����� ���ڿ� �迭����Ʈ ����
		ArrayList<String> result=new ArrayList<String>();
		
		//4���� ������ ���ݼ� ��� �ݺ���
		for(int i=1;i<11;i++) {
			//������ �ִ� ������ ������ŭ�� ������ ��
			charge=charge-(i*noodle);
			for( int j=1;j<13;j++) {
				//������ �ִ� ������ ������ŭ�� ������ ��
				charge=charge-(j*pork);
				//�ع��߳��쵿�� ����� ��������� �ܾ��� ���ڸ� ���
				if(charge<0) {
					charge=charge+(j*pork);
					break;
				}
				for(int k=1;k<17;k++) {
					charge=charge-(k*cuprice);
					//�ع��߳��쵿�� ����� �Ź丸 ��������� �ܾ��� ���ڸ� ���
					if(charge<0) {
						charge=charge+(k*cuprice);
						break;
					}
					for(int l=1;l<26;l++) {
						charge=charge-(l*ramun);
						//���δ� ������  �ܾ��� ���ڸ� ���
						if(charge<0) {
							charge=charge+(l*ramun);
							break;
						}
						//���δ� ������  �ܾ��� ���� �ʴ� ���
						else if(charge==0) {
							charge=charge+(l*ramun);
							result.add(count+". �ع��߳��쵿("+i+"��), �Ź�("+k+"��), ���("+l+"��), ���("+j+"��)");
							count++;
							break;
						}
						//�ݾ� ����, �ݾ� ��� �� �ܰ�� �ѹ�
						charge=charge+(l*ramun);
					}
					//�ݾ� ����, �ݾ� ��� �� �ܰ�� �ѹ�
					charge=charge+(k*cuprice);
				}
				//�ݾ� ����, �ݾ� ��� �� �ܰ�� �ѹ�
				charge=charge+(j*pork);
			}
			//�ݾ� ����, �ݾ� ��� �� �ܰ�� �ѹ�
			charge=charge+(i*noodle);
		}
		
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i));
		
	}
}
