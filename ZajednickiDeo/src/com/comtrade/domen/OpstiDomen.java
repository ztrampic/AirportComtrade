package com.comtrade.domen;

import java.sql.ResultSet;
import java.util.List;

public interface OpstiDomen {
	public String vratiNazivZaUnos();
	public String vratiNazivTabele();
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs);
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od);
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od);
	public String vratiNazivKolona();
	
}
