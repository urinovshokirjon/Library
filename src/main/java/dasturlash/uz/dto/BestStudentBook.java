package dasturlash.uz.dto;

import dasturlash.uz.enums.StudentBookStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class BestStudentBook{
   private StudentBook studentBook;
   private int takenCount;

    public BestStudentBook(StudentBook studentBook, int takenCount) {
        this.studentBook = studentBook;
        this.takenCount = takenCount;
    }

    public StudentBook getStudentBook() {
        return studentBook;
    }

    public void setStudentBook(StudentBook studentBook) {
        this.studentBook = studentBook;
    }

    public int getTakenCount() {
        return takenCount;
    }

    public void setTakenCount(int takenCount) {
        this.takenCount = takenCount;
    }
}
