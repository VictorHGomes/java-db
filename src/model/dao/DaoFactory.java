package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }  //Criei esse metodo para instanciar o SellerDaoJDBC sem expor a implementação


}
