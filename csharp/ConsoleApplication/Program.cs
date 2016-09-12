namespace ConsoleApplication
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text;
    using Newtonsoft.Json;

    public class BstNode
    {
        public BstNode Left { get; set; }

        public BstNode Right { get; set; }

        public int Val { get; set; }
    }

    public class Point
    {
        public double X { get; set; }

        public double Y { get; set; }

        public double ToZero => Math.Pow(this.X, 2) + Math.Pow(this.Y, 2);
    }

  public class Interval
    {
      public int start;
       
      public int end;

      public Interval() { this.start = 0; this.end = 0; }

      public Interval(int s, int e) { this.start = s; this.end = e; }
  }


    class Program
    {

        static void Main(string[] args)
        {
            var set = new HashSet<string> {"what", "is", "up"};
            var list = new List<string> {"what", "what"};
            Console.WriteLine(JsonConvert.SerializeObject(set));
            Console.ReadLine();
           
        }

        static IList<IList<int>> VerticalTraversal(BstNode root)
        {
            if (root == null)
                throw new ArgumentNullException(nameof(root));
        
            var queue = new Queue<BstNode>();
            var dic = new Dictionary<int, int>();
            var resultDic = new SortedDictionary<int, IList<int>>();
            queue.Enqueue(root);
            dic.Add(root.Val, 0);
            resultDic.Add(0, new List<int> {root.Val});
            while (queue.Count > 0)
            {
                var cur = queue.Dequeue();
                var curIndex = dic[cur.Val];
                if (cur.Left != null)
                {
                    var curLeftIndex = curIndex - 1;
                    dic.Add(cur.Left.Val, curLeftIndex);
                    if (!resultDic.ContainsKey(curLeftIndex))
                        resultDic.Add(curLeftIndex, new List<int> {cur.Left.Val});
                    else
                        resultDic[curLeftIndex].Add(cur.Left.Val);
                    queue.Enqueue(cur.Left);
                }
                if (cur.Right != null)
                {
                    var curRightIndex = curIndex + 1;
                    dic.Add(cur.Right.Val, curRightIndex);
                    if (!resultDic.ContainsKey(curRightIndex))
                        resultDic.Add(curRightIndex, new List<int> { cur.Right.Val });
                    else
                        resultDic[curRightIndex].Add(cur.Right.Val);
                    queue.Enqueue(cur.Right);
                }
            }

            return resultDic.Values.ToList();
        }

        public static int LongtestCS(int[] nums)
        {
            if (nums == null || nums.Length == 0)
                throw new ArgumentException(nameof(nums));

            var set = new HashSet<int>();
            foreach (var n in nums)
                set.Add(n);

            var max = 0;
            foreach (var n in nums)
            {
                var left = n - 1;
                var right = n + 1;
                var count = 1;
                while (set.Contains(left))
                {
                    set.Remove(left--);
                    count++;
                }
                while (set.Contains(right))
                {
                    set.Remove(right++);
                    count++;
                }

                max = Math.Max(max, count);
            }

            return max;
        }

        public static IList<IList<int>> SumComb(int num)
        {
            if (num <= 0)
                throw new ArgumentException(nameof(num));
            return SumAux(num);
        }

        private static IList<IList<int>> SumAux(int num)
        {
            if (num == 1)
                return new List<IList<int>> {new List<int> {1}};

            var curList = new List<IList<int>>();
            foreach (var n in Enumerable.Range(1, num))
            {
                var minusNComb = SumAux(num - n);
                curList.AddRange(minusNComb.Select(m => new List<int>(m) {n}));
            }
            return curList;
        }

        //    Given a set of candidate numbers(C) and a target number(T), find all unique combinations in C where the candidate numbers sums to T.The same repeated number may be chosen from C unlimited number of times.
        //    Note: All numbers (including target) will be positive integers.Elements in a combination (a1, a2, ... , ak) must be in non-descending order. (ie, a1 <= a2 <= ... <= ak). The solution set must not contain duplicate combinations.For example, given candidate set 2,3,6,7 and target 7, A solution set is:
        //[7]
        //[2, 2, 3]

        public static IList<IList<int>> CombSum(IList<int> set, int target)
        {
            var res = new List<IList<int>>();
            CombSumAux(0, set.OrderBy(x => x).ToList(), target, res, new List<int>(), 0);
            return res;
        }

        private static void CombSumAux(int start, IList<int> set, int target, IList<IList<int>> res, IList<int> oneComb, int candidate)
        {
            if (target < candidate)
                return;
            if (target == candidate)
            {
                res.Add(new List<int>(oneComb));
                return;
            }

            for (var i = start; i < set.Count; i++)
            {
                oneComb.Add(set[i]);
                candidate = candidate + set[i];
                CombSumAux(i, set, target, res, oneComb, candidate);
                oneComb.RemoveAt(oneComb.Count - 1);
                candidate = candidate - set[i];
            }
        }

        /// <summary>
        /// Handle duplicates
        /// </summary>
        /// <param name="points"></param>
        /// <param name="k"></param>
        /// <returns></returns>
        public static Point KthClosestPoint(IEnumerator<Point> points, int k)
        {
            var sortedDic = new SortedDictionary<double, IList<Point>>();
            var count = 0;
            while (points.MoveNext())
            {
                var toZero = points.Current.ToZero;
                if (count >= k)
                {
                    var max = sortedDic.Keys.First();
                    if (max > toZero)
                    {
                        sortedDic.Remove(max);
                        count--;
                    }
                }
                if (sortedDic.ContainsKey(toZero))
                    sortedDic[toZero].Add(points.Current);
                else
                    sortedDic.Add(toZero, new List<Point> {points.Current});
                count++;
            }
            return sortedDic.Values.First().First();
        }

        public static BstNode PreOrderArrayToBst(IList<int> preOrder)
        {
            if (preOrder == null || preOrder.Count == 0) 
                throw new ArgumentNullException(nameof(preOrder));

            var root = new BstNode {Val = preOrder[0]};
            BuildBst(preOrder, root, 1, preOrder[0]);
            return root;
        }

        private static int BuildBst(IList<int> preOrder, BstNode parent, int curIndex, int grandPaData)
        {
            if (curIndex >= preOrder.Count) 
                return curIndex;

            if (preOrder[curIndex] < parent.Val)
            {
                parent.Left = new BstNode {Val = preOrder[curIndex++]};
                curIndex = BuildBst(preOrder, parent.Left, curIndex, parent.Left.Val);
            }
            if (curIndex < preOrder.Count && preOrder[curIndex] < grandPaData)
            {
                parent.Right = new BstNode {Val = preOrder[curIndex++]};
                curIndex = BuildBst(preOrder, parent.Right, curIndex, parent.Right.Val);
            }

            return curIndex;
        }

        //Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
        //Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
        public static IList<Interval> InsertInterval(IList<Interval> intervals, Interval newInterval)
        {
            if (intervals.Count == 0)
                return new List<Interval> {newInterval};

            var length = intervals.Count;
            for (var i = 0; i < length; i++)
            {
                var interval = intervals[i];
                if (interval.end >= newInterval.end && interval.start <= newInterval.start)
                    return intervals;
                if (newInterval.end < interval.start)
                {
                    intervals.Insert(i, newInterval);
                    return intervals;
                }
                if (newInterval.start > interval.end)
                    continue;
                newInterval.start = Math.Min(interval.start, newInterval.start);
                newInterval.end = Math.Max(interval.end, newInterval.end);
                intervals.RemoveAt(i--);
            }

            intervals.Add(newInterval);
            return intervals;
        }

        public static void Test()
        {
            var random = new Random();
            Console.WriteLine(random.Next(1, 4));
            Console.ReadLine();
        }

        //“(a)()” -> “(a)()” “((bc)” -> “(bc)” “)))a((” -> “a” “(a(b)” ->“(ab)” or “a(b)”
        public static string CleanUpParenthesis(string p)
        {
            if (string.IsNullOrWhiteSpace(p))
                throw new ArgumentNullException(nameof(p));

            var stack = new Stack<int>();
            var validIndex = new HashSet<int>();
            for (var i = 0; i < p.Length; i++)
            {
                if (p[i] != '(' && p [i] != ')')                                               
                    continue;
                if (p[i] == '(')
                    stack.Push(i);
                else if (stack.Count != 0 && p[stack.Peek()] == '(') 
                {
                    validIndex.Add(stack.Pop());
                    validIndex.Add(i);
                } 
            }
            var sb = new StringBuilder();
            for (var i = 0; i < p.Length; i++)
            {
                var c = p[i];
                if ((c != '(' && c != ')') || validIndex.Contains(i))
                    sb.Append(c);
            }
            return sb.ToString();
        }
    }
}