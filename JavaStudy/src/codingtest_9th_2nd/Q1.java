// ü���� (���α׷��ӽ� LEVEL1)

package codingtest_9th_2nd;

import java.util.Arrays;


public class Q1 {
	static public void main(String[] args) {
		Q1 s = new Q1();
		int lost[] = {3};
		int reserve[] = {1};
		System.out.println(s.solution(3, lost, reserve));
	}
	
	public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;
		int size = reserve.length-1;
		
		answer = n - lost.length;
		
		for(int i=0; i<lost.length; i++) {
			int key = lost[i];
			if(Arrays.stream(reserve).anyMatch(a-> a==(key-1))) {
				answer++;
				int idx = Arrays.binarySearch(reserve, key-1);
				reserve = delete(idx, reserve, size--);
				continue;
			}
			else if(Arrays.stream(reserve).anyMatch(a-> a==(key+1))) {
				answer++;
				int idx = Arrays.binarySearch(reserve, key+1);
				reserve = delete(idx, reserve, size--); // size-- �� ������ ArrayIndexOutOfBoundsException �߻� : ���� �迭 �ε����� ���� �ε��� ���� ��
				continue;
			}
			else if(Arrays.stream(reserve).anyMatch(a-> a==(key))) {
				answer++;
				int idx = Arrays.binarySearch(reserve, key+1);
				reserve = delete(idx, reserve, size--); // size-- �� ������ ArrayIndexOutOfBoundsException �߻� : ���� �迭 �ε����� ���� �ε��� ���� ��
				continue;
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
