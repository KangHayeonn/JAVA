// �� �����ϱ� (���� 2750��)

// �������� ����
// ���ڸ� �����ϱ� ���� �迭 ���
// �������� -> Min�� ã�� �ش� �ε��� �κ��̶� ��ȯ
package baekjoon;

import java.util.Scanner;

public class Q2750 {
	public static void main(String args[]) {
		Q2750 num = new Q2750();
		Scanner s = new Scanner(System.in);
		int count = s.nextInt();
		int arr[] = new int[count];
		
		for(int i=0; i<count; i++) {
			arr[i] = s.nextInt();
		}

		int[] newArr = num.selectionSort(arr);
		
		for(int i=0; i<count; i++) {
			if(i==count-1) System.out.print(newArr[i]);
			else System.out.println(newArr[i]);
		}
		// toString : ������ �迭 ���, deepToString : ������ �迭 ����� �� ���
		s.close();
	}
	
	public int[] selectionSort(int arr[]) {
		for(int i=0; i<arr.length; i++) {
			int min = arr[i];
			int idx = i;
			
			for(int j=i+1; j<arr.length; j++) {
				if(min > arr[j]) {
					min = arr[j];
					idx = j;
				}
			}
			
			if(min!=arr[i]) {
				arr[idx] = arr[i];
				arr[i] = min;
			}
		}
		return arr;
	}
}
