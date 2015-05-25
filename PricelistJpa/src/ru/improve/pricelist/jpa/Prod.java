package ru.improve.pricelist.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prod database table.
 * 
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="Prod.getAll", query="SELECT p FROM Prod p"),
		@NamedQuery(name="Prod.getByName", query="SELECT DISTINCT p FROM Prod p WHERE p.name = :name"),
		@NamedQuery(name="Prod.getAbavePrice", query="SELECT DISTINCT p FROM Prod p WHERE (p.price >= :lowprice)"),
		@NamedQuery(name="Prod.getBelowPrice", query="SELECT DISTINCT p FROM Prod p WHERE (p.price <= :highprice)"),
		@NamedQuery(name="Prod.getByPrice", query="SELECT DISTINCT p FROM Prod p WHERE (p.price >= :lowprice) AND (p.price <= :highprice)")
})
public class Prod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	private double price;

	//bi-directional many-to-one association to Cat
	@ManyToOne
	@JoinColumn(name="CAT_ID")
	private Cat cat;

	public Prod() {
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

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Cat getCat() {
		return this.cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

}