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
		// int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] stages = {4, 4, 4, 4, 4};
		System.out.println(Arrays.toString(solution(4, stages)));
	}
	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int index = 1;
		
		int clear = 0;
		int cnt = 0;
		
		Arrays.sort(stages);
		PriorityQueue<type> pq= new PriorityQueue<>();
		
		int before = stages[0];
		boolean check = false;
		
		for(int i=0; i<stages.length; i++) {
			if(stages[i]!=before) {
				float value = (float)cnt / (stages.length-clear);
				pq.add(new type(index++, value));
				clear += cnt;
				cnt = 1;
				before = stages[i];
				check = true;
			} else if(stages[i] == before) {
				cnt++;
				check = false;
			}
		}
		
		while(index<=N) {
			if(index<N) {
				pq.add(new type(index, 0));
				index++;
			} else {
				if(check) {
					float value = (float)(cnt-1) / (stages.length-clear);
					pq.add(new type(index, value));
				} else {
					float value = (float)(cnt) / (stages.length-clear);
					pq.add(new type(index, value));
				}
				index++;
			}
		}
		
		index = 0;
		while(!pq.isEmpty()) {
			type p = pq.poll();
			answer[index++] = (int)(p.index);
		}
		return answer;
	}
}
