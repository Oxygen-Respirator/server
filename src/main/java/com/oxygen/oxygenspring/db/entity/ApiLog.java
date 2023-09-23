package com.oxygen.oxygenspring.db.entity;

import com.oxygen.oxygenspring.db.entity.utils.TimestampedOnlyCreated;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ApiLog extends TimestampedOnlyCreated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 2)
    @Column(name = "status", length = 2)
    private String status;

    @Size(max = 50)
    @Column(name = "method", length = 50)
    private String method;

    @Column(name = "req")
    private String req;

    @Column(name = "res")
    private String res;

    @Size(max = 50)
    @Column(name = "ip", length = 50)
    private String ip;

    @Builder
    public ApiLog(String status, String method, String req, String res, String ip) {
        this.status = status;
        this.method = method;
        this.req = req;
        this.res = res;
        this.ip = ip;
    }
}