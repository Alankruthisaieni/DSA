Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

 

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:
Input: root = [1], target = 1, k = 3
Output: []

Code:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public static ArrayList<TreeNode> NodeToRootPath(TreeNode root,int val){
        ArrayList<TreeNode> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        if(root.val==val){
            res.add(root);
            return res;
        }
        ArrayList<TreeNode> n1=NodeToRootPath(root.left,val);
        if(n1.size()>0){
            n1.add(root);
            return n1;
        }
        ArrayList<TreeNode> n2=NodeToRootPath(root.right,val);
        if(n2.size()>0){
            n2.add(root);
            return n2;
        }
        return res;
    }
    public static ArrayList<Integer> printKLevelsDown(TreeNode root,int k,ArrayList<Integer> res,TreeNode blocker){
         if(root==null || k<0 || root==blocker){
            return res;
        }
                System.out.println(root.val);
        if(k==0){
            res.add(root.val);
            return res;
        }
        res=printKLevelsDown(root.left,k-1,res,blocker);
        res=printKLevelsDown(root.right,k-1,res,blocker);
        return res;
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> res=NodeToRootPath(root,target.val);
        ArrayList<Integer> result=new ArrayList<>();
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<res.size();i++){
            result=printKLevelsDown(res.get(i),k-i,new ArrayList<>(),i==0?null:res.get(i-1));
            System.out.println(result);
            for(int j=0;j<result.size();j++){
                ans.add(result.get(j));
            }
        }
        return ans;
    }
}
