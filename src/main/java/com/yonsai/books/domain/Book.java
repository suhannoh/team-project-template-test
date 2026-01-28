package com.yonsai.books.domain;

import com.yonsai.books.dto.BookAddRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
                ", sellStatus=" + sellStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
