package com.example.demo.jpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.example.demo.jpa.Memo;
import com.example.demo.jpa.MemoEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoMapper {
    Memo entityToApi(MemoEntity entity);
    MemoEntity apiToEntity(Memo api);
    List<Memo> entityListToApiList(List<MemoEntity> entity);
    List<MemoEntity> apiListToEntityList(List<Memo> api);
    Iterable<Memo> entityListToApiList(Iterable<MemoEntity> entity);
    Iterable<MemoEntity> apiListToEntityList(Iterable<Memo> api);
    
}
