package dasturlash.uz.sevice;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.*;
import dasturlash.uz.enums.StudentBookStatus;
import dasturlash.uz.repository.BookRepository;
import dasturlash.uz.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class StudentBookService {
    private BookRepository bookRepository;
    private StudentBookRepository studentBookRepository;

    public void takeBook(int bId) {
        Book book = bookRepository.getBookByIdSpring(bId);
        if (book==null){
            System.out.println("Bunday id li kitob yo'q");
            return;
        }
        int profileId=ComponentContainer.currentProfile.getId();
        List<StudentBook> studentBookList = studentBookRepository.studentBookOnHandSpring(profileId);
        if (studentBookList.size() >=5){
            System.out.println("You can take only five books");
            return;
        }
        StudentBook studentBook=new StudentBook();
        studentBook.setBook(book);
        studentBook.setStudent(ComponentContainer.currentProfile);
        studentBook.setCreatedDate(LocalDateTime.now());
        studentBook.setStatus(StudentBookStatus.TAKEN);
        LocalDate deadlineDate=LocalDate.now().plusDays(book.getAvailableDay());
        studentBook.setDeadlineDate(deadlineDate);
        studentBook.setCategory(book.getCategory());

        int result = studentBookRepository.saveSpring(studentBook);
        if (result==1){
            System.out.println("Book taken");
        }else {
            System.out.println("Error bo'ldi");
        }
    }

    public void booksOnHandByStudentId(){
        List<StudentBook> studentBookList = studentBookRepository.studentBookOnHandSpring(ComponentContainer.currentProfile.getId());
        for (StudentBook sk : studentBookList) {
            int bookId= sk.getBook().getId();
            String title=sk.getBook().getTitle();
            String author=sk.getBook().getAuthor();
            int availableDay=sk.getBook().getAvailableDay();
            String categoryName=sk.getCategory().getName();
            LocalDateTime takenDate=sk.getCreatedDate();
            LocalDateTime deadLine=takenDate.plusDays(availableDay);

            String str=String.format("%s,%s,%s,%s,%s-%s",bookId,title,author,categoryName,takenDate,deadLine);
            System.out.println(str);
        }
    }

    public void returnBook(int bId) {
     int sId=ComponentContainer.currentProfile.getId();

        int effectedRow = studentBookRepository.returnBookSpring(bId,sId);
        if ((effectedRow!=0)){
            System.out.println("Book returned");
        }else {
            System.out.println("Xatolik sodir bo'di");
        }

    }

    public void takenBookHistory() {
        List<StudentBook> studentBookList = studentBookRepository.takenBookHistorySpring(ComponentContainer.currentProfile.getId());
        for (StudentBook sk : studentBookList) {
            int bookId= sk.getBook().getId();
            String title=sk.getBook().getTitle();
            String author=sk.getBook().getAuthor();

            String categoryName=sk.getCategory().getName();
            LocalDateTime takenDate=sk.getCreatedDate();


            String str=String.format("%s,%s,%s,%s,%s-%s",bookId,title,author,categoryName,takenDate,sk.getReturnedDate());
            System.out.println(str);
        }

    }
    public void booksOnHand() {
        List<StudentBook> studentBookList = studentBookRepository.booksOnHandSpring();
        for (StudentBook sk : studentBookList) {
           Book book= sk.getBook();
           Profile profile= sk.getStudent();

            String categoryName=sk.getCategory().getName();
            LocalDateTime takenDate=sk.getCreatedDate();


            String str=String.format("%s,%s,%s,%s,%s-%s,%s,%s,%s",book.getId(),book.getTitle(),book.getAuthor(),
                    categoryName,takenDate,sk.getDeadlineDate(),
                    profile.getName(),profile.getSurname(),profile.getPhone());
            System.out.println(str);
        }

    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setStudentBookRepository(StudentBookRepository studentBookRepository) {
        this.studentBookRepository = studentBookRepository;
    }

    public void bookHistory(int bookId) {
        List<StudentBook> studentBookList = studentBookRepository.bookHistorySpring(bookId);
        for (StudentBook sk : studentBookList) {
            Profile profile= sk.getStudent();

            LocalDateTime takenDate=sk.getCreatedDate();
            String str=String.format("%s-%s,%s,%s,%s",
                    takenDate,sk.getReturnedDate(),
                    profile.getName(),profile.getSurname(),profile.getPhone());
            System.out.println(str);
        }

    }
    public void bestBooks() {
        List<BestStudentBook> studentBookList = studentBookRepository.bestBooksSpring();
        for (BestStudentBook sk : studentBookList) {
            Book book=sk.getStudentBook().getBook();
            Category category=sk.getStudentBook().getCategory();
            String str=String.format("%s,%s,%s,%s,%s",
                    book.getId(),book.getTitle(),book.getAuthor(),
                    category.getName(),sk.getTakenCount());
            System.out.println(str);
        }

    }
    @Autowired
    public StudentBookService(BookRepository bookRepository, StudentBookRepository studentBookRepository) {
        this.bookRepository = bookRepository;
        this.studentBookRepository = studentBookRepository;
    }
}
