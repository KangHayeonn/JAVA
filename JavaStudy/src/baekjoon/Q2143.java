// 두 배열의 합 (백준 골드3)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q2143 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int n = Integer.parseInt(br.readLine());
		int[] arrA = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] arrB = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dpA = new int[n][n];
		ArrayList<Integer> listA = new ArrayList<>();
		for(int i=0; i<n; i++) {
			dpA[i][i] = arrA[i];
			listA.add(dpA[i][i]);
			for(int j=i+1; j<n; j++) {
				dpA[i][j] = dpA[i][j-1] + arrA[j];
				listA.add(dpA[i][j]);
			}
		}
		
		int[][] dpB = new int[m][m];
		ArrayList<Integer> listB = new ArrayList<>();
		for(int i=0; i<m; i++) {
			dpB[i][i] = arrB[i];
			listB.add(dpB[i][i]);
			for(int j=i+1; j<m; j++) {
				dpB[i][j] = dpB[i][j-1] + arrB[j];
				listB.add(dpB[i][j]);
			}
		}
		
		Collections.sort(listA);
		Collections.sort(listB);
		
		int i=0, j=listB.size()-1, a, b; 
		long count=0, countA, countB, sum=0;
		while(i<listA.size() && j>=0) {
			sum = listA.get(i) + listB.get(j);
			
			if(sum == T) {
				a = listA.get(i);
				b = listB.get(j);
				
				countA=0; countB=0;

				while(i<listA.size() && a==listA.get(i)) {
					countA++;
					i++;
				}
				while(j>=0 && b==listB.get(j)) {
					countB++;
					j--;
				}
				
				count += countA*countB;
			}
			else if(sum > T) j--;
			else i++;
		}
		System.out.println(count);
	}
}
