package service;

public interface IDataConverter {
    <T> T dataConverter(String json, Class<T> tClass);
}
