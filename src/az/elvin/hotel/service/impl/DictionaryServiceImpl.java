package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.DictionaryDao;
import az.elvin.hotel.model.Dictionary;
import az.elvin.hotel.service.DictionaryService;

import java.util.List;

public class DictionaryServiceImpl implements DictionaryService {
    private DictionaryDao dictionaryDao;

    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Dictionary> getDictionaryList() throws Exception {
        return dictionaryDao.getDictionaryList();
    }

    @Override
    public boolean addDictionary(Dictionary dictionary) throws Exception {
        return dictionaryDao.addDictionary(dictionary);
    }

    @Override
    public List<Dictionary> getDictionaryByRoomId(Long roomId) throws Exception {
        return dictionaryDao.getDictionaryByRoomId(roomId);
    }

    @Override
    public Dictionary getDictionaryByAmenitiesId(Long amenitiesId,Long roomId) throws Exception {
        return dictionaryDao.getDictionaryByAmenitiesId(amenitiesId,roomId);
    }

    @Override
    public Dictionary getDictionaryById(Long dictionaryId) throws Exception {
        return dictionaryDao.getDictionaryById(dictionaryId);
    }

    @Override
    public boolean updateDictionary(Dictionary dictionary) throws Exception {
        return dictionaryDao.updateDictionary(dictionary);
    }

    @Override
    public boolean deleteDictionary(Long dictionaryId) throws Exception {
        return dictionaryDao.deleteDictionary(dictionaryId);
    }

    @Override
    public List<Dictionary> searchDictionaryData(String keyword) throws Exception {
        return dictionaryDao.searchDictionaryData(keyword);
    }
}
