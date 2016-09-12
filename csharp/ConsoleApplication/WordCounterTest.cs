namespace ConsoleApplication
{
    using System.IO;
    using System.Linq;
    using System.Reflection;
    using Microsoft.VisualStudio.TestTools.UnitTesting;

    /// <summary>
    /// Test methods naming convention following: MethodName_StateUnderTest_ExpectedBehavior
    /// Code coverage basd on Visual Studio Analysis: 92%
    /// </summary>
    [TestClass]
    public class WordCounterTest
    {
        private WordCounter counter;

        [TestInitialize]
        public void Init()
        {
            this.counter = new WordCounter(); 
        }


        /// <summary>
        /// Test add invalid words
        /// </summary>
        [TestMethod]
        public void AddWord_InValidWord_WordNotAddedTest()
        {
            // Add null word
            this.counter.AddWord(null); 
           
            // Add empty word
            this.counter.AddWord("");

            // Add word only contains white spaces
            this.counter.AddWord("   ");

            // Add single char
            this.counter.AddWord("a");

            // Add stopword
            this.counter.AddWord("the");

            Assert.AreEqual(this.counter.GetTop().Count(), 0);
        }

        /// <summary>
        /// Test adding number of words where number is with in the top threshold, by default it is 10
        /// </summary>
        [TestMethod]
        public void AddWord_AddNWordsWithInThreshold_CorrectTopWordOrderAndTotalNumberTest()
        {
            for (var i = 0; i < 2; i++)
                this.counter.AddWord("word1"); 
            for (var i = 0; i < 3; i++)
                this.counter.AddWord("word2");
            var topMax = this.counter.GetTop().ToList();

            Assert.AreEqual(topMax[0].Value, 3);
            Assert.AreEqual(topMax[1].Value, 2);
            Assert.AreEqual(topMax[0].Key, "word2");
            Assert.AreEqual(topMax[1].Key, "word1");
        }

        /// <summary>
        /// Test add invalid paragraph
        /// </summary>
        [TestMethod]
        public void AddParagraph_InValidParagraph_WordNotAddedTest()
        {
            // Add null
            this.counter.AddParagraph(null);

            // Add empty paragraph
            this.counter.AddParagraph("");

            // Add word only contains white spaces
            this.counter.AddParagraph("   ");
        }

        /// <summary>
        /// Pre-Condition: Test file THE_STORY_OF_THUMBELINA must be in the same folder path as test class
        /// Test add a paragraph with number of words will exceed threshold
        /// </summary>
        [TestMethod]
        public void AddParagraph_ValidParagraphThatExceedThreshold_CorrectTopWordOrderAndTotalNumberTest()
        {
            var path = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), @"THE_STORY_OF_THUMBELINA");
            var article = File.ReadAllText(path);
            this.counter.AddParagraph(article);
            var result = this.counter.GetTopStr();

            Assert.AreEqual(result, "thumbelina:21\nlittle:8\nall:7\naway:6\nswallow:6\nflower:6\ncame:5\nmole:5\ngirl:5\nday:5");
        }
    }
}