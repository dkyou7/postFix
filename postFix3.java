package com.algo;

import java.util.Scanner;
import java.util.Stack;

public class postFix3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i < 10; i++) {
			int n = sc.nextInt(); sc.nextLine();
			String input = sc.nextLine();
			String postFix = in2post(input);
			int result = calcPost(postFix);
			System.out.println("#" + i + " " + result);
		}
	}
	//후위표기법 수식을 계산해서 결과값을 리턴하는 메소드
	static int calcPost(String postFix) {
		Stack<Integer> stack = new Stack<>();	//계산할때는 스택에 숫자 넣기
		int n1,n2;
		for (int i = 0; i < postFix.length(); i++) {
			char ch = postFix.charAt(i);
			
			if(ch>='0' && ch<='9') stack.push(ch-'0');
			else {
				n1 = stack.pop();
				n2 = stack.pop();
				switch(ch) {
				case '+':
					stack.push(n1+n2);
					break;
				case '*':
					stack.push(n1*n2);
					break;
				}
			}
		}
		return stack.pop();
	}
	//중위표기법(infix) 수식을 후위표기법(postfix) 문자열로 변환해서 리턴하는 메소드
	static String in2post(String input) {
		String postFix ="";
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			
			if(ch>='0' && ch<='9') postFix +=ch;	//숫자는 그냥 결과쪽에 붙여준다.
			else if(ch==')') {
				char tmp;
				while((tmp=stack.pop()) != '(') {
					postFix +=tmp;
				}
			}else {	//숫자도, 닫히는 괄호도 아니면, (, +, * 중에 하나이다.
				//지금 들어가려는 연산자보다 top에 있는게 더 낮아야 push가 가능함. 같거나 높은애 다 pop 해야함.
				//아래 while 반복에는 pop할 조건을 걸어야함.
				while(!stack.isEmpty() && // 스택 비어있으면 안돼고.
						ch!='('&&	//'(' 지가 젤 높은줄 알고 들어가는 애라 팝하면 안됨.
						getPriority(stack.peek()) >= getPriority(ch)) {	//스택 젤위에 애가 ch보다 같거나 높으면 pop 해야함.
					postFix += stack.pop();
				}
				stack.push(ch);	// 위의 while에서 뺄놈 다 빼고 넣기.
			}
		}
		while(!stack.isEmpty()) {
			postFix += stack.pop();
		}
		return postFix;
	}
	
	//특정 연산자의 우선순위를 숫자로 리턴하는 메소드
	static int getPriority(char c) {
		if(c=='(') return 1;
		else if(c=='+') return 2;
		else if(c=='*') return 3;
		else return 0;
	}
}
