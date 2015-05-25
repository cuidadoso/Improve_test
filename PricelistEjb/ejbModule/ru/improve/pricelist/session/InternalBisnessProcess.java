package ru.improve.pricelist.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.improve.pricelist.ejb.InternalBisnessProcessLocal;
import ru.improve.pricelist.jpa.Cat;
import ru.improve.pricelist.jpa.Prod;

/**
 * Session Bean implementation class InternalBisnessProcess
 */
@Stateless
public class InternalBisnessProcess implements InternalBisnessProcessLocal {

	@PersistenceContext(unitName="PricelistJpa")
	EntityManager entityManagerPricelist;
    /**
     * Default constructor. 
     */
    public InternalBisnessProcess() {
        
    }

	@Override
	public Cat getCatById(int id) {
		return entityManagerPricelist.find(Cat.class, id);
	}

	@Override
	public Prod getProdById(int id) {
		return entityManagerPricelist.find(Prod.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cat> getCatAll() {
		return entityManagerPricelist.createNamedQuery("Cat.getAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prod> getProdAll() {
		return entityManagerPricelist.createNamedQuery("Prod.getAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cat> getCatByName(String catName) {
		List<Cat> result = null;
		result = entityManagerPricelist.createNamedQuery("Cat.getByName").setParameter("name", catName).getResultList();
		for (Cat c : result) {
			c.getProds().size();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prod> getProdByName(String prodName) {
		List<Prod> result = null;
		result = entityManagerPricelist.createNamedQuery("Prod.getByName").setParameter("name", prodName).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prod> getProdByPrice(double lowPrice, double highPrice) {
		List<Prod> result = null;
		result = entityManagerPricelist.createNamedQuery("Prod.getByPrice").setParameter("lowprice", lowPrice).setParameter("highprice", highPrice).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prod> getProdAbavePrice(double lowPrice) {
		List<Prod> result = null;
		result = entityManagerPricelist.createNamedQuery("Prod.getAbavePrice").setParameter("lowprice", lowPrice).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prod> getProdBelowPrice(double highPrice) {
		List<Prod> result = null;
		result = entityManagerPricelist.createNamedQuery("Prod.getBelowPrice").setParameter("highprice", highPrice).getResultList();
		return result;
	}

}
