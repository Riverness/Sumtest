# Feature: Addition
* Only integers up to positive `1000` are allowed as inputs.
* Inputs can be negative.
* Negative inputs can be less than `-1000`.

## Background:
* Given the application is launched
* And the inputs are empty

### Scenario #1 - Positive: Valid Addition with positive integers

* When user types `1000` to first input
* And user types `1000` to second input
* And user clicks `ADD`
* Then loading spinner should show
* Then result `2000` is displayed

### Scenario #2 - Positive: Valid Addition with negative integers

* When user types `-1001` to first input
* And user types `-1001` to second input
* And user clicks `ADD`
* Then loading spinner should show
* Then result `-2002` is displayed

### Scenario #3 - Negative: First input over limit

* When user types `1001` to first input
* And user types `1000` to second input
* And user clicks `ADD`
* Then loading spinner should show
* Then exception overflow error should be displayed

### Scenario #4 - Negative: Second input over limit

* When user types `1000` to first input
* And user types `1001` to second input
* And user clicks `ADD`
* Then loading spinner should show
* Then exception overflow error should be displayed

### Scenario #5 - Negative: First input empty

* When user types `1000` to second input
* And user clicks `ADD`
* Then loading spinner should show
* Then field empty error should be displayed

### Scenario #6 - Negative: Second input empty

* When user types `1000` to first input
* And user clicks `ADD`
* Then loading spinner should show
* Then field empty error should be displayed

### Scenario #7 - Negative: Chars as inputs

* When user types `a` to first input
* And user types `a` to second input
* And user clicks `ADD`
* Then loading spinner should show
* Then invalid input error should be displayed
