import java.util.*;

public class Survey {
    enum State {
        CREATED,
        EDITING_IN_PROGRESS,
        COMPLETED,
        DELETED,
        VERIFIED
    };

    private State state;
    private List<String> surveyQuestions;
    private final int maximumQuestions = 5;

    public Survey() {
        this.state = State.CREATED;
        this.surveyQuestions = new ArrayList<>();
        System.out.println("New Survey Created. CURRENT STATE -> " + state);
    }

    public void addSurveyQuestions(String question) {
        if (state == State.CREATED || state == State.EDITING_IN_PROGRESS) {
            if (surveyQuestions.size() < maximumQuestions) {
                surveyQuestions.add(question);
                System.out.println("New Survey Question Added -> " + question);
                if (state == State.CREATED) {
                    state = State.EDITING_IN_PROGRESS;
                    System.out.println("State changed to -> "+state);
                }
                if (surveyQuestions.size() == maximumQuestions) {
                    state = State.COMPLETED;
                    System.out.println("The Survey has reached maximum questions. State changed to -> "+state);
                }
            } else {
                System.out.println("Cannot add more questions. Survey is FULL");
            }

        } else{
            System.out.println("More Questions cannot be added in this current state of -> "+state);
        }
    }
    public void removeSurveyQuestion(String question) {
        if (state == State.EDITING_IN_PROGRESS) {
            if (surveyQuestions.remove(question)) {
                System.out.println("Question removed ->" + question);
                if (surveyQuestions.size() < maximumQuestions && state == State.COMPLETED) {
                    state = State.EDITING_IN_PROGRESS;
                    System.out.println("Survey no longer has maximum questions. Reverted State to -> " + state);
                }
            } else {
                System.out.println("Question not found.");
            }
        } else {
            System.out.println("Cannot remove questions in current state of -> : " + state);
        }
    }

    public void verify(boolean internalReviewPasses, boolean externalReviewPasses) {
        if (state == State.COMPLETED) {
            if (internalReviewPasses && externalReviewPasses) {
                state = State.VERIFIED;
                System.out.println("Survey passed verification. Transitioned State to -> " + state);
            } else {
                state = State.EDITING_IN_PROGRESS;
                System.out.println("Verification failed. Returned State to -> " + state);
            }
        } else {
            System.out.println("Cannot verify in current state of ->  " + state);
        }
    }
    public void deleteSurvey() {
        if (state != State.DELETED) {
            state = State.DELETED;
            System.out.println("Survey marked as DELETED. State changed to -> " + state);
        } else {
            System.out.println("Survey is already deleted and cannot be modified.");
        }
    }
    public static void main(String[] args) {
        Survey survey = new Survey();
        // Adding 3 questions
        survey.addSurveyQuestions("Do you like Star Wars or Star Trek?");
        survey.addSurveyQuestions("May the force be with who?");
        survey.addSurveyQuestions("What planet is Superman from?");
        
        // Testing removing a question
        survey.removeSurveyQuestion("In Star Trek, what is the name of the starship captained by James T. Kirk?");
        //adding a few more
        survey.addSurveyQuestions("What is the name of the AI in “2001: A Space Odyssey”");
        survey.addSurveyQuestions("In the movie “Inception,” what is the term for the device that allows characters to enter shared dreams?");

        //checking if it sets appropriate flags when either one review fails
        survey.verify(true, false);
        
        survey.addSurveyQuestions("Who played the role of Spock in the original series?");
        survey.addSurveyQuestions("In Star Trek: The Next Generation, what is the name of Captain Picard’s ship?");
        survey.verify(true, true);

        //Testing Deletion
        survey.deleteSurvey();

        // Trying to add questions after deletion
        survey.addSurveyQuestions("What is the name of the Klingon homeworld?");
    }
}