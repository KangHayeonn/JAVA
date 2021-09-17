// Ű�е� ������ (���α׷��ӽ� LEVEL1)

// ver1
// (1) ������ �迭�� �̿��� keypad ��ǥ�� ����
// (2) ���� �޼հ� �������� ��ġ�� ��ǥ������ ����
// (3) �̵��� numbers�� ��ǥ�� �޼� �������� ��ǥ�� ���ϸ� ���
// (4) �̵��� ���� L, R �κ��� answer�� �߰����ְ� ���

package codingtest_9th_3rd;

public class Q3 {
	static public void main(String args[]) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		Q3 s = new Q3();
		System.out.println(s.solution(numbers, "right"));
	}
	public String solution(int[] numbers, String hand) {
		String answer = "";
		int[][] keypad = {{1,0},{0,3},{1,3},{2,3},{0,2},{1,2},{2,2},{0,1},{1,1},{2,1}}; // 0 1 2 3 4 5 6 7 8 9
		int[] leftHand = {0,0}; // �޼��� ��ġ (��������: *)
		int[] rightHand = {2,0};

		for(int i : numbers) {
			if(i==1 || i==4 || i==7) {
				answer += "L";
				leftHand[0] = keypad[i][0];
				leftHand[1] = keypad[i][1];
			}
			else if(i==3 || i==6 || i==9) {
				answer += "R";
				rightHand[0] = keypad[i][0];
				rightHand[1] = keypad[i][1];
			}
			else {
				int leftDistance = Math.abs(keypad[i][0]-leftHand[0]) + Math.abs(keypad[i][1]-leftHand[1]);
				int rightDistance = Math.abs(keypad[i][0]-rightHand[0]) + Math.abs(keypad[i][1]-rightHand[1]);
				
				if(leftDistance < rightDistance) {
					answer += "L";
					leftHand[0] = keypad[i][0];
					leftHand[1] = keypad[i][1];				
				}
				else if(leftDistance > rightDistance) {
					answer += "R";
					rightHand[0] = keypad[i][0];
					rightHand[1] = keypad[i][1];
				}
				else {
					if(hand.equals("left")) {
						answer += "L";
						leftHand[0] = keypad[i][0];
						leftHand[1] = keypad[i][1];
					}
					else {
						answer += "R";
						rightHand[0] = keypad[i][0];
						rightHand[1] = keypad[i][1];
					}
				}
			}
		}
		return answer;
	}
}
