// ���� ��� (���� 1946��)

// ver1 : ��ü Ž���ϸ鼭 ���������� ���������� ���ϸ� üũ
// �ð��ʰ��� ����
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1946 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseCount = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = Integer.parseInt(br.readLine());
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				String str = br.readLine();
				String parts[] = str.split(" ");
				int docScore = Integer.parseInt(parts[0]);
				int meetScore = Integer.parseInt(parts[1]);
				arr[j][0] = docScore;
				arr[j][1] = meetScore;
			}
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 0;
		
		for(int i=0; i<arr.length; i++) {
			int check =0;
			int docMin = arr[i][0];  // �� ���� (�����ɻ�)
			int meetMin = arr[i][1]; // �� ���� (�����ɻ�)
			
			for(int j=0; j<arr.length; j++) {
				if(docMin > arr[j][0]) {
					if(meetMin < arr[j][1]) check++;
					else continue;
				}
				else if(docMin < arr[j][0]) check++;
			}
			if(check==arr.length-1) select++;
		}
		return select;
	}
}*/

// ver2 : �˰��� ����
// ���� - �ð��ʰ�
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Q1946 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseCount = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = Integer.parseInt(br.readLine());
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				String str = br.readLine();
				String parts[] = str.split(" ");
				int docScore = Integer.parseInt(parts[0]);
				int meetScore = Integer.parseInt(parts[1]);
				arr[j][0] = docScore;
				arr[j][1] = meetScore;
			}
			//System.out.println("����: "+Arrays.deepToString(arr));
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0]-o2[0];
				}
				
			});
			//System.out.println("����: "+Arrays.deepToString(arr));
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 0;
		
		for(int i=0; i<arr.length; i++) {
			int check =0;
			int docMin = arr[i][0];  // �� ���� (�����ɻ�)
			int meetMin = arr[i][1]; // �� ���� (�����ɻ�)
			
			if(docMin==1 || meetMin==1) {
				select++;
				continue;
			}
			
			for(int j=0; j<i; j++) {
				if(meetMin < arr[j][1]) check++;
				else continue;
			}
			
			if(check==i) select++;
		}
		return select;
	}
}
*/
// ver3 : bufferReader -> Scanner
// ���� -> �ٸ� �˰��� ����غ����ҵ�

package baekjoon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Q1946 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int caseCount = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = sc.nextInt();
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				arr[j][0] = sc.nextInt();
				arr[j][1] = sc.nextInt();
			}
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0]-o2[0];
				}
				
			});
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
		sc.close();
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 0;
		
		for(int i=0; i<arr.length; i++) {
			int check =0;
			int docMin = arr[i][0];  // �� ���� (�����ɻ�)
			int meetMin = arr[i][1]; // �� ���� (�����ɻ�)
			
			if(docMin==1 || meetMin==1) {
				select++;
				continue;
			}
			
			for(int j=0; j<i; j++) {
				if(meetMin < arr[j][1]) check++;
				else continue;
			}
			
			if(check==i) select++;
		}
		return select;
	}
}