package UteCo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class test4 {
	public static void main(String args[]) {
		String s = "aaabbabbaccca";
		System.out.print(Arrays.toString(solution(s)));
	}
	public static class type {
		private int position;
		private int count;
		
		public type (int position, int count) {
			this.position = position;
			this.count = count;
		}
		
		public String toString() {
			return position + " : " + count;
		}
	}
	public static int[] solution(String s) {
		ArrayList<type> arr = new ArrayList<>();
		
		char a = s.charAt(0);
		int s_count = 0;
		int j=0;
		
		for(int i=0; i<s.length(); i++) {
			
			// s의 마지막 인덱스일 경우
			if(i==s.length()-1) {
				if(s.charAt(i)==a) s_count++;
				else {
					arr.add(new type(j, s_count));
					s_count = 1;
				}
				// 0번째 인덱스와 같을 경우
				if(s.charAt(i)==s.charAt(0)) {
					arr.set(0, new type(arr.get(0).position, arr.get(0).count+s_count));
				}
				// 0번째 인덱스와 다를 경우
				else {
					j++;
					arr.add(new type(j, s_count));
				}
				break;
			}
			
			if(s.charAt(i)==a) s_count++;
			else {
				arr.add(new type(j, s_count));
				j++;
				s_count = 1;
				a = s.charAt(i);
			}
		}
		
		//Arrays.sort(arr, (a,b) -> (arr.get(a).count - arr.get(b).count));
		Collections.sort(arr, new Comparator<type>() {
			@Override
			public int compare(type a, type b) {
				int x = a.count;
				int y = b.count;
				
				if(x < y) return -1; 
				else if(x > y) return 1; 
				else return 0; 
			}
		});
		
		int[] answer = new int[arr.size()];
		
		for(int idx=0; idx<arr.size(); idx++) {
			answer[idx] = arr.get(idx).count;
		}
		
		return answer;
	}
}
