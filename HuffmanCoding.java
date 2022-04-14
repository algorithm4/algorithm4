import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanCoding {

    public static HashMap<Character, Integer> txt = new HashMap<>();
    public static Node plusparent;

    static {
        plusparent = null;
    }

    public static void numberoftexts(String src) {

        try {

            File file = new File(src);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line;

            while ((line = bufReader.readLine()) != null) {

                for (int i = 0; i < line.length(); i++) {
                    char temp = line.charAt(i);

                    if (txt.containsKey(temp)) {
                        txt.put(temp, txt.get(temp) + 1);
                    } else {
                        txt.put(temp, 1);
                    }
                }
            }

            bufReader.close();
        }catch (IOException e) {

            System.err.println(e);
            System.exit(1);

    }

        System.out.println("< 영문자 빈도 조사  >");
        for(char s : txt.keySet())
            System.out.println(s+": " + txt.get(s));
    }

    public static void HuffmanTree() {
        MinHeap mini = new MinHeap();

        if (txt.isEmpty())
                return;


        for(char frequency : txt.keySet()) {
            Node x =new Node(txt.get(frequency), frequency);
            mini.insert(x);
        }

        while(true) {

            Node leftChild = mini.MinNode();
            Node rightChild = mini.MinNode();

            plusparent = new Node(leftChild.txt+rightChild.txt,'.');
            plusparent.leftNode=leftChild;
            plusparent.rightNode=rightChild;

            if(mini.isEmpty()) return;

            mini.insert(plusparent);
        }
    }


    public static void HuffmanCode(Node root, int[] code, int idx) {

        if(root.leftNode != null) {
            code[idx]=0;

            HuffmanCode(root.leftNode, code, idx+1);
        }

        if(root.rightNode !=null) {
            code[idx]=1;
            HuffmanCode(root.rightNode, code, idx+1);
        }

        if(root.leftNode ==null && root.rightNode == null) {
            System.out.print(root.string+" :");
            for(int i = 0;i<idx;i++) {
                System.out.print(code[i]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        numberoftexts("alice2.txt");
        HuffmanTree();
        int[] arr = new int[txt.size()-1];
        System.out.println("< 허프만 코드 >");
        HuffmanCode(plusparent,
                arr,
                0);
    }
}

class Node {
    public int txt;
    public char string;
    public Node leftNode;
    public Node rightNode;

    public Node(int txt, char string) {
        this.txt=txt;
        this.string=string;
        leftNode = rightNode = null;
    }

}