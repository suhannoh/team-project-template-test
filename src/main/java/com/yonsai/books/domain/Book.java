package com.yonsai.books.domain;

import com.yonsai.books.dto.BookAddRequest;
import com.yonsai.books.dto.BookUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 도서 엔티티
 * - BookAddRequest DTO와 매핑하여 생성한다
 *
 */

@Entity
@Getter
@NoArgsConstructor
@Table (name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long bookId;
    @Column(name="category")
    private String category;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="description")
    private String description;
    @Column(name="price", nullable = false)
    private int price;
    @Column(name="discount", nullable = false)
    private int discount;
    @Column(name="pages")
    private int pages;
    @Column(name="stock")
    private int stock;
    @Column(name="sell_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;
    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public Book(BookAddRequest request) {
        this.category = request.category();
        this.title = request.title();
        this.author = request.author();
        this.description = request.description();
        this.price = request.price();
        this.discount = request.discount();
        this.pages = request.pages();
        this.sellStatus = SellStatus.IN_STOCK;
        this.stock = request.stock();
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", pages=" + pages +
                ", stock=" + stock +
                ", sellStatus=" + sellStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


    /**
     * 객체 수정이 필요할 때 해당 메서드를 불러 수정을 진행한다
     *  - 수정 요청 DTO 에서 모든 검사를 끝내고 들어와서 값만 바꿔주었음
     * @param request
     */
    public void patch (BookUpdateRequest request) {
       this.category = request.category();
       this.title = request.title();
       this.author = request.author();
       this.description = request.description();
       this.price = request.price();
       this.discount = request.discount();
       this.pages = request.pages();
       updateStock(request.stock());
    }


    /**
     * 재고 업데이트
     *  - 재고에 따라 판매 상태도 변경된다.
     *  - 판매상태는 직접 바꾸지 않는다
     * @param quantity
     */
    public void updateStock(int quantity) {
        this.stock = quantity;
        // 재고가 0 이하라면 SOLD_OUT 상태로 변경
        this.sellStatus = quantity > 0 ? SellStatus.IN_STOCK : SellStatus.SOLD_OUT;
    }
}
