662. Maximum Width of Binary Tree
Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also counted into the length calculation.

It is guaranteed that the answer will in the range of 32-bit signed integer.
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Input: root = [1,3,null,5,3]
Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Pair{
    TreeNode node;
    int index;
    Pair(TreeNode Node,int Index){
        node=Node;
        index=Index;
    }
}
class Solution {
    
    public int widthOfBinaryTree(TreeNode root) {
        //use indexing for nodes to calculate width
        if(root==null){
            return 0;
        }
        int ans=0;
        Queue<Pair> q=new ArrayDeque<>();//use level order traversal
        q.add(new Pair(root,0));
        while(q.size()>0){
            int min=q.peek().index;
            int size=q.size();
            int first=0,last=0;
            for(int i=0;i<size;i++){
                //subtract min value from all indexes to avoid overflow
                int s=q.peek().index-min;
                TreeNode n=q.peek().node;
                q.remove();
                if(i==0){
                   first=s; 
                }
                else if(i==size-1){
                    last=s;
                }
                //System.out.println(n);
                if(n.left!=null){
                    q.add(new Pair(n.left,(s*2)+1));
                }
                if(n.right!=null){
                    q.add(new Pair(n.right,(s*2)+2));
                }
            }
            ans=Math.max(ans,last-first+1);
        }
        return ans;
    }
}
T.C:O(N)
S.C:O(N)
