
public class TMain {

	public static void main(String[] args) {
		//�Ϲ� T Ŭ������ �����ڸ� �̿��ؼ� ��ü�� ����
		//T obj1 = new T();
		//T obj2 = new T();
		
		//�̱��� ������ ������ T Ŭ������ ��ü�� ����
		T obj1 = T.getInterface();
		T obj2 = T.getInterface();
		
		//��ü ��ġ ���
		System.out.println(System.identityHashCode(obj1));
		System.out.println(System.identityHashCode(obj2));
		
		
		

	}

}
