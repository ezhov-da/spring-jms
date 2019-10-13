package ru.ezhov.springjms;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public TestEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
