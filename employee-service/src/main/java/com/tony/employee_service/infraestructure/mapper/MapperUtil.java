package com.tony.employee_service.infraestructure.mapper;

import org.apache.avro.Conversions;
import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper
public interface MapperUtil {
    Schema DECIMAL_SCHEMA= LogicalTypes.decimal(8,2).addToSchema(Schema.create(Schema.Type.BYTES));
    Conversions.DecimalConversion DECIMAL_CONVERSION=new Conversions.DecimalConversion();

    default BigDecimal bytesToBigDecimal(ByteBuffer bytes){
        return bytes != null ? DECIMAL_CONVERSION.fromBytes(bytes,DECIMAL_SCHEMA,DECIMAL_SCHEMA.getLogicalType()): null;
    }

    default ByteBuffer bigDecimalToBytes(BigDecimal value ){
        return value != null ? DECIMAL_CONVERSION.toBytes(value,DECIMAL_SCHEMA,DECIMAL_SCHEMA.getLogicalType()): null;
    }


    default Instant epochMillisToInstant(Long millis){return millis != null ? Instant.ofEpochMilli(millis): null;}



    default String charSequenceToString(CharSequence sequence){
        return sequence != null ? sequence.toString(): null;

    }
    default CharSequence stringToCharSequence(String sequence){
        return sequence;
    }

    default OffsetDateTime fromEpochMilli(Long epochMillis){
        return epochMillis != null ? Instant.ofEpochMilli(epochMillis).atOffset(ZoneOffset.UTC) : null;
    }
}
