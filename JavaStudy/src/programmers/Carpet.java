// ī�� (���α׷��ӽ� LEVEL2)

/*

/ / / / / / / /
/ o o o o o o /
/ o o o o o o /
/ o o o o o o /
/ o o o o o o /
/ / / / / / / /

*/
//24 / 12 = 2 -> 24 / 8 = 3 -> 24 / 6 = 4 
//(12+2)*2 +2*2 -> (8+2)*2 +3*2 -> (6+2)*2 +4*2
//32               26                24          -> brown

package programmers;

import java.util.Arrays;

public class Carpet {
	public static void main(String args[]) {
		Carpet s = new Carpet();
		System.out.println(Arrays.toString(s.solution(10, 2)));
	}
	public int[] solution(int brown, int yellow) {
		int answer[] = new int[2];
		int length = 0; // ����
		int width = 0; // ����
		int value = 0; // ���� �� �κ�
		int total = 0; // brown�� ������ �κ�
		int i=0;
		
		if(yellow==1) {
			answer[0] = yellow+2; // ����
			answer[1] = yellow+2; // ����
			return answer;
		}
		if(yellow==2) {
			answer[0] = yellow+2;
			answer[1] = 1+2;
			return answer;
		}
		
		for(i=yellow/2; i>0; i--) {
			if(yellow % i == 0) {
				value = yellow / i;
				total = (i+2)*2 + value*2;
				if(total==brown) {
					break;
				}
			}
		}

		width = i+2;
		length = value+2;
		
		answer[0] = width;
		answer[1] = length;
		
		return answer;
	}
}
