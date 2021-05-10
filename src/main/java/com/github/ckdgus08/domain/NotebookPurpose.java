package com.github.ckdgus08.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotebookPurpose extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notebookId")
    private Notebook notebook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purposeId")
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
