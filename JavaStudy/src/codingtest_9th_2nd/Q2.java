// 모의고사 (프로그래머스 LEVEL1)

// 알고리즘
// (1) 1번, 2번, 3번 수포자의 각각의 문제를 찍는 규칙을 배열에 넣어준다.
// (2) 문제의 정답을 순서대로 돌리면서 각각의 수포자들의 정답과 비교하는 구문을 작성한다.
package codingtest_9th_2nd;

import java.util.ArrayList;
import java.util.Arrays;

public class Q2 {
	static public void main(String args[]) {
		Q2 s = new Q2();
		int[] answers = {1,2,3,4,5};
		System.out.println(Arrays.toString(s.solution(answers)));
		return;
	}
	
	public int[] solution(int[] answers) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int[] first = {1,2,3,4,5};
		int[] second = {2,1,2,3,2,4,2,5};
		int[] third = {3,3,1,1,2,2,4,4,5,5};
		int[] count = {0,0,0};
		
		for(int i=0; i<answers.length; i++) {
			int key = answers[i];
			if(first[i%first.length]==key) count[0]++;
			if(second[i%second.length]==key) count[1]++;
			if(third[i%third.length]==key) count[2]++;
		}
		int arr_max = Arrays.stream(count).max().getAsInt();
		
		if(arr_max == count[0]) {
			result.add(1);
		}
 		if(arr_max == count[1]) {
			result.add(2);
		}
		if(arr_max == count[2]) {
			result.add(3);
		}
		
		int answer[] = new int[result.size()];
		int size = 0;
		
		for(Integer item : result) {
			answer[size++] = item;
		}
		return answer;
	}
}
