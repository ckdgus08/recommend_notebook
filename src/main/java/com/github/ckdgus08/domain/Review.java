package com.github.ckdgus08.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString(of = {"id", "detail", "title", "content"})
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;

    private String title;
    private String detail;

    @Column(length = 4096)
    private String content;

    @OneToMany(mappedBy = "review")
    private List<Review_deep> review_deeps = new ArrayList<>();

    public Review(Notebook notebook, String detail, String title, String content) {
        this.id = null;
        this.detail = detail;
        this.title = title;
        this.content = content;
        if (notebook != null)
            addReview(notebook);
    }

    public void addReview(Notebook notebook) {
        this.notebook = notebook;
        notebook.getReviews().add(this);
    }

}
