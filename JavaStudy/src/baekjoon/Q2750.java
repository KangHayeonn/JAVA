// 수 정렬하기 (백준 2750번)

// 선택정렬 구현
// 숫자를 정렬하기 위해 배열 사용
// 오름차순 -> Min를 찾고 해당 인덱스 부분이랑 교환
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
		// toString : 일차원 배열 출력, deepToString : 다차원 배열 출력할 때 사용
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
