/*  
 * Project 2 - Huffman Coding
 * Tuyet Nhi Ngo
 * CIS 55 - Data Structure
 * 5/20/2020
 */
package huffmancoding;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman 
{
    private Node root;
 
    // Builds Huffman Tree and huffmanCode and decode given input text
    public static void buildHuffmanTree(String text)
    {       
        // count frequency of appearance of each character
        // and store it in a map
        Map<Character, Integer> freq = new HashMap<>();
        
        System.out.println("Frequency table");
        for (int i = 0 ; i < text.length(); i++) 
        {
            if (!freq.containsKey(text.charAt(i))) 
            {
                freq.put(text.charAt(i), 0);
            }
                freq.put(text.charAt(i), freq.get(text.charAt(i)) + 1);
        }
        for (char keys: freq.keySet())
        {
            System.out.println(keys + " : " + freq.get(keys) );
        }

        // Create a priority queue to store live nodes of Huffman tree
        PriorityQueue<Node> tree = new PriorityQueue<>((l, r) -> l.freq - r.freq);

        // Create a leaf node for each character and add it to the priority queue.
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) 
        {
            tree.add(new Node(entry.getKey(), entry.getValue()));
        }

        // do till there is more than one node in the queue
        while (tree.size() != 1)
        {
            // Remove the two nodes of highest priority
            // (lowest frequency) from the queue
            Node left = tree.poll();
            Node right = tree.poll();

            // Create a new internal node with these two nodes as children
            // and with frequency equal to the sum of the two nodes
            // frequencies. Add the new node to the priority queue.
            Node parent = new Node('*', left.freq + right.freq, left, right);
            parent.left = left;
            parent.right = right;
            tree.add(parent);
        }

        // root stores pointer to root of Huffman Tree
        Node root = tree.peek();
        
        System.out.println("\nHuffman tree");
        displayTree(root, "");
        
        // traverse the Huffman tree and store the Huffman codes in a map
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        // print the Huffman codes
        System.out.println("\nHuffman coding table");
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) 
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // print encoded string
        StringBuilder data = new StringBuilder();
        for (int i = 0 ; i < text.length(); i++) 
        {
            data.append(huffmanCode.get(text.charAt(i)));
        }
        System.out.println("\nEncoded string is: \n" + data);

        // traverse the Huffman Tree again and this time
        // decode the encoded string
        int index = -1;
        System.out.println("\nDecoded string is: ");
        while (index < data.length() - 2) 
        {
            index = decode(root, index, data);           
        }
        System.out.println();       
    }
    
     // traverse the Huffman Tree and store Huffman Codes
    // in a map.
    public static void encode(Node root, String s, Map<Character, String> code)
    {
        if (root == null)
            return;
        
        // found a leaf node
        if (root.left == null && root.right == null) 
        {
            code.put(root.ch, s);
        }

        encode(root.left, s + "0", code);
        encode(root.right, s + "1", code);
    }
 
    // traverse the Huffman Tree and decode the encoded string
    public static int decode(Node root, int index, StringBuilder str)
    {
        if (root == null)
            return index;

        // found a leaf node
        if (root.left == null && root.right == null)
        {
            System.out.print(root.ch);
            return index;
        }

        index++;

        if (str.charAt(index) == '0')
            index = decode(root.left, index, str);
        else
            index = decode(root.right, index, str);

        return index;
    }
    
    public static void displayTree(Node root, String dashes)
    {
        if (root.ch != ' ') 
        {
            System.out.println(dashes + root.freq + ":" + root.ch);
        }
        else 
        {
            System.out.println(dashes + root.freq);
        }

        // Start recursive on left child then right
        if (root.left != null) {
            displayTree(root.left, dashes + "-");
        }
        if (root.right != null) {
            displayTree(root.right, dashes + "-");
        }
    }
}