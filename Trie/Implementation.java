public class Implementation {

    static class Trie {

        class TrieNode {
            private TrieNode[] links;
            private boolean flag;

            public TrieNode() {
                this.links = new TrieNode[26];
            }

            public void createLink(char ch) {
                this.links[ch - 'a'] = new TrieNode();
            }

            public boolean isLinkPresent(char ch) {
                return (this.links[ch - 'a'] != null);
            }

            public TrieNode getLink(char ch) {
                return this.links[ch - 'a'];
            }

            public void setFlag() {
                this.flag = true;
            }

            public boolean getFlag() {
                return this.flag;
            }
        }

        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode temp = this.root;

            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);

                if (!temp.isLinkPresent(ch))
                    temp.createLink(ch);
                temp = temp.getLink(ch);
            }

            temp.setFlag();
        }

        public boolean search(String word) {
            TrieNode temp = this.root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (!temp.isLinkPresent(ch))
                    return false;
                temp = temp.getLink(ch);
            }

            return temp.getFlag();
        }

        public boolean startsWith(String prefix) {
            TrieNode temp = this.root;

            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);

                if (!temp.isLinkPresent(ch))
                    return false;
                temp = temp.getLink(ch);
            }

            return true;
        }

        // Alternatives

        // In this we can't just return boolean as in 'search' function we also
        // need to check whether the last node of this word has flag true or not

        private TrieNode searchPrefix(String word) {
            TrieNode temp = this.root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (!temp.isLinkPresent(ch))
                    return null;
                temp = temp.getLink(ch);
            }

            return temp;
        }

        public boolean search2(String word){
            TrieNode temp = searchPrefix(word);
            return temp != null && temp.getFlag(); 
        }

        public boolean startsWith2(String prefix){
            TrieNode temp = searchPrefix(prefix);
            return temp != null;
        }
    }

    public static void main(String[] args) {
        Trie obj = new Trie();

        obj.insert("apple");
        obj.insert("apps");
        obj.insert("ape");
        obj.insert("bac");
        obj.insert("back");
        obj.insert("bags");

        System.out.println(obj.search("apple"));
        System.out.println(obj.search("app"));
        System.out.println(obj.startsWith("app"));
        System.out.println(obj.startsWith("apk"));
        System.out.println(obj.search("back"));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */