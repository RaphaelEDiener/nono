package src.html;


import java.util.*;

public class HTMX implements HTML {

    private final List<HTMXTrigger> triggers;
    private String post;

    public HTMX() {
        this.triggers = new ArrayList<>();
        this.post = "";
    }

    public void addTrigger(HTMXTrigger trigger) {
        this.triggers.add(trigger);
    }

    public void post(String url) {
        this.post = url;
    }

    @Override
    public String toHtml() {
        // don't convert to string concat, since conditional building
        // could be required in future
        var ans = new StringBuilder();
        ans.append("hx-trigger=\"");
        ans.append(String.join(
                ", ",
                this.triggers.stream().map(HTMXTrigger::toString).toArray(String[]::new)
        ));
        ans.append(" from:body\" ");
        ans.append("hx-post=\"");
        ans.append(this.post);
        ans.append("\" ");
        return ans.toString();
    }

    @Override
    public String innerHtml() {
        return this.toHtml();
    }
}
