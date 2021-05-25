package com.github.ckdgus08.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ckdgus08.domain.enum_.MajorType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@ToString(of = {"id", "detail", "title", "content"})
public class Review extends BaseEntity {
    @OneToMany(mappedBy = "review")
    private final List<ReviewDeep> reviewDeeps = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long id;

    private String title;
    private String detail;

    @Column(length = 4096)
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private MajorType majorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notebookId")
    private Notebook notebook;

    public Review(Notebook notebook, User user, String detail, String title, String content) {
        this.id = null;
        this.user = user;
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
