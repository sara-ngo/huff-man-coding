package huffmancoding;

public class Node implements Comparable<Node>
{
    char ch;
    int freq;
    Node left = null, right = null;
       
    Node(char ch, int freq)
    {
        this.ch = ch;
        this.freq = freq;
    }
    
    public Node(char ch, int freq, Node left, Node right) 
    {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int compareTo(Node that)
    {
        return this.freq - that.freq;
    }
    
}