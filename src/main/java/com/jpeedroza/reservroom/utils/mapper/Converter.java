package com.jpeedroza.reservroom.utils.mapper;

public interface Converter<T, S> {
  T convertToEntity(S sourceOrigin);
  S convertToDTO(T targetOrigin);
}
