package dasturlash.uz.dto;

import dasturlash.uz.enums.StudentBookStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "student_book_table")
public class StudentBook extends BaseEntity{
    @ManyToOne
    private Book book;
    @ManyToOne
    private Profile student;
    @ManyToOne
    private Category category;
    @Column(name = "deadline_date")
    private LocalDate deadlineDate;
    @Column(name = "return_date")
    private LocalDateTime returnedDate;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StudentBookStatus status;
    @Column(name = "taken_count")
    private int takenCount;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Profile getStudent() {
        return student;
    }

    public void setStudent(Profile student) {
        this.student = student;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public StudentBookStatus getStatus() {
        return status;
    }

    public void setStatus(StudentBookStatus status) {
        this.status = status;
    }

    public int getTakenCount() {
        return takenCount;
    }

    public void setTakenCount(int takenCount) {
        this.takenCount = takenCount;
    }
}
