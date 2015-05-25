package ru.improve.pricelist.ejb;

import java.util.List;

import javax.ejb.Local;

import ru.improve.pricelist.jpa.Cat;
import ru.improve.pricelist.jpa.Prod;

@Local
public interface InternalBisnessProcessLocal {

	Cat getCatById(int id);
	Prod getProdById(int id);	
	List<Cat> getCatAll();
	List<Prod> getProdAll();
	List<Cat> getCatByName(String catName);
	List<Prod> getProdByName(String prodName);
	List<Prod> getProdByPrice(double lowPrice, double highPrice);
	List<Prod> getProdAbavePrice(double lowPrice);
	List<Prod> getProdBelowPrice(double highPrice);
}
