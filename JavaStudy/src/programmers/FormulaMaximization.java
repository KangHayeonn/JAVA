// ���� �ִ�ȭ (���α׷��ӽ� LEVEL2 - Kakao)

/* [ �˰��� ]
 * 
 * 1. �̸� -, +, * ���������� ���� �� �ִ� �켱���� ����� ���� �̾Ƴ��� (���� �̿��ص���)
 * 2. ���ڿ��� ������ �������� �и���
 * 3. Ư�� ������ �켱������ �´��� Ȯ�� �� �� �κ��� ��� �� ���ο� �迭�� ����� �� (���� ���� �� �迭 �ε��� ����)
 * 4. �켱���� case �� ���� ���� ������ ���� resultList�� ����
 * 5. resultList �� ���� ū ���� �������� ���
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
