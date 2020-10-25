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


/**
 *
 * @author Md Rezaul karim
 */
@Entity
@Table(name="Marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    private Student student;
     
    @ManyToOne
    private Exam exam;
    
    @ManyToOne
    private Course course;
    
    @Column(name = "writtenmark", scale = 4)
    private double writtenmark;
    
    @Column(name = "objectivemark", scale = 4)
    private double objectivemark;
    
    @Column(name = "labmark", scale = 4)
    private double labmark;
    
    @Column(name = "obtainmark", scale = 4)
    private double obtainmark;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",updatable=false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

	public Marks() {
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public double getWrittenmark() {
		return writtenmark;
	}

	public void setWrittenmark(double writtenmark) {
		this.writtenmark = writtenmark;
	}

	public double getObjectivemark() {
		return objectivemark;
	}

	public void setObjectivemark(double objectivemark) {
		this.objectivemark = objectivemark;
	}

	public double getLabmark() {
		return labmark;
	}

	public void setLabmark(double labmark) {
		this.labmark = labmark;
	}

	public double getObtainmark() {
		return obtainmark;
	}

	public void setObtainmark(double obtainmark) {
		this.obtainmark = obtainmark;
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
		return "Marks [id=" + id + ", student=" + student + ", exam=" + exam + ", course=" + course + ", writtenmark="
				+ writtenmark + ", objectivemark=" + objectivemark + ", labmark=" + labmark + ", obtainmark="
				+ obtainmark + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}

	public Marks(Long id, Student student, Exam exam, Course course, double writtenmark, double objectivemark,
			double labmark, double obtainmark, Date createDate, Date modifyDate) {
		super();
		this.id = id;
		this.student = student;
		this.exam = exam;
		this.course = course;
		this.writtenmark = writtenmark;
		this.objectivemark = objectivemark;
		this.labmark = labmark;
		this.obtainmark = obtainmark;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}



}
