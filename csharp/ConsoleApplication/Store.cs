using System;
using System.Collections.Generic;
using System.Linq;

namespace ConsoleApplication
{

    public class TrieNode
    {
        public char Val { get; set; }

        public IList<TrieNode> Children { get; set; }

        public TrieNode()
        {
            this.Children = new List<TrieNode>();
        }
    }

    public class Store
    {
        private readonly TrieNode root;

        public Store()
        {
            this.root = new TrieNode();
        }

        public void AddString(string str)
        {
             if (string.IsNullOrWhiteSpace(str))
                throw new ArgumentNullException(nameof(str));

            AddAux(this.root, 0, str);
        }

        private static void AddAux(TrieNode node, int i, string str)
        {
            while (i < str.Length)
            {
                var match = node.Children.FirstOrDefault(c => c.Val == str[i]);
                if (match == null)
                {
                    match = new TrieNode {Val = str[i]};
                    node.Children.Add(match);
                }
                node = match;
                i++;
            }
        }

        public bool Search(string str)
        {
            if (string.IsNullOrWhiteSpace(str))
                throw new ArgumentNullException(nameof(str));
            var i = 0;
            var cur = this.root;
            while (i < str.Length)
            {
                var match = cur.Children.FirstOrDefault(c => c.Val == str[i]);
                if (match == null)
                    return false;
                cur = match;
                i++;
            }
            return true;
        }

        public static void Test()
        {
            var store = new Store();
            store.AddString("teststring");
            store.AddString("test");
            Console.WriteLine(store.Search("test"));
            Console.WriteLine(store.Search("testa"));
            Console.WriteLine(store.Search("teststring"));
        }
    }
}
