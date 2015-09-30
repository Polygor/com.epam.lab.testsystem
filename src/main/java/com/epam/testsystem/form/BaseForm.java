package com.epam.testsystem.form;

import com.epam.testsystem.model.BaseEntity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;

public abstract class BaseForm<E extends BaseEntity> extends ActionForm {
    protected Long id;
//    protected boolean deleted;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private User createdBy;
//    private User updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract void map(E e);

    public abstract void update(E e);
}
