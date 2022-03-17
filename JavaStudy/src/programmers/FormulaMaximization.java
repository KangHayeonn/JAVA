// 수식 최대화 (프로그래머스 LEVEL2 - Kakao)

/* [ 알고리즘 ]
 * 
 * 1. 미리 -, +, * 세가지에서 나올 수 있는 우선순위 경우의 수를 뽑아놓음 (순열 이용해도됨)
 * 2. 문자열을 수식을 기준으로 분리함
 * 3. 특정 수식이 우선순위에 맞는지 확인 후 그 부분을 계산 후 새로운 배열로 만들어 줌 (삭제 연산 시 배열 인덱스 주의)
 * 4. 우선순위 case 별 계산된 값을 절댓값을 취해 resultList에 저장
 * 5. resultList 중 가장 큰 값을 정답으로 출력
 * 
 */

package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FormulaMaximization {
	public static void main(String args[]) {
		String s = "50*6-3*2";
		System.out.println(solution(s));
	}
	public static long solution(String expression) {
		long answer = 0;
		String[][] caseList = {
				{"-", "+", "*"},
				{"-", "*", "+"},
				{"+", "-", "*"},
				{"+", "*", "-"},
				{"*", "+", "-"},
				{"*", "-", "+"},
		};
		
		String newExpression = "";
		
		for(int i=0; i<expression.length(); i++) {
			char c = expression.charAt(i);
			
			if(Character.isDigit(c)) {
				newExpression += c;
			} else {
				newExpression += ' ';
				newExpression += c;
				newExpression += ' ';
			}
		}
		
		String[] strArr = newExpression.split(" ");
		ArrayList<String> arr = new ArrayList<>();
		ArrayList<Long> resultList = new ArrayList<>();
		long result = 0;
		
		for(int i=0; i<6; i++) {
			arr = new ArrayList<>();
			for(int a=0; a<strArr.length; a++) {
				arr.add(strArr[a]);
			}
			
			for(int j=0; j<3; j++) {
				Iterator<String> it = arr.iterator();
				while(it.hasNext()) {
					String item = it.next();
					if(item.contains(caseList[i][j]) && item.length()==1) {
						int index = arr.indexOf(item); //  ex 1
						long num1 = Long.parseLong(arr.get(index-1));
						long num2 = Long.parseLong(arr.get(index+1));
						char op = item.charAt(0);
						result = 0;
						
						switch(op) {
							case '+' : result = num1 + num2; break;
							case '-' : result = num1 - num2; break;
							case '*' : result = num1 * num2; break;
							default : break;
						}
						
						for(int idx=0; idx<3; idx++) {
							arr.remove(index-1);
						}
						
						
						arr.add(index-1, String.valueOf(result));
						it = arr.iterator();
						
					}
				}
			}
			resultList.add(Math.abs(result));
		}
		
		answer = Collections.max(resultList);
	
		return answer;
	}
}
