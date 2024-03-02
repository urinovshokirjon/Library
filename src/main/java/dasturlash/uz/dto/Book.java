package dasturlash.uz.dto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "book_table")
public class Book extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "publish_date")
    private LocalDate publishDate;
    @Column(name = "available_day")
    private Integer availableDay;
    @Column(name = "visible")
    private Boolean visible;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getAvailableDay() {
        return availableDay;
    }

    public void setAvailableDay(Integer availableDay) {
        this.availableDay = availableDay;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
