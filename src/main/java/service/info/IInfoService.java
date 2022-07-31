package service.info;

import model.Info;

import java.util.List;

interface IInfoService {

    List<Info> showInfo();

	boolean saveInfo(Info info);

	Info getLastAddedInfo();

	List<Info> getAllInfo();

}
