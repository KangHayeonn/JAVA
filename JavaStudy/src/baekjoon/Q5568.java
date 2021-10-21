// 카드 놓기 (백준 5568번)

// Set 사용
package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

public class Q5568 {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 주어지는 카드의 수
		int k = Integer.parseInt(br.readLine()); // 뽑을 카드의 수
		String arr[] = new String[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = br.readLine();
		}
		
		HashSet<String> set = new HashSet<>();
		boolean visited[] = new boolean[n];
		combi(arr, visited, 0, n, k, set);
		
		Iterator<String> iterSet = set.iterator();
		while(iterSet.hasNext()) {
			System.out.print(iterSet.next() + " ");
		}
		System.out.print(set.size());
	}
	
	public static void combi(String arr[], boolean visited[], int idx, int n, int k, HashSet<String> set) {
		if(k==0) {
			setFunction(arr, visited, n, set);
			return;
		}
		
		for(int i=idx; i<n; i++) {
			visited[i] = true;
			combi(arr, visited, i + 1, n, k - 1, set);
			visited[i] = false;
		}
	}
	
	public static void setFunction(String arr[], boolean visited[], int n, HashSet<String> set) {
		String number = "";
		for(int i=0; i < n; i++) {
			if(visited[i]) {
				System.out.println(arr[i]);
				number += arr[i];
			}
		}
		set.add(number);
		System.out.println("잉: " +number);
		number = "";
		for(int i = n-1; i >= 0; i--) {
			if(visited[i]) {
				number += arr[i];
				System.out.println(arr[i]);
			}
		}
		set.add(number);
		System.out.println("잉2: " +number);
	}
	
}
