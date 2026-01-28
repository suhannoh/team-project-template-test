package com.yonsai.books.domain;

import com.yonsai.books.dto.BookAddRequest;
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
     *  todo :
     *      이 아래 메서드들은 아직은 사용하지 않지만 미리 생성
     */

    /**
     * 유저가 구매를 할 때 재고를 차감한다.
     * @param quantity 구매 수량
     */
    public void sell(int quantity) {
        updateStock(this.stock - quantity);
    }

    /**
     * 판매자가 재고를 추가할 때 재고를 증가한다.
     * @param quantity 판매 수량
     */
    public void addStock (int quantity) {
        updateStock(this.stock + quantity);
    }

    /**
     * 재고 업데이트
     * @param quantity
     */
    public void updateStock(int quantity) {
        this.stock = quantity;
        // 재고가 0 이하라면 SOLD_OUT 상태로 변경
        if (this.stock <= 0) {
            updateSellStatusSoldOut();
        }
    }
    // 재고가 0 이하라면 SOLD_OUT 상태로 변경
    private void updateSellStatusSoldOut() {
        this.sellStatus = SellStatus.SOLD_OUT;
    }
    // 재고가 0 초과라면 IN_STOCK 상태로 변경
    public void updateSellStatusInStock() {
        this.sellStatus = SellStatus.IN_STOCK;
    }
}
