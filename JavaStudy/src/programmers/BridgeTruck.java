// �ٸ��� ������ Ʈ�� (���α׷��ӽ� LEVEL2)

// ť�� �̿��ؼ� (���Լ���) ������ Ʈ���� ������ �ش�.
// �̶�, ���� Ʈ���� ������ ���Ը� ���� �� ����
// ���� ť�� ���� ��Ұ� ���̿� �������� ���� ���� ���� �ϳ��� �������ش�.
// �̶� ��� �������� ī��Ʈ�� ���� Ʈ���� ��ĭ�� �����̰� ���������� ��� �ð��� �����ش�.

// ver1 : ����
// �׽�Ʈ���̽� 2,4,5,6,7,8,10
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
