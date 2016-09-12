using System;
using System.Linq;
using System.Text;

namespace ConsoleApplication
{
    public class LCS
    {
        public static string LcsLen(string a, string b)
        {
            if (string.IsNullOrEmpty(a) || string.IsNullOrEmpty(b))
                return "";

            var dp = new int[a.Length + 1, b.Length + 1];
            dp[0, 0] = 0;
            for (var i = 0; i < a.Length; i++)
            {
                for (var j = 0; j < b.Length; j++)
                {
                    if (a[i] == b[j])
                        dp[i + 1, j + 1] = dp[i, j] + 1;
                    else
                        dp[i + 1, j + 1] = Math.Max(dp[i, j + 1], dp[i + 1, j]);
                }
            }

            var sb = new StringBuilder();
            var x = a.Length;
            var y = b.Length;
            while (x != 0 && y != 0)
            {
                if (dp[x, y] == dp[x - 1, y])
                    x--;
                else if (dp[x, y] == dp[x, y - 1])
                    y--;
                else
                {
                    sb.Append(a[x - 1]);
                    x--;
                    y--;
                }
            }
            return string.Join("", sb.ToString().Reverse());
        }

        public static void Test()
        {
            Console.WriteLine(LcsLen("abcdefg", "bdf"));
        }
    }
}
