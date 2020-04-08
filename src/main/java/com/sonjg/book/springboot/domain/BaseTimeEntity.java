package com.sonjg.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass /* JPA Entity inherits this class has attr as columns in this class*/
@EntityListeners(AuditingEntityListener.class) /* enable auditing */
public abstract class BaseTimeEntity {
/* super class for every entity and manage createdDate and modifiedDate */
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
