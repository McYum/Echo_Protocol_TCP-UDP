package org.acme.Temp.Entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "studio2", schema = "sql11655762", catalog = "")
public class StudioEntity extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "studio", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<SeasonEntity> seasonsById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudioEntity that = (StudioEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Collection<SeasonEntity> getSeasonsById() {
        return seasonsById;
    }

    public void setSeasonsById(Collection<SeasonEntity> studiosById) {
        this.seasonsById = studiosById;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
