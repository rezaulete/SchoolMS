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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import school.model.enumvalue.Coursetype;
import school.model.enumvalue.Groups;
import school.model.enumvalue.Schclass;

/**
 *
 * @author Md Rezaul karim
 */
@Entity
@Table(name = "Course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "code")
	private String code;

	@Column(name = "schclass")
	private Schclass schclass;

	@Column(name = "Course_type")
	private Coursetype coursetype;

	@Column(name = "groups")
	private Groups groups;

	@Column(name = "Highest_mark", scale = 4)
	private double highestmark;

	@Column(name = "pass_mark", scale = 4)
	private double passmark;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", updatable = false)
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	// @ManyToMany(fetch = FetchType.LAZY)
	// private Set<TakeCourse> takeCourse = new HashSet<>();

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Schclass getSchclass() {
		return schclass;
	}

	public void setSchclass(Schclass schclass) {
		this.schclass = schclass;
	}

	public Coursetype getCoursetype() {
		return coursetype;
	}

	public void setCoursetype(Coursetype coursetype) {
		this.coursetype = coursetype;
	}

	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public double getHighestmark() {
		return highestmark;
	}

	public void setHighestmark(double highestmark) {
		this.highestmark = highestmark;
	}

	public double getPassmark() {
		return passmark;
	}

	public void setPassmark(double passmark) {
		this.passmark = passmark;
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
		return "Course [id=" + id + ", title=" + title + ", code=" + code + ", schclass=" + schclass + ", coursetype="
				+ coursetype + ", groups=" + groups + ", highestmark=" + highestmark + ", passmark=" + passmark
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}

	public Course(Long id, String title, String code, Schclass schclass, Coursetype coursetype, Groups groups,
			double highestmark, double passmark, Date createDate, Date modifyDate) {
		super();
		this.id = id;
		this.title = title;
		this.code = code;
		this.schclass = schclass;
		this.coursetype = coursetype;
		this.groups = groups;
		this.highestmark = highestmark;
		this.passmark = passmark;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

}
