using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    public class ValidP
    {

        public static bool IsPalindrome(string s)
        {
            if (string.IsNullOrEmpty(s))
                return true;

            var left = 0;
            var right = s.Length - 1;
            while (left <= right)
            {
                if (!char.IsLetter(s[left]))
                {
                    left++;
                }
                else if (!char.IsLetter(s[right]))
                {
                    right--;
                }
                else
                {
                    if (char.ToLower(s[left++]) != char.ToLower(s[right--]))
                        return false;
                }
            }

            return true;
        }

        public static void Test()
        {
            IsPalindrome("0p");
        }
    }
}
