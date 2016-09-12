namespace ConsoleApplication
{
    using System;
    using System.CodeDom;
    using System.Collections.Generic;

    class MinWindowSubstring
    {
        public  string MinWindow(string s, string t)
        {
            if (string.IsNullOrEmpty(s) || string.IsNullOrEmpty(t) || s.Length < t.Length)
                return "";

            var window = new Dictionary<char, int>();
            var tDict = new Dictionary<char, int>();
            foreach (var ch in t)
            {
                if (!tDict.ContainsKey(ch))
                    tDict.Add(ch, 1);
                else
                    tDict[ch]++;
            }
            foreach (var ch in s)
                if (!window.ContainsKey(ch))
                    window.Add(ch, 0);

            var matchCount = 0;
            var left = 0;
            var right = 0;
            var min = int.MaxValue;
            var start = 0;
            while (right < s.Length)
            {
                window[s[right]]++;
                if (tDict.ContainsKey(s[right]) && window[s[right]] <= tDict[s[right]])
                    matchCount++;
                right++;

                while (matchCount == t.Length)
                {

                    if (min > right - left)
                    {
                        min = right - left;
                        start = left;
                    }
                    window[s[left]]--;
                    if (tDict.ContainsKey(s[left]) && window[s[left]] < tDict[s[left]]) 
                        matchCount--;
                    left++;
                }
            }

            return min == int.MaxValue ? "" : s.Substring(start, min);
        }

        public static void Test()
        {
            var s = "ADOBECODEBANC";
            var t = "ABC";
            Console.WriteLine(new MinWindowSubstring().MinWindow(s, t));
            Console.ReadLine();
        }
    }
}