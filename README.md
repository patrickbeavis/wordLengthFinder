# wordLengthCalc
Experiment to find frequency of each word length that exist in a given text file


Patrick Beavis - Word length finder:

------------------------------------------------------------
ASSUMPTIONS
------------------------------------------------------------
**Separators -**
Words are separated in three ways: spaces, tabs, new lines.

Any other punctuation/special characters will be considered a character contributing to the length of the word. 
e.g word-word is one 9 letter word. 


**Terminators -**
Words can be terminated with any of: . , ? - ; : ! " ' ...
Any other characters will be considered as contributing to the length of the word.

e.g hello! is one 5 letter word, hello1 is one 6 letter word

**Characters as words -** 
Characters such as "&" will be counted as one word of length one.

**Preceding punctuation -** 
If a word is preceded by either single or double quotes, then these will not count towards the length of the word. Any other special characters will be considered as contributing towards the length of the word. 
e.g. "Hello" is one 5 letter word. !Hello is one 6 letter word. 

------------------------------------------------------------

Running the script - 

Easiest way is to run in Eclipse (or your favoured IDE) using the command line arguments feature to pass the text file path. 





