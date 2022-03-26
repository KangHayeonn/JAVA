// 숫자 게임 (Step1-4)

// 정렬, 완전탐색

package programmersSchool;

import java.util.Arrays;

public class NumberGame {
	public static void main(String args[]) {
		int[] A = {5, 1, 3, 7};
		int[] B = {2, 2, 6, 8};
		
		System.out.println(solution(A, B));
	}
	public static int solution(int[] A, int[] B) {
		int answer = 0;
		Arrays.sort(A);
		Arrays.sort(B);
		
		int idx = 0;
		
		for(int i=0; i<A.length; i++) {
			for(int j=idx; j<B.length;) {
				if(A[i]<B[j]) {
					answer++;
					idx = j+1;
					break;
				} else {
					j++;
				}
			}
		}
		
		return answer;
	}
}
