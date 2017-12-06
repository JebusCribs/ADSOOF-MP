    class WordStoreImp implements WordStore {

        Node[] store;

        final double BUCKETSIZE = 1;

        public WordStoreImp(int num) {

            double numberBuckets = num * BUCKETSIZE;
            int numBuckets = (int) numberBuckets;
            store = new Node[numBuckets];
            for (int i = 0; i < numBuckets; i++) {
                store[i] = null;
            }
        }

        @Override
        public int count(String word) {
            int hash = calculateHash(word);
            if (store[hash] == null) {

                return 0;
            } else {
                if (store[hash].word.compareTo(word) == 0) {

                    return store[hash].freq;
                } else if ((store[hash].left == null) && (store[hash].right == null)) {

                    return 0;
                } else if ((store[hash].left != null) && (store[hash].left.word.compareTo(word) == 0)) {

                    return store[hash].left.freq;
                } else if ((store[hash].right != null) && (store[hash].right.word.compareTo(word) == 0)) {

                    return store[hash].right.freq;
                } else {

                    Node parent = recFindParent(store[hash], word);
                    if ((parent.left != null) && (parent.left.word.compareTo(word) == 0)) {
                        return parent.left.freq;
                    } else if ((parent.right != null) && (parent.right.word.compareTo(word) == 0)) {
                        return parent.right.freq;
                    } else {
                        return 0;
                    }
                }
            }
        }

        @Override
        public void add(String word) {
            int total = count(word);
            int hash = calculateHash(word);
            if (store[hash] == null) {

                store[hash] = new Node(word);
            } else if (count(word) != 0) {

                if (store[hash].word.compareTo(word) == 0) {
                    store[hash].freq += 1;
                } else if ((store[hash].left != null) && (store[hash].left.word.compareTo(word) == 0)) {
                    store[hash].left.freq += 1;
                } else if ((store[hash].right != null) && (store[hash].right.word.compareTo(word) == 0)) {
                    store[hash].right.freq += 1;
                } else {

                    Node parent = recFindParent(store[hash], word);
                    if ((parent.left != null) && (parent.left.word.compareTo(word) == 0)) {
                        parent.left.freq += 1;
                    } else {
                        parent.right.freq += 1;
                    }
                }
            } else {

                Node parent = recFindShouldParent(store[hash], word);
                if (parent.word.compareTo(word) < 0) {
                    parent.left = new Node(word);
                } else {
                    parent.right = new Node(word);
                }
            }
            int showTotal = count(word);
            if (total >= showTotal) {
                System.out.println("NOT ADDED");
            }
        }

        @Override
        public void remove(String word) {
            if (count(word) == 0) {
                return;
            }
            int hash = calculateHash(word);
            Node parent = recFindParent(store[hash], word);
            Node node = findNode(parent, word);
            if ((node.word.compareTo(word) == 0) && (node.freq > 1)) {

                node.freq--;
            } else {

                if ((node.left == null) && (node.right == null)) {

                    if (store[hash].word.compareTo(word) == 0) {

                        store[hash] = null;
                    } else if ((parent.left != null) && (parent.left.word.compareTo(word) == 0)) {

                        parent.left = null;
                    } else {

                        parent.right = null;
                    }
                } else if ((node.left != null) && (node.right != null)) {

                    Node parentOfMinValue = recFindParent(node, nextSmallVal(node.right).word);
                    if (store[hash].word.compareTo(word) == 0) {

                        store[hash] = parentOfMinValue.left;
                    } else if ((parent.left != null) && (parent.left.word.compareTo(word) == 0)) {

                        parent.left = parentOfMinValue.left;
                    } else {

                        parent.right = parentOfMinValue.left;
                    }
                    parentOfMinValue.left = null;
                } else {

                    if (store[hash].word.compareTo(word) == 0) {

                        store[hash] = (node.left != null) ? node.left : node.right;
                    } else if ((parent.left != null) && (parent.left.word.compareTo(word) == 0)) {

                        parent.left = ((node.right == null) && (node.left != null)) ? node.left : node.right;
                    } else {

                        parent.right = ((node.right == null) && (node.left != null)) ? node.left : node.right;
                    }
                }
            }
        }


        public int calculateHash(String word) {
            int hash = 7;
            for (int i = 0; i < word.length(); i++) {
                hash = (hash * 31 + word.charAt(i)) % store.length;
            }
            return hash;
        }

        public Node recFindShouldParent(Node parent, String word) {

            if ((parent.left == null) && (parent.right == null)) {
                return parent;
            }
            if (parent.word.compareTo(word) < 0) {
                return (parent.left == null) ? parent : recFindShouldParent(parent.left, word);
            } else {
                return (parent.right == null) ? parent : recFindShouldParent(parent.right, word);
            }
        }

        public Node recFindParent(Node parent, String word) {

            if ((parent.word.compareTo(word) < 0) && (parent.left != null)) {
                return (parent.left.word.compareTo(word) == 0) ? parent : recFindParent(parent.left, word);
            }
            if ((parent.word.compareTo(word) > 0) && (parent.right != null)) {
                return (parent.right.word.compareTo(word) == 0) ? parent : recFindParent(parent.right, word);
            }
            return parent;
        }

        public Node findNode(Node parent, String word) {

            if ((parent.left != null) && (parent.left.word.compareTo(word) == 0)) {
                return parent.left;
            } else if ((parent.right != null) && (parent.right.word.compareTo(word) == 0)) {
                return parent.right;
            } else {
                return parent;
            }
        }

        public Node nextSmallVal(Node parent) {
            return (parent.left == null) ? parent : nextSmallVal(parent.left);
        }
    }
