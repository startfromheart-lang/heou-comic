package com.heou.comicgenerator.mapper;

import com.heou.comicgenerator.entity.ComicCharacter;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ComicCharacterMapper {
    List<ComicCharacter> selectCharacterList(ComicCharacter character);
    ComicCharacter selectCharacterById(Integer id);
    int insertCharacter(ComicCharacter character);
    int updateCharacter(ComicCharacter character);
    int deleteCharacterById(Integer id);
    int countProjectByCharacterId(Integer characterId);
}