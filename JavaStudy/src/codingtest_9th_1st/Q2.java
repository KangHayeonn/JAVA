// 숫자 문자열과 영단어 (프로그래머스 LEVEL1)

package codingtest_9th_1st;

import java.util.HashMap;
import java.util.Map;

public class Q2 {
	
	public static void main(String[] args) {
		Q2 s = new Q2();
		System.out.println(s.solution("2three45sixseven"));
	}
	
	public int solution(String s) {
		int answer = 0;
		String newStr = "";
		
		Map<String, String> map = new HashMap<>();
		
		map.put("zero", "0");
		map.put("one", "1");
    	map.put("two", "2");
    	map.put("three", "3");
    	map.put("four", "4");
    	map.put("five", "5");
    	map.put("six", "6");
    	map.put("seven", "7");
    	map.put("eight", "8");
    	map.put("nine", "9");
    	
    	for(String key : map.keySet()) {
    		if(s.contains(key)) {
    			newStr = s.replace(key, map.get(key));
    			s = newStr;
    		}
    	}
    	answer = Integer.parseInt(s);
		return answer;
	}
}
