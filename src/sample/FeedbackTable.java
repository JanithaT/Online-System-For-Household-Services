package sample;

public class FeedbackTable {
    String name,message;
    int feedbackID;

    public FeedbackTable(int feedbackID,String name, String message) {
        this.feedbackID=feedbackID;
        this.name = name;
        this.message = message;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
