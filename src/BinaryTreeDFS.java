import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeDFS {

    public static void main(String[] args) {
        /*
                    1
                 2     3
              4    5
            6   7

            Pre-Order :  [1, 2, 4, 6, 7, 5, 3]  -> root + left + right
            In-Order :   [6, 4, 7, 2, 5, 1, 3]  -> left + root + right
            Post-Order : [6, 7, 4, 5, 2, 3, 1]  -> left + right + root
         */
        TreeNode tree = new TreeNode(1,
                new TreeNode(2, new TreeNode(4, new TreeNode(6), new TreeNode(7)), new TreeNode(5)),
                new TreeNode(3));

        System.out.println("Pre-Order Traversal");
        System.out.println(preOrderTraversal(tree));
        System.out.println(preOrderTraversalNonRecursive(tree));

        System.out.println("In-Order Traversal");
        System.out.println(inOrderTraversal(tree));
        System.out.println(inOrderTraversalNonRecursive(tree));

        System.out.println("Post-Order Traversal");
        System.out.println(postOrderTraversal(tree));
        System.out.println(postOrderTraversalNonRecursive(tree));
    }

    public static List<Integer> preOrderTraversal(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        result.addAll(preOrderTraversal(root.left));
        result.addAll(preOrderTraversal(root.right));

        return result;

    }

    public static List<Integer> preOrderTraversalNonRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode current;
        stack.push(root);

        while (!stack.isEmpty()) {
            current = stack.pop();
            result.add(current.val);
            if (current.right != null) { //LIFO - Last In First Out
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;

    }

    public static List<Integer> inOrderTraversal(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(inOrderTraversal(root.left));
        result.add(root.val);
        result.addAll(inOrderTraversal(root.right));

        return result;

    }

    public static List<Integer> inOrderTraversalNonRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }

        return result;
    }

    public static List<Integer> postOrderTraversal(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(postOrderTraversal(root.left));
        result.addAll(postOrderTraversal(root.right));
        result.add(root.val);

        return result;

    }
    public static List<Integer> postOrderTraversalNonRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode prev = root;
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();
            boolean hasChild = (current.left != null || current.right != null);
            boolean isPrevLastChild = (prev == current.right || (prev == current.left && current.right == null));

            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                result.add(current.val);
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }

        return result;
    }
}
