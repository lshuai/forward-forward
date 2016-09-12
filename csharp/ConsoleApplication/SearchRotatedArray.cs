using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    //(i.e., 0 1 2 4 5 6 7 might become  4 5 9 -1 0 1 2 ).

    public class SearchRotatedArray
    {
        public static bool Search(int[] nums, int target)
        {
            if (nums == null || nums.Length == 0)
                return false;

            return true;
        }

        //public static bool Aux(int left, int right, int[] nums, int target)
        //{
        //    if (left > right)
        //        return false;
        //    var mid = left + (right - left)/2;
            
             
        //}

        public static void Test()
        {
            Console.WriteLine(Search(new List<int> { 4, 5, 6, 7, 8, 1, 2, 3 }.ToArray(), 8));
        }
    }
}
