package snapchat;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;

class XMLNode {
    public String name;
    public String content;
    public List<XMLNode> children;

    public XMLNode(String name, String content) {
        this.name = name;
        this.content = content;
        this.children = new LinkedList<XMLNode>();
    }
}

class Tag {
    private String xml;
    private int start;
    private int end;

    public Tag(String xml, int start, int end) {
        this.xml = xml;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        if (this.open())
            return this.xml.substring(start + 1, end - 1);
        else 
            return this.xml.substring(start + 2, end - 1);
    }
    
    public boolean open() {
        return xml.charAt(start + 1) != '/';
    }
}

public class XMLParser {
	public static void main(String[] args) throws Exception {
		String xml = new String(Files.readAllBytes(Paths.get("/Users/lshuai/Documents/JWorkspace/Forward/src/snapchat/xml.xml")));
		XMLNode root = new XMLParser().parse(xml);
		System.out.println(root);
	}
	
    public XMLNode parse(String xml) {
        if (xml == null || xml.length() == 0)
            return null;
         
        Stack<XMLNode> stack = new Stack<XMLNode>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < xml.length()) {
            while (xml.charAt(i) != '<')
                sb.append(xml.charAt(i++));
            int s = i;
            while (xml.charAt(i) != '>')
                i++;
            Tag tag = new Tag(xml, s, ++i);
            if (stack.empty() || tag.open()) {
                XMLNode newNode = new XMLNode(tag.getName(), null);
                if (!stack.empty())
                    stack.peek().children.add(newNode);
                stack.push(newNode); 
            }
            else {
                XMLNode top = stack.pop();
                top.content = sb.toString();
                if (stack.empty())
                    return top;
            }
            sb = new StringBuilder();
        }

        return null;
    }
}
