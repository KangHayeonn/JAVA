// 다리를 지나는 트럭 (프로그래머스 LEVEL2)

/* [ 알고리즘 ]
 * 
 * 1. 먼저 대기트럭의 무게를 저장한 배열을 받음
 * 2. 해당 대기트럭의 배열의 첫번째 인덱스의 원소를 빼서 sum에 더함
 * 3. sum이 다리가 견딜 수 있는 무게를 넘지 않으면 큐에 저장(time++) -> 다음 대기트럭 인덱스로 넘어감
 * 4. sum이 다리가 견딜 수 있는 무게를 넘었을 경우 -> 큐에 0을 추가(time++)
 * 5. 해달 큐의 길이가 다리의 길이를 넘지 않을 때가지 3-4번을 반복
 * 6. 만약 큐의 길이가 다리 길이를 넘었을 경우 큐에서 하나를 삭제 -> 해당 원소를 sum에서 빼줌
 * 7. 만약 대기트럭이 다 저장될 경우 큐가 isEmpty가 될 때까지 time++
 */

// ver1 : 실패
// 테스트케이스 2,4,5,6,7,8,10
/*
package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class BridgeTruck {
	public static void main(String args[]) {
		BridgeTruck s = new BridgeTruck();
		int arr[] = {7,4,5,6};
		System.out.println(s.solution(2, 10, arr));
	}
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		Queue<Integer> bridge = new LinkedList<Integer>();
		int answer = 0;
		int sum = 0;		
		
		for(int i=0; i<truck_weights.length; i++) {
			int truck = truck_weights[i];
			
			for(int j=0; j<bridge_length+truck_weights.length; j++) {
				if(bridge.isEmpty()) {
					bridge.add(truck);
					sum += truck;
					answer++;
					break;
				}
				else if(bridge.size() == bridge_length) sum -= bridge.poll();
				else {
					sum += truck;
					if(sum<=weight) {
						bridge.add(truck);
						answer++;
						break;
					}
					else {
						bridge.add(0);
						sum -= truck;
						answer++;
					}
				}
			}
			
			if(i==truck_weights.length-1) answer += bridge_length;
		}
		return answer;
	}
}*/

// ver2 : 성공

package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class BridgeTruck {
	public static void main(String args[]) {
		BridgeTruck s = new BridgeTruck();
		int arr[] = {2, 2, 2, 2, 1, 1, 1, 1, 1};
		System.out.println(s.solution(5, 5, arr));
	}
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		Queue<Integer> bridge = new LinkedList<Integer>();
		int time = 0;
		int sum = 0; // 다리위에 올라간 트럭들의 무게
		
		for(int i=0; i<truck_weights.length;) {
			sum += truck_weights[i];
			if(sum <= weight) { // ** 중요 (3)   == 없으면 정답 x
				bridge.add(truck_weights[i]); 
				time++; 
				i++; 
			}
			else {
				sum -= truck_weights[i]; 
				while(bridge.size()<bridge_length) {
					bridge.add(0); 
					time++; 
				}
			}
			if(bridge.size()==bridge_length) { 
				sum -= bridge.poll();
			}
		}
		
		while(bridge.size()<bridge_length) { // ** 중요 (2)
			bridge.add(-1); 
			time++; 
		}
		
		while(!bridge.isEmpty()) {
			//bridge.poll();
			if(bridge.poll()!=-1) time++; // ** 중요 (1)
			//time++;
		}
		
		return time;
	}
}
