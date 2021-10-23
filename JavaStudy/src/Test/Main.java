package Test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static void solution(int numOfOperation, Operation[] operations) {
		
		boolean answer[] = new boolean[numOfOperation+1];
		Arrays.fill(answer, false);
		answer[0] =true; // 1번 branch는 항상 true
		
		for(int i=0; i<numOfOperation; i++) {
			String str = operations[i].type.toString();
			if("branch".equals(str)) {
				for(int j=1; j<=numOfOperation; j++) {
					if(answer[j]== false) {
						answer[j] = true;
						break;
					}
				}
			}
			else {
				answer[operations[i].value-1] = false;
			}
		}
		
		for(int i=0; i<=numOfOperation; i++) {
			if(answer[i]==true) {
				if(i==numOfOperation) System.out.print(i+1);
				System.out.print(i+1 + " ");
			}
		}
		
		if("1 2 4 6".equals("1 2 4 6 ")){
			System.out.println("동일");
		}
	}
	
	private static class InputData {
		int numOfOperation;
		Operation[] operations;
	}
	
	private static class Operation {
		OperationType type;
		Integer value;
		
		public Operation(OperationType type, Integer value) {
			this.type = type;
			this.value = value;
		}
	}
	
	private static enum OperationType {
		branch,
		merge;
	}
	
	private static InputData processStdin() {
		InputData inputData = new InputData();
		
		try(Scanner scanner = new Scanner(System.in)){
			inputData.numOfOperation = Integer.parseInt(scanner.nextLine());
			inputData.operations = new Operation[inputData.numOfOperation];
			
			for(int i=0; i< inputData.numOfOperation; i++) {
				String[] temp = scanner.nextLine().split(" ");
				
				Integer value = null;
				OperationType operationType = OperationType.valueOf(temp[0]);
				
				if(OperationType.merge == operationType) {
					value = Integer.valueOf(temp[1]);
				}
				
				inputData.operations[i] = new Operation(operationType, value);
			}
		} catch(Exception e) {
			throw e;
		}
		return inputData;
	}
	
	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();
		
		solution(inputData.numOfOperation, inputData.operations);
	}
}
