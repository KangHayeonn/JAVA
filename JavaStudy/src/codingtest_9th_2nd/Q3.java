// ũ���� �����̱� ���� (���α׷��ӽ� LEVEL1)

// �˰���
// (1) moves ���� �־��� ��� �߿��� �ش� board�� �ε����� ��
// (2) (1)���� ���, 0�� �ƴ� ���� ������ �� ���ڸ� ���ο� �迭�� ����
// (3) (1)���� ���, 0�̸� continue
// (4) �ش� �迭�� ��� �������� �� ���Լ��� �̵�
// (5) �迭�� ��Ҹ� ���鼭 �ڽ��� ���� ���ڰ� �ڽŰ� ������ ��� �������ְ� �ٽ� ���Լ��� ��͸� ������.
// (6) ��� ���� �� �κ� �տ� �����Ѱ� ������ �ش� ����Լ��� return
// (7) �������ٶ��� ���ΰ� �ڽ��� ���� ī��Ʈ ����� �ϱ� ������ answer +2 ���ش�.
// (8) ���������� ������ ���� answer�� ���

// ver1 : ����
// �׽�Ʈ 4,7,8,9,10,11 ����
package codingtest_9th_2nd;

import java.util.ArrayList;

public class Q3 {
	static public void main(String args[]) {
		Q3 s = new Q3();
		int[][] board = {{0,0,0,0,0},
						{0,0,1,0,3},
						{0,2,5,0,1},
						{4,2,4,4,2},
						{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		System.out.println(s.solution(board, moves));
		return;
	}
	
	public int solution(int[][] board, int[] moves) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
        int answer = 0;
   
        for(int i=0; i<moves.length; i++) {
        	switch(moves[i]) {
	        	case 1: 
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][0] != 0) {
	        				arr.add(board[j][0]);
	        				board[j][0] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 2:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][1] != 0) {
	        				arr.add(board[j][1]);
	        				board[j][1] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 3:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][2] != 0) {
	        				arr.add(board[j][2]);
	        				board[j][2] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 4:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][3] != 0) {
	        				arr.add(board[j][3]);
	        				board[j][3] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 5:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][4] != 0) {
	        				arr.add(board[j][4]);
	        				board[j][4] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
        	}
        }
        
        answer = comparison(arr, answer);
        return answer;
    }
	
	public int comparison(ArrayList<Integer> arr, int answer) {
		//arr = new ArrayList<Integer>(); // ���� ArrayList�� �ʱ�ȭ ������
		int index;
		while(arr.size()>1) {
			for(index=1; index<arr.size(); index++) {
				if(arr.get(index-1)==arr.get(index)) {
					arr.remove(index);
					arr.remove(index-1);
					answer = answer+2;
					return comparison(arr, answer);
				}
			}
			if(index==arr.size()) break; // �̺κ��� ��¥ ������� (1)
		}
		return answer; // �̺κ��� ��¥ ������� (2)
	}
}
