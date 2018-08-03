
public class T {

	//외부에서 객체를 생성못하도록 생성자를 private으로 변경
	private T() {}
	
	//객체를 1개만 만들어서 리턴해주기 위한 static 변수 선언
	private static T obj;
	
	//객체를 처음 1개만 만들어서 리턴해주는 메소드 생성
	public static T getInterface() {
		if(obj == null) {
			obj = new T();
		}
		return obj;
	}
}
