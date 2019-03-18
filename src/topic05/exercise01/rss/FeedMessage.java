package topic05.exercise01.rss;

/*
 * Represents one RSS message
 */
public final class FeedMessage {

  private String title;
  private String description;
  private String link;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public String toString() {
    return "FeedMessage [title=" + title + ", description=" + description + ", link=" + link + "]";
  }
}
