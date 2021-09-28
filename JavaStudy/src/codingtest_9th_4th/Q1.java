// 없는 숫자 더하기 (프로그래머스 LEVEL1)

// ver1
// (1) numbers를 정렬한다.
// (2) 반복문을 돌리면서 해당 원소가 numbers에 들어있지 않으면 result에 더함

package codingtest_9th_4th;

import java.util.Arrays;

public class Q1 {
	static public void main(String args[]) {
		int numbers[] = {1,2,3,4,6,7,8,0};
		Q1 s = new Q1();
		System.out.println(s.solution(numbers));
	}
	
	public int solution(int[] numbers) {
		int answer = 0;
		int j=0;
		
		Arrays.sort(numbers);
		System.out.println(Arrays.toString(numbers));
		for(int i=0; i<10;i++) {
			
			if(j>=numbers.length) {
				answer +=i;
				continue;
			}
			else if(numbers[j]==i) {
				j++;
			}
			else {
				System.out.printf("answer : %d , i : %d , j: %d, numbers[j]: %d\n" , answer, i, j, numbers[j]);
				answer +=i;
			}
		}
		return answer;
	}
}
