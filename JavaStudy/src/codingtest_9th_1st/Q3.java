// 폰켓몬 (프로그래머스 LEVEL1)

package codingtest_9th_1st;

import java.util.HashSet;

public class Q3 {
	public static void main(String[] args){
		int[] nums = {3,1,2,3};
		Q3 s = new Q3();
		System.out.println(s.solution(nums));
	}
	public int solution(int[] nums) {
		int answer =0;
		HashSet<Integer> set = new HashSet<Integer>();
		
		for(int i=0; i<nums.length; i++) {
			set.add(nums[i]);
			// System.out.println(nums[i]);
			// System.out.println("set크기확인: "+ set.size()+"\n");
		}
		if(nums.length/2 > set.size()) answer = set.size();
		else answer = nums.length/2;
		
		return answer;
	}
}
