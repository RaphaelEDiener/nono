package src.html;


import java.util.*;

public class HTMX implements HTML {

    private final List<HTMXTrigger> triggers;
    private String post_url;
    private String target_id;

    public HTMX() {
        this.triggers = new ArrayList<>();
        this.post_url = "";
        this.target_id = "";
    }
    public HTMX(List<HTMXTrigger> triggers, String post) {
        this.triggers = triggers;
        this.post_url = post;
    }
    public HTMX(HTMX old) {
        this.triggers  = old.triggers ;
        this.post_url  = old.post_url ;
        this.target_id = old.target_id;
    }

    public HTMX addTrigger(HTMXTrigger trigger) {
        var new_list = new ArrayList<HTMXTrigger>(this.triggers);
        new_list.add(trigger);
        return new HTMX(new_list, this.post_url);
    }

    public HTMX post(String url) {
        return new HTMX(this.triggers, url);
    }

    public HTMX target_id(String id) {
        var ans = new HTMX(this);
        ans.target_id = id;
        return ans;
    }

    public String toHtml() {
        var ans = new StringBuilder();
        if (!this.triggers.isEmpty()){
            ans.append("hx-trigger=\"");
            ans.append(String.join(
                    ", ",
                    this.triggers.stream().map(HTMXTrigger::toString).toArray(String[]::new)
            ));
            ans.append(" from:body\" ");
        }
        if (!this.post_url.isEmpty()) {
            ans.append("hx-post=\"");
            ans.append(this.post_url);
            ans.append("\" ");
        }
        if (!this.target_id.isEmpty()) {
            ans.append("hx-target=\"#")
               .append(this.target_id)
               .append("\" ");
        }
        return ans.toString();
    }

    public String innerHtml() {
        return this.toHtml();
    }
}
