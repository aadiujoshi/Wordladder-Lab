public class bfs {
    static class TreeNode{
        int val;
        TreeNode left, right;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static void main(String agdsa[]){
        // createTree(new TreeNode(0), 0, 0, 3);

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);


        for(;;){
            break;
        }
    }

    public static void printTreeBF(TreeNode node){
        
        System.out.print(node.left.val + "  ");
        System.out.print(node.right.val + "  ");
    }

    public static TreeNode createTree(TreeNode node, Integer ind, int layerInd, int maxLayers){
        ind++;

        node.left = new TreeNode(ind);
        createTree(node.left, ind, layerInd+1, maxLayers);

        node.right = new TreeNode(ind);
        createTree(node.right, ind, layerInd+1, maxLayers);

        if(layerInd >= maxLayers)
            return node;
        return null;
    }
}
