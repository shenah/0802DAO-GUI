
public class T {

	//�ܺο��� ��ü�� �������ϵ��� �����ڸ� private���� ����
	private T() {}
	
	//��ü�� 1���� ���� �������ֱ� ���� static ���� ����
	private static T obj;
	
	//��ü�� ó�� 1���� ���� �������ִ� �޼ҵ� ����
	public static T getInterface() {
		if(obj == null) {
			obj = new T();
		}
		return obj;
	}
}
