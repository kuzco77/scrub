package com.rochefort.scrub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "CUSTOM_LOG", schema = "IIM", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomLogEntity extends EntityBase {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOM_LOG")
    @SequenceGenerator(name = "CUSTOM_LOG", initialValue = 1, allocationSize = 1, sequenceName = "CUSTOM_LOG_SEQ")
    @Id
    @Column(name = "LOG_ID")
    private Long logId;
    @Basic
    @Column(name = "DEVICE")
    private String device;
    @Basic
    @Column(name = "LOG")
    private String log;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomLogEntity that = (CustomLogEntity) o;
        return Objects.equals(logId, that.logId) && Objects.equals(device, that.device) && Objects.equals(log, that.log);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, device, log);
    }
}
