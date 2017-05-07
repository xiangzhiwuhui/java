package qunar;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//给定树的先序中序遍历序列，求该树的层次遍历序列
class Node{
	int data;
	Node left;
	Node right;
	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

class Tree{
	Node root;
	public Tree() {
		this.root = null;
	}
	
	void constructTree(int[] pre, int[] mid){
		this.root = this.constructTree(pre, 0, pre.length - 1, mid, 0, mid.length-1);
	}

	public Node constructTree(int[] pre, int i, int j, int[] mid, int k, int l) {
		if (i > j || k > l) {
			return null;
		}
		int rootD = pre[i];
		Node head = new Node(rootD);
		int rootI = findIndex(mid, rootD, k, l);
		int off = rootI - k - 1;
		
		Node left = constructTree(pre, i+1, i+1+off, mid, k, k+off);
		Node right = constructTree(pre, i+off+2, j, mid, rootI+1, l);
		head.left = left;
		head.right = right;
		return head;
		
	}

	public int findIndex(int[] mid, int rootD, int k, int l) {
		for (int i = k; i < mid.length; i++) {
			if (mid[i] == rootD) {
				return i;
			}
		}
		return -1;
	}
	
	void tranverse(){
		if (root == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
		while(!queue.isEmpty()){
			Node node = queue.poll();
			System.out.print(node.data);
			System.out.print(" ");
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		
	}
	
}

public class TreeTransvers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] pre = new int[n];
		int[] mid = new int[n];
		for (int i = 0; i < n; i++) {
			pre[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			mid[i] = sc.nextInt();
		}
		
		Tree tree = new Tree();
		tree.constructTree(pre, mid);
		tree.tranverse();
		

	}


}







