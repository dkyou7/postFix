package com.algo;

import java.util.Scanner;
import java.util.Stack;

public class postFix2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 1; i <=10; i++) {
			int len = sc.nextInt(); sc.nextLine();
			
			String input = sc.nextLine();
			String postFix = "";	//후위표기법 수식 만들어서 이어붙이기 진행할 예정
			
			Stack<Character> stack = new Stack<>();
			for (int j = 0; j < input.length(); j++) {
				char ch = input.charAt(j);
				
				if(ch>='0' && ch<='9') {	//읽은 글자가 숫자일 경우 붙인다.
					postFix += ch;
				}else {	//숫자가 아니면 무조건 '+'가 들어옴
					if(stack.isEmpty()) {	//'+'만 후보임으로 비었을 때만 push 가능하다.
						stack.push(ch);
					}else if(ch=='+'){	//스택에 뭔가 들어있는데 지금 넣고싶은건 +
						while(!stack.isEmpty()) {//안쪽에 있는거 pop해서 후위표기식에 이어붙이기.
							postFix += stack.pop();
						}
						stack.push(ch);	//스택 비워놓고 '+'를 push 해야함.
					}else if(ch=='*') {	//스택에 연산자가 뭔가 들어있는데 지금 넣고싶은건 *
						while(!stack.isEmpty() && stack.peek() == '*') {	//현재 들어갈 *보다 우선순위가 같거나 높은애들 다 뺴야함.
							postFix += stack.pop();
						}
						stack.push(ch);	//while 끝나기 위해 스택이 비거나 top에 나보다 낮은 연산자가 있을꺼야.
					}
				}
			}
			while(!stack.isEmpty()) { 	//앞 처리가 끝났는데 스택에 연산자가 남았다? 다 pop 해서 뒤에 붙이자.
				postFix += stack.pop();
			}
//			System.out.println(postFix);//	후위표기식 만든거 확인하기
			
			Stack<Integer> numStack = new Stack<>();
			for (int j = 0; j < postFix.length(); j++) {
				char ch = postFix.charAt(j);
				
				if(ch>='0' && ch<='9') {
					//char -> int 바꾸는 법 1
//					numStack.push(ch-'0');
					//char -> int 바꾸는 법 2
					numStack.push(Integer.parseInt(ch+""));
//					numStack.push((int)ch); <-- 이건 안된다.
				}else if(ch=='+'){ 	// 숫자가 아니면 연산자인데 이 예제에서는 '+'밖에 없음.
					int n1 = numStack.pop();
					int n2 = numStack.pop();
					numStack.push(n1+n2);
				}else if(ch=='*') {
					int n1 = numStack.pop();
					int n2 = numStack.pop();
					numStack.push(n1*n2);
				}
			}
			
			int result = numStack.pop();	//수식이 잘 이루어졌다면 스택에 마지막 연산 결과가 들어온다.
			System.out.printf("#%d %d\n",i,result);
		}
	}

}
