// 완주하지 못한 선수 (프로그래머스 LEVEL1)

// ver1
// (1) 오름차순으로 정리
// (2) 순서대로 스택에 정리
// (3) budget을 넘는지 확인하기
// (4) 스택에 저장된 개수를 result로 출력

package codingtest_9th_3rd;

import java.util.ArrayList;
import java.util.Arrays;

public class Q2 {
	static public void main(String args[]) {
		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		Q2 s = new Q2();
		System.out.println(s.solution(participant, completion));
	}
	public String solution(String[] participant, String[] completion) {
		String answer ="";
		Arrays.sort(participant);
		Arrays.sort(completion);
		
		for(int i=0; i<completion.length; i++) {
			if(participant[i].equals(completion[i])) {
				continue;
			}
			else {
				answer = participant[i];
				break;
			}
		}

		if(answer.isEmpty()) return participant[participant.length-1];
		else return answer;
	}
	
}
