// 예산 (프로그래머스 LEVEL1)

// ver1
// (1) 오름차순으로 정리
// (2) 순서대로 스택에 정리
// (3) budget을 넘는지 확인하기
// (4) 스택에 저장된 개수를 result로 출력

package codingtest_9th_3rd;

import java.util.ArrayList;
import java.util.Arrays;

public class Q1 {
	static public void main(String args[]) {
		Q1 s = new Q1();
		int d[] = {1,3,2,5,4};
		System.out.println(s.solution(d, 9));
	}
	public int solution(int[] d, int budget) {
		int sum = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		Arrays.sort(d);
		for(int item : d) {
			sum += item;
			if(sum<=budget) {
				list.add(item);
			}
		}
		System.out.println(list + " " + list.size());
		return list.size();
	}
}
