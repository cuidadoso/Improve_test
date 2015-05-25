package ru.improve.pricelist.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat database table.
 * 
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="Cat.getAll", query="SELECT c FROM Cat c"),
		@NamedQuery(name="Cat.getByName", query="SELECT DISTINCT c FROM Cat c WHERE c.name = :name")
})
public class Cat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-one association to Prod
	@OneToMany(mappedBy="cat")
	private List<Prod> prods;

	public Cat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Prod> getProds() {
		return this.prods;
	}

	public void setProds(List<Prod> prods) {
		this.prods = prods;
	}

	public Prod addProd(Prod prod) {
		getProds().add(prod);
		prod.setCat(this);
		return prod;
	}

	public Prod removeProd(Prod prod) {
		getProds().remove(prod);
		prod.setCat(null);
		return prod;
	}

}