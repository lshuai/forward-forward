using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    public class StrStr
    {
        public static bool Run(string hayStack, string s)
        {
            if (string.IsNullOrEmpty(hayStack) || string.IsNullOrEmpty(s) || s.Length > hayStack.Length)
                return false;
            for (var i = 0; i < hayStack.Length; i++)
            {
                var j = 0;
                var curIndex = i;
                while (curIndex < hayStack.Length && j < s.Length)
                {
                    if (hayStack[curIndex++] != s[j++])
                        break;
                }
                if (j == s.Length)
                    return true;
            }
            return false;
        }

        public static void Test()
        {
            Console.WriteLine(Run("bacd", "acd"));
        }
    }
}
