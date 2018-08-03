
public class TMain {

	public static void main(String[] args) {
		//일반 T 클래스의 생성자를 이용해서 객체를 생성
		//T obj1 = new T();
		//T obj2 = new T();
		
		//싱글톤 패턴을 적용한 T 클래스의 객체를 생성
		T obj1 = T.getInterface();
		T obj2 = T.getInterface();
		
		//객체 위치 출력
		System.out.println(System.identityHashCode(obj1));
		System.out.println(System.identityHashCode(obj2));
		
		
		

	}

}
