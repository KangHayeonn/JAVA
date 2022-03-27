// 프린터 (프로그래머스 LEVEL2)

// 큐, map 이용

package programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Printer {
	public static class Type {
		private int priority;
		private int turn;
		
		public Type(int priority, int turn) {
			this.priority = priority;
			this.turn = turn;
		}
	}
	public static void main(String args[]) {
		int[] priorities = {1, 1, 9, 1, 1, 1};
		System.out.println(solution(priorities, 0));
	}
	public static int solution(int[] priorities, int location) {
		int answer = 0;
		Queue<Type> q = new LinkedList<>();
		Map<Integer, Integer> map = new HashMap<>(); // key:순서, value: location
		
		for(int i=0; i<priorities.length; i++) {
			q.add(new Type(priorities[i], i));
		}
		
		int idx = 1;
		
		while(!q.isEmpty()) {
			Type a = q.poll();
			boolean check = false; // 우선순위가 더 높은 것이 있으며 true
			
			for(Type t : q) {
				if(a.turn == t.turn) continue;
				else {
					if(a.priority < t.priority) {
						q.add(new Type(a.priority, a.turn));
						check = true;
						break;
					}
				}
			}
			
			if(!check) {
				if(!map.containsKey(a.turn)) {
					map.put(a.turn, idx);
					idx++;
				}
			}
		}
		
		answer = map.get(location);
		
		return answer;
	}
}
