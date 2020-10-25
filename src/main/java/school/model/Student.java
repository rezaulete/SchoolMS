/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import school.model.enumvalue.Gender;
import school.model.enumvalue.Groups;
import school.model.enumvalue.Isactive;
import school.model.enumvalue.Regularity;
import school.model.enumvalue.Schclass;
import school.model.enumvalue.Version;

/**
 *
 * @author Md Rezaul karim
 */
@Entity
@Table(name = "Student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "roll_no")
	private Long rollno;

	@Column(name = "registraion_no")
	private String registration;

	
	@ManyToOne
//	@Cascade(CascadeType.DELETE)
	private Stsession stsession;

	@ManyToOne
	private Section section;
	
	@Column(name = "schclass")
	private Schclass schclass;

	@Column(name = "Version")
	private Version version;
	
	@Column(name = "groups")
	private Groups groups;

	@Column(name = "gender")
	private Gender gender;
	
	@Column(name = "regularity")
	private Regularity regularity;
	
	@Column(name = "isActive")
	private Isactive Isactive;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", updatable = false)
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRollno() {
		return rollno;
	}

	public void setRollno(Long rollno) {
		this.rollno = rollno;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public Stsession getStsession() {
		return stsession;
	}

	public void setStsession(Stsession stsession) {
		this.stsession = stsession;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Schclass getSchclass() {
		return schclass;
	}

	public void setSchclass(Schclass schclass) {
		this.schclass = schclass;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Regularity getRegularity() {
		return regularity;
	}

	public void setRegularity(Regularity regularity) {
		this.regularity = regularity;
	}

	public Isactive getIsactive() {
		return Isactive;
	}

	public void setIsactive(Isactive isactive) {
		Isactive = isactive;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", rollno=" + rollno + ", registration=" + registration
				+ ", stsession=" + stsession + ", section=" + section + ", schclass=" + schclass + ", version="
				+ version + ", groups=" + groups + ", gender=" + gender + ", regularity=" + regularity + ", Isactive="
				+ Isactive + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}

	public Student(Long id, String name, Long rollno, String registration, Stsession stsession, Section section,
			Schclass schclass, Version version, Groups groups, Gender gender, Regularity regularity,
			school.model.enumvalue.Isactive isactive, Date createDate, Date modifyDate) {
		super();
		this.id = id;
		this.name = name;
		this.rollno = rollno;
		this.registration = registration;
		this.stsession = stsession;
		this.section = section;
		this.schclass = schclass;
		this.version = version;
		this.groups = groups;
		this.gender = gender;
		this.regularity = regularity;
		Isactive = isactive;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}


}
