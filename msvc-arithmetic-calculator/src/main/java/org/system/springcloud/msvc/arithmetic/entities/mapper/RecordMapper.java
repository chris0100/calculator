package org.system.springcloud.msvc.arithmetic.entities.mapper;

import org.mapstruct.Mapper;
import org.system.springcloud.msvc.arithmetic.entities.dtos.RecordDto;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;

@Mapper(componentModel = "spring")
public interface RecordMapper {

    RecordDto recordToRecordDto(Record record);
}
