
import java.util.*;

public class Main {

	class Node {
		int val;
		Node left;
		Node right;

		Node(int val) {
			this.val = val;
			left = right = null;

		}
	}

	static int Max = Integer.MIN_VALUE;
	static int Min = Integer.MAX_VALUE;
	static int pre = -1;
	static int suc = -1;

	public static void main(String[] args) {
		Main obj = new Main();
		System.out.println("Generate Random array:");
		int[] ar = obj.generateRandomArray(10);
		System.out.println(Arrays.toString(ar));

		Node root = obj.insertLevelOrder(ar, null, 0, 10);
		System.out.println();
		System.out.println("height: " + obj.height(root));
		System.out.println("In Order Traversal:");
		obj.inOrderTraversal(root);
		System.out.println();
		System.out.println("Pre Order Traversal:");
		obj.PreOrderTraversal(root);
		System.out.println();
		System.out.println("Post Order Traversal:");
		obj.PostOrderTraversal(root);
		System.out.println();
		boolean result = obj.search2(root, 5);
		System.out.println(result);

		// *****
		/*
		 * System.out.println("Find parent node"); Node node=obj.getParent(root,1);
		 * System.out.println(node.val);
		 */

		// creating a binary search tree from an array
		int[] arr = { 4, 6, 8, 3, 5, 6, 7, 4, 3, 2 };
		System.out.println("Creating a binary search tree from an array: ");
		Node outNode = obj.sortedArrayToBST(arr);
		obj.PreOrderTraversal(outNode);

		// MIN Node of Binary tree
		System.out.println();
		obj.minNode(outNode);
		System.out.println("Min: " + Main.Min);
		// Max Node of Binary tree
		obj.maxNode(outNode);
		System.out.println("Max: " + Main.Max);

		// Min and Max of BST
		int Minnimum = obj.findMin(outNode);
		int Maxixum = obj.findMax(outNode);
		System.out.println("Minnimum: " + Min);
		System.out.println("Maximum: " + Max);

		// Pre & Suc of BST
		obj.findPreSuc(outNode, pre, suc, 3);
		System.out.println("Predecessor: " + pre);
		System.out.println("Successor: " + suc);

	}

	public int[] generateRandomArray(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = (int) (Math.random() * 10);
		}
		return a;
	}

	// Creating Binary Tree from array
	public Node insertLevelOrder(int[] arr, Node root, int i, int n) {

		if (i < n) {

			Node temp = new Node(arr[i]);
			root = temp;

			root.left = insertLevelOrder(arr, root.left, 2 * i + 1, n);
			root.right = insertLevelOrder(arr, root.right, 2 * i + 2, n);

		}
		return root;
	}

	// Creating Binary Search Tree from array
	public Node sortedArrayToBST(int[] nums) {

		Arrays.sort(nums);
		System.out.println("Sorted array: " + Arrays.toString(nums));
		if (nums.length == 0)
			return null;
		return insertion(nums, 0, nums.length - 1);
	}

	public Node insertion(int[] arr, int left, int right) {
		if (left > right)
			return null;
		int midpoint = left + (right - left) / 2;
		Node node = new Node(arr[midpoint]);
		node.left = insertion(arr, left, midpoint - 1);
		node.right = insertion(arr, midpoint + 1, right);

		return node;
	}

	public int height(Node root) {
		if (root == null) {
			return -1;
		}

		int ht = Math.max(height(root.left), height(root.right)) + 1;
		return ht;
	}

	public void inOrderTraversal(Node root) {

		if (root == null) {
			return;
		}
		inOrderTraversal(root.left);
		System.out.print(root.val + " ");
		inOrderTraversal(root.right);
	}

	public void PreOrderTraversal(Node root) {

		if (root == null) {
			return;
		}
		System.out.print(root.val + " ");
		PreOrderTraversal(root.left);
		PreOrderTraversal(root.right);

	}

	public void PostOrderTraversal(Node root) {

		if (root == null) {
			return;
		}
		PostOrderTraversal(root.left);
		PostOrderTraversal(root.right);
		System.out.print(root.val + " ");

	}

	// searching for Binary Search Tree- log (N)
	public boolean search(Node node, int val) {
		if (node == null) {
			return false;
		}
		boolean isPresent = false;
		while (node != null) {
			if (val < node.val) {
				node = node.left;
			} else if (val > node.val) {
				node = node.right;
			} else {
				isPresent = true;
				break;
			}
		}
		return isPresent;
	}

	// searching for Binary Tree-O(N)
	public boolean search2(Node node, int key) {

		if (node == null) {
			return false;
		}

		if (node.val == key) {
			return true;
		}

		return search2(node.left, key) || search2(node.right, key);
	}

	// **pre-order trversal
	public Node getParent(Node node, int key) {

		if (node == null || node.val == key) {
			return null;
		}

		if ((node.left != null && node.left.val == node.val) || (node.right != null && node.right.val == node.val)) {
			return node;
		}

		Node l = getParent(node.left, key);

		if (l != null) {
			return l;
		}

		l = getParent(node.right, key);

		return l;
	}

	// binary tree
	public void minNode(Node root) {
		if (root == null)
			return;

		if (root.val < Min) {
			Min = root.val;
		}

		minNode(root.left);
		minNode(root.right);

	}

	// binary tree
	public void maxNode(Node root) {
		if (root == null) {
			return;
		}

		if (root.val > Max) {
			Max = root.val;
		}

		maxNode(root.left);
		maxNode(root.right);

	}

	// Find Min in BST
	public int findMin(Node root) {
		if (root == null) {
			System.out.println("Tree is Empty");
		} else if (root.left == null) {
			return root.val;
		}
		return findMin(root.left);
	}

	// Find Max in BST
	public int findMax(Node root) {
		if (root == null) {
			System.out.println("Tree is Empty");
		} else if (root.right == null) {
			return root.val;
		}
		return findMax(root.right);
	}

	// ***
	public void findPreSuc(Node root, int pre, int suc, int key) {

		if (root == null) {
			return;
		}

		if (root.val == key) {

			if (root.left != null) {
				Node temp = root.left;
				while (temp.right != null)
					temp = temp.right;
				pre = temp.val;
			}

			if (root.right != null) {
				Node temp2 = root.right;
				while (temp2.left != null)
					temp2 = temp2.left;
				suc = temp2.val;
			}
			return;
		}

		if (root.val > key) {
			suc = root.val;
			findPreSuc(root.left, pre, suc, key);
		} else {

			pre = root.val;
			findPreSuc(root.right, pre, suc, key);
		}
	}

}
