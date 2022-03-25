// 가장 큰 수 (Step1-2)
package programmersSchool;

import java.util.*;

// ver 1 : 문자열 순열 문제 -> 시간초과
/*
public class LargestNumber {
	 static String result = "";
	 public static void main(String args[]) {
		 int[] numbers = {0, 0, 0, 0, 0};
		 System.out.println(solution(numbers));
	 }
	 public static String solution(int[] numbers) {
		 String answer = "";
	     int len= numbers.length;
	     String[] numArr = new String[len];
	     // arr = new ArrayList<>();

	     // 순열에서 사용
	     boolean[] visited = new boolean[len];
	     String[] output = new String[len];


	     for(int i=0; i<len; i++) {
	         numArr[i] = Integer.toString(numbers[i]);
	         result += numArr[i];
	     }
			permutation(numArr, output, visited, 0, len, len);

	     // Collections.sort(arr, Collections.reverseOrder());
	     // answer = Integer.toString(arr.get(0));
	     answer = result;
	     return answer;
	 }
	// 문자열 순열
	public static void permutation(String[] numArr, String[] output, boolean[] visited, int depth, int n, int r) {
		if(depth == r) {
			String s = "";
			for(int j=0; j<output.length; j++) {
				s += output[j];
			}

         if(result.compareTo(s) < 0) {
             result = s;
             // System.out.println(result);
         } 
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = numArr[i];
				permutation(numArr, output, visited, depth+1, n, r);
				visited[i] = false;
			}
		}
	}
}
*/

// ver 2 : 문자열 정렬 -> 두가지를 더해서 정렬시키는게 keypoint

public class LargestNumber {
	static PriorityQueue<type> pq;
	public static class type implements Comparable<type>{
		private String s;
		
		public type (String s) {
			this.s = s;
		}
		
		@Override
		public int compareTo(type t) {
         String str1 = this.s;
         String str2 = t.s;
         return (str2+str1).compareTo(str1+str2);
		}
	 }
	 public static void main(String args[]) {
		 int[] numbers = {33, 30, 340, 34, 343};
		 System.out.println(solution(numbers));
	 }
	 public static String solution(int[] numbers) {
		 String answer = "";
	     int len= numbers.length;
	     pq = new PriorityQueue<>();
	
	     for(int i=0; i<len; i++) {
	         String s = Integer.toString(numbers[i]);
	         pq.add(new type(s));
	     }

	     while(!pq.isEmpty()) {
	    	 answer += pq.poll().s;
	     }
	     
	     if(answer.charAt(0) == '0') {
	    	 answer = "0";
	     }
	
	     return answer;
	 }
}
