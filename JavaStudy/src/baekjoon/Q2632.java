// �����Ǹ� (���� 2632��)

/* [ �˰��� ]
 * 
 * - �κ� ������ ���� �̿��� A���ڿ� B���� �������� ���� �� �ִ� ���� �迭�� ����� (SumA, SumB)
 * - SumA���� �ϳ� SumB���� �ϳ� ����(a,b)�� ��� ������ �� ���� ����� �ϴ� ���� ũ��� �������� ��
 * - ���� ��� �ش� ���̽��� ����� ���� ������ (a�� A�� ��� �����ϰ� �ִ���, b�� B�� ��� �����ϰ� �ִ���)
 * 
 * # ������
 * 1. ���ӵ� ������ �Ǹ� (�κ� ������ ���� ���� �� ���)
 * 2. �����̹Ƿ� ù��° �ε����� ������ �ε����� ����Ǿ�� ��
 *  
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Q2632 {
	static ArrayList<Integer> SumA = new ArrayList<>();
	static ArrayList<Integer> SumB = new ArrayList<>();
	
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner(System.in);
		int buySize = sc.nextInt();
		int m = sc.nextInt(); // A���� ������ ����
		int n = sc.nextInt(); // B���� ������ ����
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int buySize = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // A���� ������ ����
		int n = Integer.parseInt(st.nextToken()); // B���� ������ ����
		*/
		int[] A = new int[m];
		int[] B = new int[n];
		
		for(int i=0; i<m; i++) {
			//A[i] = Integer.parseInt(br.readLine());
			A[i] = sc.nextInt();
		}
		for(int i=0; i<n; i++) {
			//B[i] = Integer.parseInt(br.readLine());
			B[i] = sc.nextInt();
		}
		
		// �κ� ������ �� ���ϱ� (A)
		for(int i=0; i<m; i++) {
			boolean[] check = new boolean[m];
			check[i] = true;
			if(i==0) funcSum(SumA, A, check, i+1, A[i], buySize, m);
			else funcSum(SumA, A, check, i+1, A[i], buySize, m-1);
			/*
			System.out.print("Start : " + i +" -> " );
			for(int j=0; j<SumA.size(); j++) {
				System.out.print(SumA.get(j) +" ");
			}
			System.out.println();*/
		}
		
		// �κ� ������ �� ���ϱ� (B)
		for(int i=0; i<n; i++) {
			boolean[] check = new boolean[n];
			check[i] = true;
			if(i==0) funcSum(SumB, B, check, i+1, B[i], buySize, n);
			else funcSum(SumB, B, check, i+1, B[i], buySize, n-1);
		}
		
		SumA.add(0); // �Ȼ̴� ��쵵 �����ؾ���
		SumB.add(0);
		
		Collections.sort(SumA);
		Collections.sort(SumB);
		
		/*
		System.out.println("A = ");
		for(int i=0; i<SumA.size(); i++) {
			System.out.print(SumA.get(i)+" ");
		}
		System.out.println("\nB = ");
		for(int i=0; i<SumB.size(); i++) {
			System.out.print(SumB.get(i)+" ");
		}
		System.out.println();*/
		
		int answer = 0; // ���� �� (���ڸ� �Ǹ��ϴ� ����� ���� ��)
		int aIdx = 0;
		int bIdx = SumB.size()-1;
		
		while(aIdx < SumA.size() && bIdx >= 0) {
			int valueA = SumA.get(aIdx);
			int valueB = SumB.get(bIdx);
			int sum = valueA + valueB;
			System.out.println("check :  : " + aIdx + " / " + bIdx);
			System.out.println("value : " + valueA + " / " + valueB);
			if(sum == buySize) {
				int countA = 0, countB = 0;
				
				/*
				for(int i=aIdx; i<SumA.size(); i++) {
					if(SumA.get(i) == valueA) {
						countA++; 
						aIdx++;
					}
				}
				for(int i=bIdx; i>=0; i--) {
					if(SumB.get(i) == valueB) {
						countB++;
						bIdx--;
					}
				}*/
				
				// �Ʒ� �κ��� �и��ؼ� �� �ּ��� ���� for-if�� ���� �ð��ʰ�
				while(aIdx < SumA.size() && SumA.get(aIdx)==valueA) {
					countA++; 
					aIdx++;
				}
				while(bIdx >= 0 && SumB.get(bIdx)==valueB) {
					countB++; 
					bIdx--;
				}
				answer += countA * countB;
				//aIdx++;
				//bIdx--;
			}
			if(sum < buySize) aIdx++; // �̺κ� ������ ����� ������ 0���� ������ length-1���� ��
			else if(sum > buySize) bIdx--; 
		}
		
		System.out.print(answer);
	}
	
	public static void funcSum(ArrayList<Integer> arr, int[] List, boolean[] check, int idx, int sum, int target, int count) {
		if(count==0) return;
		if(idx==List.length) idx = 0;
		
		arr.add(sum);

		if(check[idx]==false ) {
			sum += List[idx];
			check[idx] = true;
			//System.out.println(List[idx] + " -> " + sum);
			if(sum <= target) funcSum(arr, List, check, idx+1, sum, target, count-1);
		}
	}
}
