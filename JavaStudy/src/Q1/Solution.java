// ���� ���ڿ��� ���ܾ� (���α׷��ӽ� LEVEL1)

package Q1;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution("one4seveneight");
	}
	
    public int solution(String s) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>(); // �ð� ���⵵ : O(1) , Ű ���� �迭�� �ε����� ��ȯ
        map.put("zero", 0);
    	map.put("one", 1);
    	map.put("two", 2);
    	map.put("three", 3);
    	map.put("four", 4);
    	map.put("five", 5);
    	map.put("six", 6);
    	map.put("seven", 7);
    	map.put("eight", 8);
    	map.put("nine", 9);
    	
    	for(String key : map.keySet()) {
            System.out.println(key+":"+map.get(key));
         }
    	
        System.out.println("�ȳ��ϼ���");
        return answer;
    }

}