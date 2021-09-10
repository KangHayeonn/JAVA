// 문자열 압축 (프로그래머스 LEVEL2)

// 알고리즘
// (1) 길이별로 문자를 자른다.
// (2) 자른 문자열을 배열에 넣어준다.
// (3) 배열에서 반복되는 문자열이 있는지 확인 (-> 함수)
// (4) 있으면 반복되는 갯수 + 해당 문자열 순으로 작성
// (5) 없으면 그냥 문자열을 작성해주고 다음 인덱스로 넘어간다
// (6) 그래서 최종 문자열의 길이를 구해준다.
// (7) 각 길이를 배열에 담아준다.
// (8) 배열의 요소 중 최소값을 출력

package codingtest_9th_2nd;

import java.util.ArrayList;
import java.util.Collections;

public class Q4 {
	static public void main(String args[]) {
		Q4 s = new Q4();
		System.out.println(s.solution("abcabcabcabcdededededede"));
	}
	public int solution(String s) {
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
	public int comparison(ArrayList<String> arr) {
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
            
			if(i==arr.size()-2) { // 마지막 부분에서 if에 걸릴 경우 else 여서 묵인되는 케이스를 포함하기 위해 추가
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
