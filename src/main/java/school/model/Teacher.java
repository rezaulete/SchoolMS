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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import school.model.enumvalue.Gender;


/**
 *
 * @author Md Rezaul karim
 */
@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
    private Long id;

    @NotNull(message = "Teacher name is required.")
    @Size(min = 2, max=30 ,message = "Teacher name must be 2 to 30 characters.")
	@Column(name = "teacher_name")
	private String teachername;
	
    @NotNull(message = "Teacher designation is required.")
    @Column(name = "designation")
	private String designation;
    
    @Column(name = "shortprofile")
	private String shortprofile;
 
    @NotNull(message = "Phone number is required.")
	@Column(name = "Phonenumber")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "joiningdate")
    @Temporal(TemporalType.DATE)
	private Date joiningdate;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "picFile")
	private String picFile;
	
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "facebook")
	private String facebook;
	
	@Column(name = "twitter")
	private String twitter;
	
	@Column(name = "linkedin")
	private String linkedin;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", updatable = false)
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getShortprofile() {
		return shortprofile;
	}

	public void setShortprofile(String shortprofile) {
		this.shortprofile = shortprofile;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicFile() {
		return picFile;
	}

	public void setPicFile(String picFile) {
		this.picFile = picFile;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
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


}
