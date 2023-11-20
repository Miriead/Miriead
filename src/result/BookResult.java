package result;

public class BookResult {
    private int id;
    private String title;
    private String author;
    private int page;
    private int school;
    private String type;
    private String story;

    public BookResult(int id, String title, String author, int page, int school, String type, String story) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.page = page;
        this.school = school;
        this.type = type;
        this.story = story;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPage() {
        return page;
    }

    public int getSchool() {
        return school;
    }

    public String getType() {
        return type;
    }

    public String getStory() {
        return story;
    }
}