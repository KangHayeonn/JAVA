// ���ڿ� ���� (���α׷��ӽ� LEVEL2)

// �˰���
// (1) ���̺��� ���ڸ� �ڸ���.
// (2) �ڸ� ���ڿ��� �迭�� �־��ش�.
// (3) �迭���� �ݺ��Ǵ� ���ڿ��� �ִ��� Ȯ�� (-> �Լ�)
// (4) ������ �ݺ��Ǵ� ���� + �ش� ���ڿ� ������ �ۼ�
// (5) ������ �׳� ���ڿ��� �ۼ����ְ� ���� �ε����� �Ѿ��
// (6) �׷��� ���� ���ڿ��� ���̸� �����ش�.
// (7) �� ���̸� �迭�� ����ش�.
// (8) �迭�� ��� �� �ּҰ��� ���

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
            
			if(a.equals(b)) { // ==�̰ɷ� ���ڿ� �� �ȵ�
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
            
			if(i==arr.size()-2) { // ������ �κп��� if�� �ɸ� ��� else ���� ���εǴ� ���̽��� �����ϱ� ���� �߰�
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
