package client.android.cobe.com.androidclient.model;

public class Question {

    private int numquestion;
    private String question;
    private Answer answer;
    private String corresctAnswer;

    public int getNumquestion() {
        return numquestion;
    }

    public Question setNumquestion(int numquestion) {
        this.numquestion = numquestion;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Question setAnswer(Answer answer) {
        this.answer = answer;
        return this;
    }

    public String getCorresctAnswer() {
        return corresctAnswer;
    }

    public Question setCorresctAnswer(String corresctAnswer) {
        this.corresctAnswer = corresctAnswer;
        return this;
    }
}
