Justification for Design Choice

Using an enum makes the most sense to have the list of all the states the program has and then implementing the code with appropriate methods with the same logic as needed in the state diagram and then wrote a few tests to make sure everything is working as expected.

Let's break it down based on all the concepts I used.

-- State Management
1. Started with an Enum to define all the states the survey can have.
2. CREATED -> this is the state of the program when a new survey is created.
3. EDITING_IN_PROGRESS -> the state the program uses when you are adding or deleting questions.
4. COMPLETED -> when there are total of 5 questions added which is the maxmimum limit the state is change to COMPLETED so that no further questions can be added.
5. VERFIIED -> this state happens when both external review and automated tool review is passed as true.
6. DELETED -> this state marks that the survey is marked for deletion and cannot add more questions.


-- Tests
I created tests based on different scenario to make sure that the state machine is performing just as expected in the diagram. 

1. Started with adding a few tasks just to make sure it is setting the state as CREATED for the first one. and EDITING_IN_PROGRESS for the second one.

2. Removing a few question sets the flag appropriately, and if you try to remove a question that doesn't exists it detects that.

3. if Verification fails it sets the state to EDITING_IN_PROGRESS

4. If you delete a question you cannot add more questions.