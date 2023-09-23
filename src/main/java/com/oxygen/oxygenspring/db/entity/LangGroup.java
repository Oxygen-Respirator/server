package com.oxygen.oxygenspring.db.entity;

import com.oxygen.oxygenspring.db.entity.utils.TimestampedOnlyCreated;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class LangGroup extends TimestampedOnlyCreated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Builder
    public LangGroup(String name) {
        this.name = name;
    }


    public LangGroup(Long groupId) {
        this.id = groupId;
    }
}
