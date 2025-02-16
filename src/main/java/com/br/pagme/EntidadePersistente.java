package com.br.pagme;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class EntidadePersistente<T extends Serializable> implements Persistable<T> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o){ return true;}

        if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        EntidadePersistente<?> that = (EntidadePersistente<?>) o;
         return id != null && Objects.equals(id, that.id);
    }

    @Override
    public T getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
