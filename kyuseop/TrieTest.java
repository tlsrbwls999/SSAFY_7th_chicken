import java.util.HashMap;
import java.util.Map;

public class TrieTest {

	public static void main(String[] args) {
		Trie trie = new Trie();
		
		trie.insert("Hello");
		trie.insert("World");
		
		System.out.println(trie.contains("World"));
	}
}

class Trie {
	public TrieNode root = new TrieNode();
	
	// 삽입
	public void insert(String word) {
		TrieNode currentNode = this.root;
		
		for(int i=0; i<word.length(); i++) {
			char currentChar = word.charAt(i);
			
			// key값중 현재 글자에 해당하는 알파벳이 없으면 새 노드를 생성
			if(!currentNode.childs.containsKey(currentChar)) {
				currentNode.childs.put(currentChar, new TrieNode());
			}
			// 자식노드로
			currentNode = currentNode.childs.get(currentChar);
		}
		
		// currentNode 무조건 마지막 글자
		currentNode.isLastChar = true;
		
	}
	
	// 조회
	public boolean contains(String word) {
		TrieNode currentNode = this.root;
		
		for(int i=0; i<word.length(); i++) {
			char currentChar = word.charAt(i);
			
			// 현재 알파벳에 해당하는 노드가 없으면 false 반환
			if(!currentNode.childs.containsKey(currentChar)) {
				return false;
			}
			
			currentNode = currentNode.childs.get(currentChar);
		}
		
		// currentNode 무조건 마지막 글자
		// 마지막 노드가 어떤 단어의 마지막 글자인지 판별, 반환
		if(currentNode.isLastChar) return true;
		return false;
		
	}
	
	
}

class TrieNode {
	public Map<Character, TrieNode> childs = new HashMap<>();
	// 배열로 하려면 
	//public TrieNode[] = new TrieNode[26]; // 0~25 a~z -> 소문자, 대문자 한정된 경우만
	
	public boolean isLastChar; // 어떤 단어의 마지막 글자인지
}