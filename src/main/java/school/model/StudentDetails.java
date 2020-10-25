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
@Table(name="StudentDetais")
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
    
    @Column(name = "stsession")
    private String stsession;
    
    @Column(name = "class")
    private Schclass grade;
    
    @Column(name = "Version")
    private Version version;
  
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "admitiongdate")
    @Temporal(TemporalType.DATE)
	private Date admitiondate;
	
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


	
}
