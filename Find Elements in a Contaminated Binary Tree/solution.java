class FindElements {
    private Set<Integer> vis = new HashSet<>();

    public FindElements(TreeNode root) {
        root.val = 0;
        dfs(root);
    }

    private void dfs(TreeNode root) {
        vis.add(root.val);
        if (root.left != null) {
            root.left.val = root.val * 2 + 1;
            dfs(root.left);
        }
        if (root.right != null) {
            root.right.val = root.val * 2 + 2;
            dfs(root.right);
        }
    }

    public boolean find(int target) {
        return vis.contains(target);
    }
}
