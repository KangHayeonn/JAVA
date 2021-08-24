// 목표 : 객체지향언어에 대한 이해와 Map 인터페이스의 사용

package MapTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	
	static void printMap(Map<String, Integer> map) {
        for(String key : map.keySet()) {
           System.out.println(key+":"+map.get(key));
        }
        /*  // 클래스 메서드 내에 인스턴스 메서드 호출
        Solution s1 = new Solution();
        s1.solution("하연");
        */
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("안녕하세요");
		s.solution("one4seveneight");
		
		HashMap<String, Integer> hashmap = new HashMap<>(); // 특정 규칙없이 출력
        hashmap.put("One", 1);
        hashmap.put("Two", 2);
        hashmap.put("Three", 3);
        printMap(hashmap);
        TreeMap<String, Integer> tmap = new TreeMap<>(hashmap); // 키 값이 알파벳 순으로 정렬
        printMap(tmap);
        LinkedHashMap<String, Integer> lmap = new LinkedHashMap<>(hashmap); // 키 값이 입력 순서대로 출력 (링크드리스트와 유사)
        printMap(lmap);
	}
	
	public int solution(String s) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();  
        // map : key,value 형식의 데이터를 저장할 수 있는 인터페이스 / HashMap : map을 사용하기 위한 구현 클래스
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println(map);
        Map<String, Integer> tmap = new TreeMap<>(map);
        System.out.println(tmap);
        Map<String, Integer> lmap = new LinkedHashMap<>(map);
        System.out.println(lmap);
        solution1("hello");
        System.out.println(s);
        return answer;
    }
	
	public int solution1(String s) {
        int answer = 0;
        System.out.println("히히");
        return answer;
    }
}


// public , static, private 차이
/*
public : 접근 제한이 없음 , but 다른 클래스 내에선 객체를 선언한 후 사용 가능
protected : 동일한 패키지 내에 존재하거나 상속받은 하위 클래스에서 사용할 수 있음
default : 동일한 패키지 내에서 접근 가능
private : 자기 자신의 클래스내에서만 접근이 가능 (private 변수가 선언된 클래스)
static : "공유"의 의미를 담는 키워드, 클래스가 인스턴스화 되어있지 않더라도 접근 가능 (객체 없어도 선언 가능)

클래스 변수 : 여러 객체들이 객체 밖에서도 공유 가능한 변수 (ex, static int count =0)
인스턴스 변수 : 객체 각각에서만 쓸 수 있는 객체 내부의 변수
*/