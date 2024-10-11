
# Using PMD


Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset (see the [pmd install instruction](./pmd-help.md)). Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false positive). Explain why you would not solve this issue.


## Answer


The issue that we think should be solved is the UnnecessaryFullyQualifiedName, which occurs in multiple locations. 

.\TP2\commons-math\commons-math-core\src\main\java\org\apache\commons\math4\core\jdkmath\AccurateMath.java:699: UnnecessaryFullyQualifiedName:  Unnecessary qualifier 'AccurateMath': 'log' is already in scope

Instead of writing : return AccurateMath.log(a + Math.sqrt(a * a - 1));
It could be simplified to : return log(a + Math.sqrt(a * a - 1));
This change makes the code more simple and more readable.

Nevertheless, the issue UselessParentheses which was reported a lot of time is not worth solving because the parentheses make the code more readable.