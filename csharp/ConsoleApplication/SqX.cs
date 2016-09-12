using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    public class SqX
    {
        public static int Compute(int x)
        {
            if (x <= 0)
                return -1;

            var s = 0;
            var e = x;
            while (s <= e)
            {

                var mid = s + (e - s)/2;
                var toTwo = mid*mid;
                if (toTwo == x)
                    return mid;
                if (toTwo > x)
                    e = mid - 1;
                if (toTwo < x)
                    s = mid + 1;
            }
            return e;
        }

        public static void Test()
        {
            Console.WriteLine(Compute(132312));
        }
    }
}
