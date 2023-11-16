package org.acme.Temp.Entities;

import jakarta.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbTransient;

import java.util.Objects;

@Entity
@Table(name = "season2", schema = "sql11655762", catalog = "")
public class SeasonEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "studioId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonbTransient
    private StudioEntity studio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudioEntity getStudio() {
        return studio;
    }

    public void setStudio(StudioEntity studioByStudioId) {
        this.studio = studioByStudioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
