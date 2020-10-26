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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import school.model.enumvalue.Schclass;
import school.model.enumvalue.Version;

/**
 *
 * @author Md Rezaul karim
 */
@Entity
@Table(name="StudentDetails")
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    
    @OneToOne
    private Student student;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@Column(name = "address")
	private String address;
    
    @Column(name = "father_name")
    private String fathername;
    
    @Column(name = "father_occupation")
    private String fatheroccupation;
        
	@Column(name = "father_contact")
	private Long fathercontactno;
	
	@Column(name = "father_nid")
	private Long fathernid;
	
    @Column(name = "mother_name")
    private String mothername;
    
    @Column(name = "mother_occupation")
    private String motheroccupation;
        
	@Column(name = "mother_contact")
	private Long mothercontactno;
	
	@Column(name = "mother_nid")
	private Long mothernid;
    
    @Column(name = "guardian_name")
    private String guardianname;
    
    @Column(name = "guardian_contact")
	private Long guardiancontactno;
    
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "admitiongdate")
    @Temporal(TemporalType.DATE)
	private Date admissiondate;
	
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",updatable=false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
	

	public StudentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getFathername() {
		return fathername;
	}


	public void setFathername(String fathername) {
		this.fathername = fathername;
	}


	public String getFatheroccupation() {
		return fatheroccupation;
	}


	public void setFatheroccupation(String fatheroccupation) {
		this.fatheroccupation = fatheroccupation;
	}


	public Long getFathercontactno() {
		return fathercontactno;
	}


	public void setFathercontactno(Long fathercontactno) {
		this.fathercontactno = fathercontactno;
	}


	public Long getFathernid() {
		return fathernid;
	}


	public void setFathernid(Long fathernid) {
		this.fathernid = fathernid;
	}


	public String getMothername() {
		return mothername;
	}


	public void setMothername(String mothername) {
		this.mothername = mothername;
	}


	public String getMotheroccupation() {
		return motheroccupation;
	}


	public void setMotheroccupation(String motheroccupation) {
		this.motheroccupation = motheroccupation;
	}


	public Long getMothercontactno() {
		return mothercontactno;
	}


	public void setMothercontactno(Long mothercontactno) {
		this.mothercontactno = mothercontactno;
	}


	public Long getMothernid() {
		return mothernid;
	}


	public void setMothernid(Long mothernid) {
		this.mothernid = mothernid;
	}


	public String getGuardianname() {
		return guardianname;
	}


	public void setGuardianname(String guardianname) {
		this.guardianname = guardianname;
	}


	public Long getGuardiancontactno() {
		return guardiancontactno;
	}


	public void setGuardiancontactno(Long guardiancontactno) {
		this.guardiancontactno = guardiancontactno;
	}


	public Date getAdmissiondate() {
		return admissiondate;
	}


	public void setAdmissiondate(Date admissiondate) {
		this.admissiondate = admissiondate;
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
