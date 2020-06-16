package az.elvin.hotel.service;

import az.elvin.hotel.model.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> getDictionaryList() throws Exception;

    boolean addDictionary(Dictionary dictionary) throws Exception;

    List<Dictionary> getDictionaryByRoomId(Long roomId) throws Exception;

    Dictionary getDictionaryByAmenitiesId(Long amenitiesId,Long roomId) throws Exception;

    Dictionary getDictionaryById(Long dictionaryId) throws Exception;

    boolean updateDictionary(Dictionary dictionary) throws Exception;

    boolean deleteDictionary(Long dictionaryId) throws Exception;

    List<Dictionary> searchDictionaryData(String keyword) throws Exception;
}
