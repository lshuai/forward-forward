using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    using System.Collections;

    //public static String reverseWords(String s)
    //{
    //    StringBuilder res = new StringBuilder();
    //    for (int start = s.length() - 1; start >= 0; start--)
    //    {
    //        if (s.charAt(start) == ' ') continue;
    //        int end = start;
    //        while (start >= 0 && s.charAt(start) != ' ') start--;
    //        res.append(s.substring(start + 1, end + 1)).append(" ");
    //    }
    //    return res.toString().trim();
    //}
    public static class ReverseW
    {
        public static string Reverse(string words)
        {
            var sb = new StringBuilder();
            for (var right = words.Length - 1; right >= 0; right--)
            {
                if (words[right] == ' ')
                    continue;
                var left = right;
                while (left >= 0 && words[left] != ' ')
                    left--;
                sb.Append(words.Substring(left + 1, right - left)).Append(" ");
                right = left;
            }
             
            return sb.ToString().Substring(0, sb.Length - 1);
        }

        public static void Test()
        {
            Console.Write(Reverse(" Man  bites  dog  "));
        }

    }

}
