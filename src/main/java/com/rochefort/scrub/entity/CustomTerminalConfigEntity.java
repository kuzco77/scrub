package com.rochefort.scrub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CUSTOM_TERMINAL_CONFIG", schema = "IIM", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomTerminalConfigEntity extends EntityBase {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOM_TERMINAL_CONFIG_GEN")
    @SequenceGenerator(name = "CUSTOM_TERMINAL_CONFIG_GEN", initialValue = 1, allocationSize = 1, sequenceName = "CUSTOM_TERMINAL_CONFIG_SEQ")
    @Id
    @Column(name = "CONFIG_ID")
    private Long configId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "STATUS")
    private int status;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

}
