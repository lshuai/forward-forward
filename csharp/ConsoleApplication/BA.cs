using System;
using System.Linq;
using System.Text;

namespace ConsoleApplication
{

    public class BA
    {
        public static string Add(string a, string b)
        {
            if (string.IsNullOrEmpty(a) || string.IsNullOrEmpty(b))
                throw new ArgumentNullException();

            var sb = new StringBuilder();
            var zeros = string.Join("", new int[Math.Abs(a.Length - b.Length)]);
            if (a.Length > b.Length)
                b = zeros + b;
            else
                a = zeros + a;
            var carry = 0;
            for (var i = a.Length - 1; i >= 0; i--)
            {
                if (a[i] == '1' && b[i] == '1')
                {
                    sb.Append(carry > 0 ? '1' : '0');
                    carry = 1;
                }
                else if (a[i] != '1' && b[i] != '1')
                {
                    sb.Append(carry > 0 ? '1' : '0');
                    carry = 0;
                }
                else
                {
                    if (carry > 0)
                    {
                        sb.Append('0');
                        carry = 1;
                    }
                    else
                        sb.Append('1');
                }
            }
            if (carry == 1)
                sb.Append('1');

            return string.Join("", sb.ToString().Reverse());
        }

        public static void Test()
        {
            Console.WriteLine(Add("10101010", "1100110"));
        }
    }
}