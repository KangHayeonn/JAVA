// �ٸ��� ������ Ʈ�� (���α׷��ӽ� LEVEL2)

/* [ �˰��� ]
 * 
 * 1. ���� ���Ʈ���� ���Ը� ������ �迭�� ����
 * 2. �ش� ���Ʈ���� �迭�� ù��° �ε����� ���Ҹ� ���� sum�� ����
 * 3. sum�� �ٸ��� �ߵ� �� �ִ� ���Ը� ���� ������ ť�� ����(time++) -> ���� ���Ʈ�� �ε����� �Ѿ
 * 4. sum�� �ٸ��� �ߵ� �� �ִ� ���Ը� �Ѿ��� ��� -> ť�� 0�� �߰�(time++)
 * 5. �ش� ť�� ���̰� �ٸ��� ���̸� ���� ���� ������ 3-4���� �ݺ�
 * 6. ���� ť�� ���̰� �ٸ� ���̸� �Ѿ��� ��� ť���� �ϳ��� ���� -> �ش� ���Ҹ� sum���� ����
 * 7. ���� ���Ʈ���� �� ����� ��� ť�� isEmpty�� �� ������ time++
 */

// ver1 : ����
// �׽�Ʈ���̽� 2,4,5,6,7,8,10
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

// ver2 : ����

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
		int sum = 0; // �ٸ����� �ö� Ʈ������ ����
		
		for(int i=0; i<truck_weights.length;) {
			sum += truck_weights[i];
			if(sum <= weight) { // ** �߿� (3)   == ������ ���� x
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
		
		while(bridge.size()<bridge_length) { // ** �߿� (2)
			bridge.add(-1); 
			time++; 
		}
		
		while(!bridge.isEmpty()) {
			//bridge.poll();
			if(bridge.poll()!=-1) time++; // ** �߿� (1)
			//time++;
		}
		
		return time;
	}
}
