package service.info;

import db.InfoDAO;
import model.Book;
import model.Info;
import model.Student;

import java.util.List;

public class InfoService implements IInfoService {
    private InfoDAO infoDAO = new InfoDAO();

    @Override
    public List<Info> showInfo() {
        return infoDAO.getInfo();
    }
    @Override
    public boolean saveInfo(Info info) {
        return infoDAO.save(info);
    }
    
    @Override
    public Info getLastAddedInfo() {
        return infoDAO.getLastAddedInfo();
    }
    @Override
    public List<Info> getAllInfo() {
        return infoDAO.getAllInfo();
    }


}
