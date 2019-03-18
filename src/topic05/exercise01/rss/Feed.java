package topic05.exercise01.rss;

import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public final class Feed {

  // by definition an RSS feed has these fields and much more
  private String title;
  private String link;
  private String description;

  private List<FeedMessage> entries = new ArrayList<FeedMessage>();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }

  public List<FeedMessage> getMessages() {
    return entries;
  }

  public void setMessages(List<FeedMessage> entries) {this.entries = entries;}

  @Override
  public String toString() {
    return "Feed [title=" + title + ", description=" + description + ", link=" + link + "]";
  }
}
