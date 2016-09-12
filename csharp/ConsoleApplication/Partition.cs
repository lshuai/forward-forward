using System;
using System.Collections.Generic;

namespace ConsoleApplication
{
    public class Partition
    {
        public static void Run(int[] a, int start, int end, int pivotIndex)
        {
            var pivot = a[pivotIndex];
            var left = start;
            var right = end;
            var i = start;
            while (i <= right)
            {
                if (a[i] < pivot)
                    Swap(a, i++, left++);
                else if (a[i] > pivot)
                    Swap(a, i, right--);
                else
                    i++;
            }
            Console.WriteLine();
        }

        private static void Swap(int[] a, int i, int j)
        {
            var tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        public static void Test()
        {
            //var a = new List<int> {2, 1, 1, 0, 2, 0, 1, 1, 2, 0}.ToArray();
            var a = new List<int> {2, 1, 1, 0, 3, 0, 4, 1, 2, 0}.ToArray();
            var i = 1;
            Run(a, 0, a.Length-1, 3);
            Console.WriteLine();
        }
    }
}
