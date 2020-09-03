/*  
 * Project 2 - Huffman Coding
 * Tuyet Nhi Ngo
 * CIS 55 - Data Structure
 * 5/20/2020
 */

package huffmancoding;

import java.util.Scanner;

public class HuffmanCoding {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Huffman Coding");
        System.out.println("--------------");
        
        while (true) 
        {
            System.out.print("Type the text to be encoded:");
            String text = in.nextLine().trim();

            if (text.isEmpty()) 
            {
                System.out.println("Bye bye!");
                return;
            }
        
            System.out.println();
            Huffman.buildHuffmanTree(text);  
            System.out.println();  
        }   
    }
}
