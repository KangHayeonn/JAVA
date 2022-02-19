// 실패율 (프로그래머스 LEVEL1 - Kakao)

package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FailureRate {
	public static class type implements Comparable<type> {
		private int index;
		private float value;
		
		public type(int index, float value) {
			this.index = index;
			this.value = value;
		}
		
		@Override
		public int compareTo(type t) {
			if(this.value == t.value) {
				return this.index - t.index; // index 오름차순
			}
			else return t.value - this.value > 0 ? 1 : -1; // 기본 : value 내림차순
		}
	}
	public static void main(String args[]) {
		//int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] stages = {4, 4, 4, 4, 4};
		System.out.println(Arrays.toString(solution(4, stages)));
	}
	public static float[] solution(int N, int[] stages) {
		float[] answer = new float[N+1];
		int index = 1;
		
		int clear = 0;
		int cnt = 0;
		
		Arrays.sort(stages);
		PriorityQueue<type> pq= new PriorityQueue<>();
		
		int before = stages[0];
		
		for(int i=0; i<stages.length; i++) {
			System.out.println("v: " + before +" i : " + stages[i]);
			if(stages[i]!=before) {
				float value = (float)cnt / (stages.length-clear);
				pq.add(new type(index++, value));
				clear += cnt;
				cnt = 1;
				before = stages[i];
			} else if(stages[i] == before) {
				cnt++;
			}
		}
		// 이부분 일단 skip
		float value = (float)(cnt-1) / (stages.length-clear);
		pq.add(new type(index, value));
		
		while(!pq.isEmpty()) {
			type p = pq.poll();
			System.out.println(p.index + " value: " + p.value);
		}
		return answer;
	}
}
