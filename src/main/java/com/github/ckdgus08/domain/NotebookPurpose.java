package com.github.ckdgus08.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class NotebookPurpose extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    public NotebookPurpose(Notebook notebook, Purpose purpose) {
        this.notebook = notebook;
        this.purpose = purpose;
        if (notebook != null)
            addPurposeToNotebook(notebook);
    }

    void addPurposeToNotebook(Notebook notebook) {
        this.notebook = notebook;
        notebook.getNotebookPurposes().add(this);
    }
}
