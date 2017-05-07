package javaconcurrent;

//结点结构
class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode(int x) { val = x; }
}

public class Main {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		
		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n4.left = n6;
		
		System.out.println(lowestCommonAncestor(root, n4, n3).val);
		
		
	}
	
	
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 左右子树探索时发现目标节点，则通过返回值标记
        if(root == null || p == root || q == root) {
            return root;
        }

        // 查看左子树中是否有目标结点，没有为null
        TreeNode l = lowestCommonAncestor(root.left,p,q);
        // 查看右子树中是否有目标结点，没有为null
        TreeNode r = lowestCommonAncestor(root.right,p,q);

        //都不为空，说明做右子树都有目标结点，则公共祖先就是本身
        if(l!= null && r!= null) {
            return root;
        }
        // 其他情况，则要继续向上标记，显示此节点下边有目标节点
        return l != null?l:r;
    }

}


