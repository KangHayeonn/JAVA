// 다리를 지나는 트럭 (프로그래머스 LEVEL2)

// 큐를 이용해서 (선입선출) 들어오는 트럭을 저장해 준다.
// 이때, 들어온 트럭은 정해진 무게를 넘을 수 없음
// 만약 큐에 들어온 요소가 길이와 같아지면 가장 먼저 들어온 하나를 삭제해준다.
// 이때 모든 과정에서 카운트를 해줘 트럭이 한칸씩 움직이고 빠져나오는 모든 시간을 세어준다.

// ver1 : 실패
// 테스트케이스 2,4,5,6,7,8,10
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
}
