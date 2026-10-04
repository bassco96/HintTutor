INSERT INTO questions (id, difficulty, topic, question_text, hint, answer) VALUES
(1, 'easy', 'Arrays', '[10,20,30] - What index is needed to print ''20''?', 'Java arrays start at index 0', '1'),

(2, 'easy', 'Loops', 'Fill in the blank to make the loop run 5 times:

for (int i = 0; i < ___; i++)', 'The loop starts at 0, so it needs to stop before 5.', '5'),

(3, 'easy', 'Loops', 'Fill in the blank to make the loop run 10 times:

for (int i = 0; i <= ___; i++)', 'The condition for the loop is less than OR equal to.', '9'),

(4, 'easy', 'If Statements', 'int x = 12;
if (x = 12)
    System.out.print("true");
else
    System.out.print("false");

Does this print true, false or error?', 'Assignment operator (=), comparison operator (==)', 'error'),

(5, 'medium', 'Enhanced For Loop', 'Convert the standard for loop into an enhanced one:

int[] numbers = {1,2,3,4};

for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

for (_______________) {
    System.out.print(num);
}', 'Enhanced for loop format: for (variable : what is being iterated through)', 'num : numbers'),

(6, 'medium', 'Sorting Algorithms', 'Selection sort: takes the current item and inserts it into the sorted left side.
Insertion sort: finds the smallest remaining item and swaps it into place.

True or False?', 'Insertion sort builds a sorted portion by inserting the current item; selection sort repeatedly selects the smallest remaining item.', 'False'),

(7, 'medium', 'Time Complexity', 'What is the time complexity of this code?

for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        System.out.println(i + j);
    }
}

Answer using Big-O notation: example -> O(log n)', 'The inner loop runs n times for every one run of the outer loop.', 'O(n^2)');
