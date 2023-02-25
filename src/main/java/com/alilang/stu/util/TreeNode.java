package com.alilang.stu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {
    int id;
    int parentId;
    List<TreeNode> children;

    public TreeNode(int id, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    public List<TreeNode> convertToTreeNode(int[][] data) {
        List<TreeNode> nodes = new ArrayList<>();
        for (int[] row : data) {
            TreeNode node = new TreeNode(row[0], row[1]);
            nodes.add(node);
        }
        return nodes;
    }

    // 将节点构建成树结构
    public TreeNode buildTree(List<TreeNode> nodes) {
        // 根节点
        TreeNode root = null;
        // key：父节点id，value：父节点对象
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        // key：子节点id，value：子节点对象
        Map<Integer, TreeNode> childMap = new HashMap<>();
        for (TreeNode node : nodes) {
            int parentId = node.parentId;
            if (parentId == 0) {
                root = node;
            } else {
                parentMap.putIfAbsent(parentId, new TreeNode(parentId, 0));
                childMap.putIfAbsent(node.id, node);
            }
        }
        for (TreeNode node : nodes) {
            int parentId = node.parentId;
            if (parentId != 0) {
                TreeNode parentNode = parentMap.get(parentId);
                parentNode.children.add(node);
                childMap.put(node.id, node);
            }
        }
        return root;
    }
}
