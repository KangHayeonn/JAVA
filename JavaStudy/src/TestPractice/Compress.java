// 압축 (프로그래머스 LEVEL2 - Kakao)

package TestPractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Compress {
	static int num=27;
	public static void main(String args[]) {
		String msg = "KAKAO";
		System.out.println(Arrays.toString(solution(msg)));
	}
	public static int[] solution(String msg) {
		char ch = 'A';
		Queue<Integer> q = new LinkedList<>();
		StringBuffer newMsg = new StringBuffer(msg);

		// 길이가 1인 모든 단어를 포함하도록 사전을 초기화
		Map<String, Integer> map = new HashMap<>();
		for(int i=1; i<=26; i++) {
			map.put(String.valueOf(ch), i);
			ch+=1;
		}
		
		int idx = 1;
		int index = 0; // answer에 추가되는 인덱스
		
		for(int i=idx; i<=newMsg.length(); i++) {
			String s = newMsg.substring(0, i);
			
			if(map.containsKey(s)) {
				index = map.get(s);
			} else {
				q.add(index);
				index = 0;
				map.put(s, num);
				num++;
				newMsg.delete(0, i-1);
				i=0;
			}
		}
		
		if(index != 0) {
			q.add(index);
		}
		
		int[] answer = new int[q.size()];
		int i=0;
		
		while(!q.isEmpty()) {
			answer[i] = q.poll();
			i++;
		}
		
		return answer;
	}
}
