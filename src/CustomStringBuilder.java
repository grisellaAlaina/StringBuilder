import java.util.Stack;

public class CustomStringBuilder {
    private StringBuilder stringBuilder;
    private Stack<String> history;

    public CustomStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new Stack<>();
    }

    public void append(String str) {
        saveState();
        stringBuilder.append(str);
    }

    public void delete(int start, int end) {
        if (start >= 0 && end <= stringBuilder.length() && start <= end) {
            saveState();
            stringBuilder.delete(start, end);
        } else {
            throw new IllegalArgumentException("Invalid indices");
        }
    }

    public void insert(int offset, String str) {
        if (offset >= 0 && offset <= stringBuilder.length()) {
            saveState();
            stringBuilder.insert(offset, str);
        } else {
            throw new IllegalArgumentException("Invalid offset");
        }
    }

    public String toString() {
        return stringBuilder.toString();
    }

    private void saveState() {
        history.push(stringBuilder.toString());
    }

    public void undo() {
        if (!history.isEmpty()) {
            String lastState = history.pop();
            stringBuilder = new StringBuilder(lastState);
        } else {
            System.out.println("No operations to undo.");
        }
    }
}