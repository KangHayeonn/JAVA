// ü���� (���α׷��ӽ� LEVEL1)

// ver 3 : �׽�Ʈ���̽� �߰��ؼ� ����
// �Է°� > 5, [2,3,4] , [3,4,5]
// ��� > 4 
package codingtest_9th_2nd;

import java.util.Arrays;

public class Q1 {
	static public void main(String[] args) {
		Q1 s = new Q1();
		int lost[] = {2,3,4};
		int reserve[] = {1,2,3};
		System.out.println(s.solution(5, lost, reserve));
	}
	
	public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;
		int size = reserve.length-1;
		int size2 = lost.length-1;
		
		answer = n - lost.length; // ���Ҿ���� �л���
		
		Arrays.sort(lost);
		Arrays.sort(reserve);
	
		for(int i=0; i<lost.length; i++) {
			int key = lost[i];
			for(int j=0; j<reserve.length; j++) {
				if(reserve[j]==(key)) {
					answer++;
					reserve = delete(j, reserve, size--);
					lost = delete(i, lost, size2--);
					i--;
					break;
				}
				else continue;
			}
		}
		
		for(int i=0; i<lost.length; i++) {
			int key = lost[i];
			for(int j=0; j<reserve.length; j++) {
				if(reserve[j]==(key-1)||reserve[j]==(key+1)) {
					answer++;
					reserve = delete(j, reserve, size--);
					break;
				}
				else continue;
			}
		}
        
        return answer;
	}
	
	// ���� �Լ� ��ü������ ����
	public int[] delete(int idx, int[] arr, int size) {
		int[] newArr = new int[arr.length-1];
		for(int i=0; i<idx; i++) {
			newArr[i] = arr[i];
		}
		for(int i=idx; i<size; i++) {
			newArr[i] = arr[i+1];
		}
		return newArr;
	}
}
