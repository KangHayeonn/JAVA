// 숫자 문자열과 영단어 (프로그래머스 LEVEL1)

package Q1;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution("2three45sixseven"));
	}
	
    public int solution(String s) {
        int answer = 0;
        String newStr = "";
        Map<String, String> map = new HashMap<>(); // 시간 복잡도 : O(1) , 키 값이 배열의 인덱스로 변환
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
