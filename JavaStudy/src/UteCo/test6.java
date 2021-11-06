package UteCo;

import java.util.Stack;

public class test6 {
	public static void main(String args[]) {
		String[][] plans = {{"����", "8AM",  "10AM"},{"������", "6PM","2PM"},{"�ѱ�","10AM","7PM"}};
		System.out.print(solution(23, plans));
	}
	
	public static String solution(double time, String[][] plans) {
		String answer= "";
		Stack<String> stack = new Stack<>(); // �� �� �ִ� �������� ��� ��
		double departTime = 0; // ��߽ð�
		double arriveTime = 0; // �����ð�
		
		double Friday_go = 9.5;  // �� ��ٽð�
		double Friday_back = 18; // �� ��ٽð�
		double Monday_go = 13;   // �� ��ٽð�
		double Monday_back = 18; // �� ��ٽð�
		
		double sum = 0;
		
		for(int i=0; i<plans.length; i++) {

			// ��� �ð�
			if(plans[i][1].contains("AM")) departTime = Double.parseDouble(plans[i][1].replace("AM", ""));
			else departTime = Double.parseDouble(plans[i][1].replace("PM", "")) + 12;
			
			// ���� �ð�
			if(plans[i][2].contains("AM")) arriveTime = Double.parseDouble(plans[i][2].replace("AM", ""));
			else arriveTime = Double.parseDouble(plans[i][2].replace("PM", "")) + 12;
			
			// ��
			if(departTime <= Friday_go) sum += Friday_back-Friday_go;
			else if(Friday_go < departTime && departTime <Friday_back) sum += Friday_back - departTime;
			else sum += 0;
			
			// ��
			if(arriveTime <= Monday_go) sum += 0;
			else if(Monday_go < arriveTime && arriveTime <= Monday_back) sum += arriveTime - Monday_go;
			else sum += Monday_back - Monday_go;
			
			if(sum <= time) {
				stack.add(plans[i][0]);
				if(i==plans.length-1) answer = stack.pop();
			}
			else {
				if(!stack.isEmpty()) answer = stack.pop();
				else break;
			}
		}
		
		
		return answer;
	}
}
