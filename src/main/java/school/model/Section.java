/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import school.model.enumvalue.Schclass;

/**
 *
 * @author Md Rezaul karim
 */
@Entity
@Table(name = "Section")
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "schclass")
	private Schclass schclass;

	public Section() {
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

	public Schclass getSchclass() {
		return schclass;
	}

	public void setSchclass(Schclass schclass) {
		this.schclass = schclass;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", title=" + title + ", schclass=" + schclass + "]";
	}

	public Section(Long id, String title, Schclass schclass) {
		super();
		this.id = id;
		this.title = title;
		this.schclass = schclass;
	}

}
