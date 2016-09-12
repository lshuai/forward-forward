using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    //'?' Matches any single character.
    //'*' Matches any sequence of characters (including the empty sequence).

    //The matching should cover the entire input string (not partial).

    //The function prototype should be:
    //bool isMatch(const char* s, const char* p)

    //Some examples:
    //isMatch("aa","a") → false
    //isMatch("aa","aa") → true
    //isMatch("aaa","aa") → false
    //isMatch("aa", "*") → true
    //isMatch("aa", "a*") → true
    //isMatch("ab", "?*") → true
    //isMatch("aab", "c*a*b") → false
    //isMatch("ab", "a*b*") → true

    //************************************

    //'.' Matches any single character.
    //'*' Matches zero or more of the preceding element.

    //The matching should cover the entire input string (not partial).

    //The function prototype should be:
    //bool isMatch(const char* s, const char* p)

    //Some examples:
    //isMatch("aa","a") → false
    //isMatch("aa","aa") → true
    //isMatch("aaa","aa") → false
    //isMatch("aa", "a*") → true
    //isMatch("aa", ".*") → true
    //isMatch("ab", ".*") → true
    //isMatch("aab", "c*a*b") → true
    public class WildCard
    {
    }
}
