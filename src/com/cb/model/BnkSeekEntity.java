package com.cb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;


@AllArgsConstructor
@Data
@Entity
@Table
public class BnkSeekEntity  implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    String vkey;

    @Column
    String real;

    @Column
    String pzn;

    @Column(nullable = false)
    String uer;

    @Column(nullable = false)
    String rgn;

    @Column
    String ind;

    @Column
    String tnp;

    @Column
    String nnp;

    @Column
    String adr;

    @Column
    String rkc;

    @Column(nullable=false)
    String namep;

    @Column(nullable=false)
    String namen;

    @Column(nullable=false)
    String newnum;

    @Column
    String newks;

    @Column
    String permfo;

    @Column(nullable=false)
    String srok;

    @Column
    String at1;

    @Column
    String at2;

    @Column
    String telef;

    @Column
    String regn;

    @Column
    String okpo;

    @Column(nullable = false)
    Date dt_izm;

    @Column
    String cks;

    @Column
    String kznp;

    @Column(nullable = false)
    Date date_in;

    @Column
    Date date_ch;

    @Column
    String vkeydel;

    @Column
    Date dt_izmr;

}
