package lc105;

import java.util.HashMap;
import java.util.Map;

public class Lc105 {
}
/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 */

class Solution {
    private Map<Integer,Integer> map = new HashMap<Integer, Integer>();


    private TreeNode getTree(int[] preorder,int pLeft,int pRight,int[] inorder,int iLeft,int iRight){
        //递归终止条件
        if(pLeft>pRight)
            return null;
        TreeNode root = new TreeNode(preorder[pLeft]);
        //获取根节点索引
        int rootIndex = map.get(preorder[pLeft]);
        //递归获取左子树
        root.left = getTree(preorder,pLeft+1,pLeft+rootIndex-iLeft,inorder,iLeft,rootIndex-1);
        //递归获取右子树
        root.right = getTree(preorder,pLeft+rootIndex-iLeft+1,pRight,inorder,rootIndex+1,iRight);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //利用Hash表保存元素的索引
        for(int i=0;i<inorder.length;i++)
            map.put(inorder[i],i);
        return getTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
}

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}