package src.html;


import java.util.*;

public class HTMX {

    private final List<HTMXTrigger> triggers;
    private final String post_url;
    private final String target_id;

    public HTMX() {
        this.triggers = new ArrayList<>();
        this.post_url = "";
        this.target_id = "";
    }
    public HTMX(List<HTMXTrigger> triggers, String post, String targetId) {
        this.triggers = triggers;
        this.post_url = post;
        this.target_id = targetId;
    }
    public HTMX(HTMX old) {
        this.triggers  = old.triggers ;
        this.post_url  = old.post_url ;
        this.target_id = old.target_id;
    }

    public HTMX addTrigger(HTMXTrigger trigger) {
        var new_list = new ArrayList<HTMXTrigger>(this.triggers);
        new_list.add(trigger);
        return new HTMX(new_list, this.post_url, this.target_id);
    }

    public HTMX post(String url) {
        return new HTMX(this.triggers, url, this.target_id);
    }

    public HTMX target_id(String id) {
        return new HTMX(this.triggers, this.post_url, id);
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
