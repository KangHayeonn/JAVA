// 기능개발 (프로그래머스 LEVEL2)

// 큐 이용
package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FunctionalDevelopment {
	public static void main(String args[]) {
		int[] progresses = {50, 6, 65};
		int[] speeds = {5, 10, 7};
		System.out.println(Arrays.toString(solution(progresses, speeds)));
	}
	public static int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i=0; i<progresses.length; i++) {
			int a = 100 - progresses[i];
			int b = speeds[i];
			
			if(a>=b) {
				int days = (int)Math.ceil(a/(double)b);
				q.add(days);
			} else {
				q.add(1);
			}
		}
		
		int before = 0;
		int count = 0;
		
		while(!q.isEmpty()) {
			int day = q.poll();
            
			if(before==0) {
				count=1;
				before = day;
				continue;
			}
			
			if(before < day) {
				result.add(count);
				count = 1;
                before = day;
			} else {
				count++;
			}
		}
		
		result.add(count);
		
        int[] answer = new int[result.size()];
        int i = 0;
        
		for(int a : result) {
            answer[i] = a;
			i++;
		}
		
		return answer;
	}
}
