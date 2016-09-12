namespace ConsoleApplication
{
    using System;
    using System.Collections.Generic;
    using System.IO;
    using System.Linq;
    using System.Reflection;

    public class WordCounter
    {
        /// <summary>
        /// Keep track of count for all words
        /// </summary>
        private readonly IDictionary<string, int> container;

        /// <summary>
        /// Maintain a number of top words based on the the value of "top"
        /// This dictionary is designed for performance to make the time complexcity of returning top words
        /// always nlogn where n less than or equals to value of "top"
        /// </summary>
        private readonly IDictionary<string, int> topDic;

        /// <summary>
        /// Contains a set of words that will be igored for word count
        /// </summary>
        private readonly HashSet<string> stopWords;

        /// <summary>
        /// Indicates how many top words will be tracked
        /// </summary>
        private readonly int top;

        /// <summary>
        /// Default constructor that default the top value to 10, stopWords to a list of words
        /// Syntax Note: this is one way of setting default values in C#, pretty handy
        /// </summary>
        public WordCounter(): this(10, 
            new HashSet<string>
            {
                "the",
                "and",
                "she",
                "her",
                "in",
                "of",
                "it",
                "was",
                "am",
                "are",
                "is",
                "were",
                "i'm",
                "to",
                "as",
                "but",
                "he",
                "with",
                "for",
                "so",
                "by",
                "who",
                "did",
                "his",
                "they",
                "had",
                "that",
                "you",
                "its"
            })
        {
        }

        /// <summary>
        /// Constructor that initialize all private properties
        /// </summary>
        /// <param name="top"><seealso cref="top"/></param>
        /// <param name="stopWords"><seealso cref="stopWords"/></param>
        public WordCounter(int top, HashSet<string> stopWords)
        {
            if (stopWords == null)
                throw new ArgumentNullException(nameof(stopWords));

            this.container = new Dictionary<string, int>(top);
            this.topDic = new Dictionary<string, int>();
            this.top = top;
            this.stopWords = stopWords;
        }

        /// <summary>
        /// Take an paragraph and tokenize it to words then add them to the system for count tracking
        /// </summary>
        /// <param name="paragraph">A paragraph of words</param>
        public void AddParagraph(string paragraph)
        {
            if (string.IsNullOrWhiteSpace(paragraph) || paragraph.Length <= 1)
                return;

            var reader = new StringReader(paragraph);
            var line = reader.ReadLine();
            while (line != null)
            {
                if (!string.IsNullOrWhiteSpace(line))
                {
                    var words = line.Trim().Split(' ');
                    foreach (var word in words)
                    {
                        // Syntax Note: "IEnumerable.Where(param => function(param))", this is called Linq in C#
                        // It kind of applies functional programming concept, list comprehension. 
                        // Java 8 just caught up by introducing "Stream", python has similar built-in functions such as, map, reduce
                        // and lambda expression
                        var letters = word.Where(char.IsLetter).ToList();
                        if (letters.Any()) 
                            this.AddWord(string.Join("", letters));
                    }
                }
                line = reader.ReadLine();
            }
        }

        /// <summary>
        /// Add a word to the system for count tracking 
        /// </summary>
        /// <param name="word"></param>
        public void AddWord(string word)
        {
            if (string.IsNullOrWhiteSpace(word) || word.Length <= 1)
                return;
            // Make word counting case-insensitive
            word = word.ToLower();
            // Ignore defined stopword for word count
            if (this.stopWords.Contains(word))
                return;

            // Keep the container up to date
            var count = 1;
            if (!this.container.ContainsKey(word))
                this.container.Add(word, count);
            else
            {
                count = this.container[word] + 1;
                this.container[word] = count;
            }

            // Maintain and update the topDic
            // If the word already exists in the topDic, we simply update the count for it
            if (this.topDic.ContainsKey(word))
            {
                this.topDic[word] = count;
                return;
            }
            // If word is not in topDic and size of the topDic hasn't hit the top yet, e.g. 10, we add the new entry
            if (this.topDic.Count < this.top)
                this.topDic.Add(word, count);
            // Otherwise, we take the min value from topDic and compare it to the new count
            // If min less than new count, the new count should be the new member in topDic, current min should be removed
            // Otherwise, we do nothing
            else
            {
                var min = int.MaxValue;
                var minWord = string.Empty;
                foreach (var curWord in this.topDic.Keys)
                {
                    var cur = this.topDic[curWord];
                    if (cur >= min) continue;
                    min = cur;
                    minWord = curWord;
                }
                if (min < count)
                {
                    this.topDic.Remove(minWord);
                    this.topDic.Add(word, count);
                }
            }
        }

        /// <summary>
        /// Return top max words in KeyValuePair, which is designed for in code comsuption
        /// </summary>
        public IEnumerable<KeyValuePair<string, int>> GetTop()
        {
            return this.topDic.Count != 0 
                ? this.topDic.Select(p => p).OrderByDescending(p => p.Value) 
                : Enumerable.Empty<KeyValuePair<string, int>>();
        }

        /// <summary>
        /// Return top max words in string delimited by '\n', which is desighed for human testing
        /// </summary>
        public string GetTopStr()
        {
            return this.topDic.Count != 0 
                ? string.Join("\n", this.GetTop().Select(p => $"{p.Key}:{p.Value}")) 
                : string.Empty;
        }
    }
}
