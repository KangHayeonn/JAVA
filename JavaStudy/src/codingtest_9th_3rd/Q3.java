// 키패드 누르기 (프로그래머스 LEVEL1)

// ver1
// (1) 이차원 배열을 이용한 keypad 좌표값 설정
// (2) 현재 왼손과 오른손의 위치를 좌표값으로 설정
// (3) 이동할 numbers의 좌표랑 왼손 오른손의 좌표를 비교하며 계산
// (4) 이동에 따라 L, R 부분을 answer에 추가해주고 출력

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
		int[] leftHand = {0,0}; // 왼손의 위치 (시작지점: *)
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
