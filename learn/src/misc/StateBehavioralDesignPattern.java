package misc;

// State interface
interface State {
    void handleRequest();
}
// Concrete States
class DraftState implements State {
    @Override
    public void handleRequest() {
        System.out.println("Document is in Draft state. Editing allowed.");
    }
}
class ModerationState implements State {
    @Override
    public void handleRequest() {
        System.out.println("Document is in Moderation state. Waiting for approval.");
    }
}
class PublishedState implements State {
    @Override
    public void handleRequest() {
        System.out.println("Document is in Published state. Ready for public viewing.");
    }
}
// Context class
class Document {
    private State state;
    public Document() {
        this.state = new DraftState(); // Default state
    }
    public void setState(State state) {
        this.state = state;
    }
    public void publish() {
        state.handleRequest();
    }
}
// Client code
public class StateBehavioralDesignPattern {
    public static void main(String[] args) {
        Document document = new Document();
        document.publish(); // Draft state
        document.setState(new ModerationState());
        document.publish(); // Moderation state
        document.setState(new PublishedState());
        document.publish(); // Published state
    }
}