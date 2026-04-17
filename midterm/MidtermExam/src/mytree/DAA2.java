package mytree;

// AVL Tree = Height-Balanced (HB) Tree

public class DAA2 extends DAA1 {

	// 4. isHeightBalanced() [10 points]
	public static boolean isHeightBalanced(MyTree t) {
        if (t.getEmpty()) {
            return true;
        }
        int leftHeight = MyTreeOps.height(t.getLeft());
        int rightHeight = MyTreeOps.height(t.getRight());
        boolean currentBalanced = Math.abs(leftHeight - rightHeight) <= 1;
        return currentBalanced && isHeightBalanced(t.getLeft()) && isHeightBalanced(t.getRight());
    }

	// 5. insertHB() [10 points]
	public static MyTree insertHB(int n, MyTree t) {
        if (t.getEmpty()) {
            return new MyTree(n, new MyTree(), new MyTree());
        }
        int currentValue = t.getValue();
        if (n < currentValue) {
            MyTree newLeft = insertHB(n, t.getLeft());
            MyTree updatedTree = new MyTree(currentValue, newLeft, t.getRight());
            return rebalanceForLeft(updatedTree);
        } 
        else if (n > currentValue) {
            MyTree newRight = insertHB(n, t.getRight());
            MyTree updatedTree = new MyTree(currentValue, t.getLeft(), newRight);
            return rebalanceForRight(updatedTree);
        } 
        else {
            return t; 
        }
	}

	// rebalanceForLeft is called when the left subtree of t may have
	// grown taller by one notch.
	// If it is indeed taller than the right subtree by two notches,
	// return a height-balanced version of t using single or double rotations.
	// The subtrees of t are assumed to be already height-balanced and
	// no effort is made to rebalance them.
	//
	// Likewise, for the case of the right subtree -> rebalanceForRight
	// Both rebalanceForLeft & rebalanceForRight will be used by insertHB() and deleteHB()
	// 6. rebalanceForLeft() [15 points]
	private static MyTree rebalanceForLeft(MyTree t) {
		int leftHeight = MyTreeOps.height(t.getLeft());
        int rightHeight = MyTreeOps.height(t.getRight());

        if (leftHeight - rightHeight == 2) {
            MyTree leftNode = t.getLeft();
            int leftLeftHeight = MyTreeOps.height(leftNode.getLeft());
            int leftRightHeight = MyTreeOps.height(leftNode.getRight());

            if (leftLeftHeight >= leftRightHeight) {
                MyTree newRight = new MyTree(t.getValue(), leftNode.getRight(), t.getRight());
                return new MyTree(leftNode.getValue(), leftNode.getLeft(), newRight);
            } 

            else {
                MyTree leftRightNode = leftNode.getRight();
                MyTree newLeft = new MyTree(leftNode.getValue(), leftNode.getLeft(), leftRightNode.getLeft());
                MyTree newRight = new MyTree(t.getValue(), leftRightNode.getRight(), t.getRight());
                return new MyTree(leftRightNode.getValue(), newLeft, newRight);
            }
        }
        return t;
	}
	
	// 7. rebalanceForRight() [15 points]
	private static MyTree rebalanceForRight(MyTree t) {
		int leftHeight = MyTreeOps.height(t.getLeft());
        int rightHeight = MyTreeOps.height(t.getRight());
		if (rightHeight - leftHeight == 2) {
            MyTree rightNode = t.getRight();
            int rightLeftHeight = MyTreeOps.height(rightNode.getLeft());
            int rightRightHeight = MyTreeOps.height(rightNode.getRight());
			
			if (rightRightHeight >= rightLeftHeight) {
                MyTree newLeft = new MyTree(t.getValue(), t.getLeft(), rightNode.getLeft());
                return new MyTree(rightNode.getValue(), newLeft, rightNode.getRight());
            } 
            
			else {
                MyTree rightLeftNode = rightNode.getLeft();
                MyTree newLeft = new MyTree(t.getValue(), t.getLeft(), rightLeftNode.getLeft());
                MyTree newRight = new MyTree(rightNode.getValue(), rightLeftNode.getRight(), rightNode.getRight());
                return new MyTree(rightLeftNode.getValue(), newLeft, newRight);
            }
        }
        return t;
	}
	
	// 8. deleteHB() [10 points]
	/**
	 * Deletes the value 'x' from the given tree, if it exists, and returns the new
	 * Tree.
	 *
	 * Otherwise, the original tree will be returned.
	 */
	public static MyTree deleteHB(MyTree t, int x) {
        if (t.getEmpty()) {
            return t; 
        }
        int currentValue = t.getValue();

        if (x < currentValue) {
            MyTree newLeft = deleteHB(t.getLeft(), x);
            MyTree updatedTree = new MyTree(currentValue, newLeft, t.getRight());
            return rebalanceForRight(updatedTree);
        } 

        else if (x > currentValue) {
            MyTree newRight = deleteHB(t.getRight(), x);
            MyTree updatedTree = new MyTree(currentValue, t.getLeft(), newRight);
            return rebalanceForLeft(updatedTree);
        } 
        
		else {
            if (t.getLeft().getEmpty() && t.getRight().getEmpty()) {
                return new MyTree(); // Kembalikan tree kosong
            } 
            else if (t.getLeft().getEmpty()) {
                return t.getRight();
            } 
            else if (t.getRight().getEmpty()) {
                return t.getLeft();
            } 
            else {
                int maxLeft = max(t.getLeft());
                MyTree newLeft = deleteHB(t.getLeft(), maxLeft);
				MyTree updatedTree = new MyTree(maxLeft, newLeft, t.getRight());
                return rebalanceForRight(updatedTree);
            }
        }
	}

}