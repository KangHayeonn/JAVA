// 문자열 압축 (프로그래머스 LEVEL2)

package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class StringCompression {
	public static void main(String args[]) {
		String s = "aabbaccc";
		System.out.println(solution(s));
	}
	public static int solution(String s) {
		int answer=0;
        int i, end;
        String str;
		ArrayList<String> arr;
		ArrayList<Integer> str_length = new ArrayList<Integer>();
		if(s.length()==1) return 1;
		for(int cnt=1; cnt<s.length()/2+1; cnt++) {
			i=0;
			arr = new ArrayList<String>();
			while(i < s.length()) { 
				end = i+cnt;
				if(end > s.length()) end = s.length();
				str = s.substring(i,end); 
				i=end;
				arr.add(str);  
			}
			str_length.add(comparison(arr)); 
		}
		answer = Collections.min(str_length);
		return answer;
	}
	public static int comparison(ArrayList<String> arr) {
		String result = "";
		String a,b;
		int count=1;
		for(int i=0; i<arr.size()-1; i++) {
			a= arr.get(i);
			b= arr.get(i+1);
            
			if(a.equals(b)) { // ==이걸로 문자열 비교 안됨
				count++;
			}
			else {
				if(count==1) {
					result += arr.get(i);
				}
				else {
					result += count+arr.get(i); 
					count=1;
				}
			}
            
			if(i==arr.size()-2) {
				if(count==1) {
					result += arr.get(i+1);
				}
				else {
					result += count+arr.get(i+1);
					count=1;
				}
			}
        }
		return result.length();
	}
}
